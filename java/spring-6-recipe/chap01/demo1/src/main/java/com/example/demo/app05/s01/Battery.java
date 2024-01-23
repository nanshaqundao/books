package com.example.demo.app05.s01;

public class Battery extends Product {

	private final boolean rechargeable;

	public Battery(String name, double price, boolean rechargeable) {
		super(name, price);
		this.rechargeable = rechargeable;
	}

	public boolean isRechargeable() {
		return rechargeable;
	}

}
