package com.example.demo.app05.s01;


import com.example.demo.app05.s01.config.ShopConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		var cfg = ShopConfiguration.class;
		try (var context = new AnnotationConfigApplicationContext(cfg)) {

			var aaa = context.getBean("aaa", Product.class);
			var cdrw = context.getBean("cdrw", Product.class);
			var dvdrw = context.getBean("dvdrw", Product.class);

			var cart1 = context.getBean(ShoppingCart.class);
			cart1.addItem(aaa);
			cart1.addItem(cdrw);
			System.out.println("Shopping cart 1 contains " + cart1.getItems());

			var cart2 = context.getBean(ShoppingCart.class);
			cart2.addItem(dvdrw);
			System.out.println("Shopping cart 2 contains " + cart2.getItems());
		}
	}
}
