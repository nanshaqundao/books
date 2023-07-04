package com.example.demo.app02;

public abstract class Product {

    private final String name;
    private final Double price;

    protected Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String name() {
        return name;
    }

    public Double price() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
