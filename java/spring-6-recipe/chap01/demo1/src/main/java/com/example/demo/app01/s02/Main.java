package com.example.demo.app01.s02;

public class Main {
    public static void main(String[] args) {
        var x = new SimpleSequenceDao();
        var y = x.getSequence("IT");
        System.out.println(y.getPrefix());

        System.out.println(x.getNextValue("IT"));
        System.out.println(x.getNextValue("IT"));
    }
}
