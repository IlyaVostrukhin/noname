package dev.noname.model;

import dev.noname.entity.Product;

import java.io.Serializable;

public class ShoppingCartItem implements Serializable {

    private static final long serialVersionUID = -3586435151613948016L;
    private Product product;
    private int count;

    public ShoppingCartItem(){
        super();
    }

    public ShoppingCartItem(Product product, int count) {
        super();
        this.product = product;
        this.count = count;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ShoppingCartItem [" +
                "product=" + product +
                ", count=" + count +
                ']';
    }
}
