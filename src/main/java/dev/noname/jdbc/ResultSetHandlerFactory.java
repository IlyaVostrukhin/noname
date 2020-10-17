package dev.noname.jdbc;

import dev.noname.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetHandlerFactory {
    public final static ResultSetHandler<Product> PRODUCT_RESULT_SET_HANDLER =
            new ResultSetHandler<Product>() {
                @Override
                public Product handle(ResultSet rs) throws SQLException {
                    Product product = new Product();
                    product.setCategory(rs.getString("category"));
                    product.setDescription(rs.getString("description"));
                    product.setId(rs.getInt("id"));
                    product.setImageLink(rs.getString("image_link"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getBigDecimal("price"));
                    product.setProducer(rs.getString("producer"));
                    return product;
                }
            };

    public final static ResultSetHandler<Category> CATEGORY_RESULT_SET_HANDLER =
            new ResultSetHandler<Category>() {
                @Override
                public Category handle(ResultSet rs) throws SQLException {
                    Category category = new Category();
                    category.setId(rs.getInt("id"));
                    category.setName(rs.getString("name"));
                    category.setUrl(rs.getString("url"));
                    category.setProductCount(rs.getInt("product_count"));
                    return category;
                }
            };

    public final static ResultSetHandler<Producer> PRODUCER_RESULT_SET_HANDLER =
            new ResultSetHandler<Producer>() {
                @Override
                public Producer handle(ResultSet rs) throws SQLException {
                    Producer producer = new Producer();
                    producer.setId(rs.getInt("id"));
                    producer.setName(rs.getString("name"));
                    producer.setProductCount(rs.getInt("product_count"));
                    return producer;
                }
            };

    public final static ResultSetHandler<Account> ACCOUNT_RESULT_SET_HANDLER =
            new ResultSetHandler<Account>() {
                @Override
                public Account handle(ResultSet rs) throws SQLException {
                    Account a = new Account();
                    a.setId(rs.getInt("id"));
                    a.setEmail(rs.getString("email"));
                    a.setName(rs.getString("name"));
                    return a;
                }
            };

    public final static ResultSetHandler<OrderItem> ORDER_ITEM_RESULT_SET_HANDLER =
            new ResultSetHandler<OrderItem>() {
                @Override
                public OrderItem handle(ResultSet rs) throws SQLException {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setId(rs.getLong("id"));
                    orderItem.setCount(rs.getInt("count"));
                    orderItem.setIdOrder(rs.getLong("id_order"));
                    Product product = PRODUCT_RESULT_SET_HANDLER.handle(rs);
                    orderItem.setProduct(product);
                    return orderItem;
                }
            };

    public final static ResultSetHandler<Order> ORDER_RESULT_SET_HANDLER =
            new ResultSetHandler<Order>() {
                @Override
                public Order handle(ResultSet rs) throws SQLException {
                    Order order = new Order();
                    order.setId(rs.getLong("id"));
                    order.setCreated(rs.getTimestamp("created"));
                    order.setIdAccount(rs.getInt("id_account"));
                    return order;
                }
            };

    public final static <T> ResultSetHandler<T> getSingleResultSetHandler(
            final ResultSetHandler<T> oneRowResultSetHandler) {
        return new ResultSetHandler<T>() {
            @Override
            public T handle(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    return oneRowResultSetHandler.handle(rs);
                } else {
                    return null;
                }
            }
        };
    }

    public final static <T> ResultSetHandler<List<T>> getListResultSetHandler(
            final ResultSetHandler<T> oneRowResultSetHandler) {
        return new ResultSetHandler<List<T>>() {
            @Override
            public List<T> handle(ResultSet rs) throws SQLException {
                List<T> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(oneRowResultSetHandler.handle(rs));
                }
                return list;
            }
        };
    }

    public final static ResultSetHandler<Integer> getCountResultSetHandler() {
        return new ResultSetHandler<Integer>() {
            @Override
            public Integer handle(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    return 0;
                }
            }
        };
    }
}
