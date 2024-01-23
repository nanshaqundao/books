package com.example.demo.app05.s01.config;

import com.example.demo.app05.s01.Battery;
import com.example.demo.app05.s01.Disc;
import com.example.demo.app05.s01.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.demo.app05.s01")
public class ShopConfiguration {

	@Bean
	public Product aaa() {
		return new Battery("AAA", 2.5, true);
	}

	@Bean
	public Product cdrw() {
		return new Disc("CD-RW", 1.5, 700);
	}

	@Bean
	public Product dvdrw() {
		return new Disc("DVD-RW", 3.0, 4900);
	}
}
