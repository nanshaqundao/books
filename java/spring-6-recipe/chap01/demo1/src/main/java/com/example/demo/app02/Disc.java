package com.example.demo.app02;

public class Disc extends Product {

    private final int capacity;

    public Disc(String name, Double price, int capacity) {
        super(name, price);
        this.capacity = capacity;
    }

    public int capacity() {
        return capacity;
    }

    @Override
    public String toString() {
        var msg = super.toString() + ", capacity=%dMB";
        return String.format(msg, this.capacity);
    }
//    @Override
//    public String toString() {
//        return "Disc{" +
//                "capacity=" + capacity +
//                "} " + super.toString();
//    }
}
