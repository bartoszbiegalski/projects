package com.otocado.product;

import java.util.List;

public class DeliveryMethod {
    private String name;
    private List<String> products;

    public DeliveryMethod(String name, List<String> products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setDeliveryMethods(List<String> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
