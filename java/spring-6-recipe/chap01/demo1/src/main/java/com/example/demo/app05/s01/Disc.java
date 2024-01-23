package com.example.demo.app05.s01;

public class Disc extends Product {

	private final int capacity;

	public Disc(String name, double price, int capacity) {
		super(name, price);
		this.capacity = capacity;
	}

	public int getCapacity() {
		return this.capacity;
	}

}
