package dev.noname.entity;

public class Producer extends AbstractEntity<Integer> {
    private static final long serialVersionUID = 897050339992593563L;

    private String name;
    private int productCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }
}
