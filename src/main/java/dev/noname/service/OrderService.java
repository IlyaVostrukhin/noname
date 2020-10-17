package dev.noname.service;

import dev.noname.entity.Order;
import dev.noname.form.ProductForm;
import dev.noname.model.CurrentAccount;
import dev.noname.model.ShoppingCart;

import java.util.List;

public interface OrderService {

    void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart);

    void removeProductFromShoppingCart(ProductForm productForm, ShoppingCart shoppingCart);

    String serializeShoppingCart(ShoppingCart shoppingCart);

    ShoppingCart deserializeShoppingCart(String string);

    long makeOrder(ShoppingCart shoppingCart, CurrentAccount currentAccount);

    Order findOrderById(long id, CurrentAccount currentAccount);

    List<Order> listMyOrders(CurrentAccount currentAccount, int page, int limit);

    int countMyOrders(CurrentAccount currentAccount);
}
