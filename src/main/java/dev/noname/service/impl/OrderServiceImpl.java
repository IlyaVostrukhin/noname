package dev.noname.service.impl;

import dev.noname.entity.Order;
import dev.noname.entity.OrderItem;
import dev.noname.entity.Product;
import dev.noname.exception.AccessDeniedException;
import dev.noname.exception.InternalServerErrorException;
import dev.noname.exception.ResourceNotFoundException;
import dev.noname.form.ProductForm;
import dev.noname.jdbc.JDBCUtils;
import dev.noname.jdbc.ResultSetHandler;
import dev.noname.jdbc.ResultSetHandlerFactory;
import dev.noname.model.CurrentAccount;
import dev.noname.model.ShoppingCart;
import dev.noname.model.ShoppingCartItem;
import dev.noname.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class OrderServiceImpl implements OrderService {

    private Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private static final ResultSetHandler<Product> productResultSetHandler =
            ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.PRODUCT_RESULT_SET_HANDLER);

    private static final ResultSetHandler<Order> orderResultSetHandler =
            ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.ORDER_RESULT_SET_HANDLER);

    private final ResultSetHandler<List<OrderItem>> orderItemListResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.ORDER_ITEM_RESULT_SET_HANDLER);

    private final ResultSetHandler<List<Order>> ordersResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.ORDER_RESULT_SET_HANDLER);

    private final ResultSetHandler<Integer> countResultSetHandler =
            ResultSetHandlerFactory.getCountResultSetHandler();

    private DataSource dataSource;

    public OrderServiceImpl(DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

    @Override
    public void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) {
        try (Connection connection = dataSource.getConnection()) {
            Product product = JDBCUtils.select(connection, "select p.*, c.name as category, pr.name " +
                    "as producer from product p, producer pr, category c where c.id=p.id_category " +
                    "and pr.id=p.id_producer and p.id=?", productResultSetHandler, productForm.getIdProduct());
            if (product == null) {
                throw new InternalServerErrorException("Product not found by id=" + productForm.getIdProduct());
            }
            shoppingCart.addProduct(product, productForm.getCount());
        } catch (SQLException exc) {
            throw new InternalServerErrorException("Can't execute SQL query: " + exc.getMessage(), exc);
        }
    }

    @Override
    public void removeProductFromShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) {
        shoppingCart.removeProduct(productForm.getIdProduct(), productForm.getCount());
    }

    @Override
    public String serializeShoppingCart(ShoppingCart shoppingCart) {
        StringBuilder res = new StringBuilder();
        for (ShoppingCartItem item : shoppingCart.getItems()) {
            res.append(item.getProduct().getId()).append("-").append(item.getCount()).append("|");
        }
        if (res.length() > 0) {
            res.deleteCharAt(res.length() - 1);
        }
        return res.toString();
    }

    @Override
    public ShoppingCart deserializeShoppingCart(String string) {
        ShoppingCart shoppingCart = new ShoppingCart();
        String[] items = string.split("\\|");
        for (String item : items) {
            try {
                String[] data = item.split("-");
                int idProduct = Integer.parseInt(data[0]);
                int count = Integer.parseInt(data[1]);
                addProductToShoppingCart(new ProductForm(idProduct, count), shoppingCart);
            } catch (RuntimeException e) {
                LOGGER.error("Can't add product to ShoppingCart during deserialization: item=" + item, e);
            }
        }
        return shoppingCart.getItems().isEmpty() ? null : shoppingCart;
    }

    @Override
    public long makeOrder(ShoppingCart shoppingCart, CurrentAccount currentAccount) {
        if (shoppingCart == null || shoppingCart.getItems().isEmpty()) {
            throw new InternalServerErrorException("Shopping Cart is null or Empty");
        }
        try (Connection connection = dataSource.getConnection()) {
            Order order = JDBCUtils.insert(connection, "insert into \"order\" values(nextval('order_seq'), ?, ?)",
                    orderResultSetHandler, currentAccount.getId(), new Timestamp(System.currentTimeMillis()));
            JDBCUtils.insertBatch(connection, "insert into \"orderItem\" values(nextval('order_item_seq'), ?, ?, ?)",
                    toOrderItemParameterList(order.getId(), shoppingCart.getItems()));
            connection.commit();
            return order.getId();
        } catch (SQLException exc) {
            throw new InternalServerErrorException("Can't execute SQL query: " + exc.getMessage(), exc);
        }
    }

    private List<Object[]> toOrderItemParameterList(long idOrder, Collection<ShoppingCartItem> items) {
        List<Object[]> parametersList = new ArrayList<>();
        for (ShoppingCartItem item : items) {
            parametersList.add(new Object[] {idOrder, item.getProduct().getId(), item.getCount()});
        }
        return parametersList;
    }

    @Override
    public Order findOrderById(long id, CurrentAccount currentAccount) {
        try (Connection connection = dataSource.getConnection()){
            Order order = JDBCUtils.select(connection, "select * from \"order\" where id=?", orderResultSetHandler, id);
            if (order == null) {
                throw new ResourceNotFoundException("Order not found by id: " + id);
            }
            if (!order.getIdAccount().equals(currentAccount.getId())) {
                throw new AccessDeniedException("Account with id=" + currentAccount.getId() + " is not owner for order with id=" + id);
            }
            List<OrderItem> list = JDBCUtils.select(connection,
                    "select o.id as oid, o.id_order as id_order, o.id_product, o.count, p.*, c.name as category, pr.name as producer "
                            + "from \"orderItem\" o, product p, category c, producer pr "
                            + "where pr.id=p.id_producer and c.id=p.id_category and o.id_product=p.id and o.id_order=?",
                    orderItemListResultSetHandler, id);
            order.setItems(list);
            return order;
        } catch (SQLException exc) {
            throw new InternalServerErrorException("Can't execute SQL query: " + exc.getMessage(), exc);
        }
    }

    @Override
    public List<Order> listMyOrders(CurrentAccount currentAccount, int page, int limit) {
        int offset = (page - 1) * limit;
        try (Connection connection = dataSource.getConnection()){
            List<Order> orders = JDBCUtils.select(connection, "select * from \"order\" where id_account=? " +
                    "order by id desc limit ? offset ?", ordersResultSetHandler, currentAccount.getId(), limit, offset);
            return orders;
        } catch (SQLException exc) {
            throw new InternalServerErrorException("Can't execute SQL query: " + exc.getMessage(), exc);
        }
    }

    @Override
    public int countMyOrders(CurrentAccount currentAccount) {
        try (Connection connection = dataSource.getConnection()){
            return JDBCUtils.select(connection,
                    "select count(*) from \"order\" where id_account=?", countResultSetHandler, currentAccount.getId());
        } catch (SQLException exc) {
            throw new InternalServerErrorException("Can't execute SQL query: " + exc.getMessage(), exc);
        }
    }
}
