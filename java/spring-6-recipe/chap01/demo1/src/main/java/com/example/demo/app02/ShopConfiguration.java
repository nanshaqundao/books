package com.example.demo.app02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShopConfiguration {

    @Bean
    public Product aaa(){
        return new Battery("AAA", 2.5, true);

    }

    @Bean Product cdrw(){
        return new Disc("CD-RW", 1.5, 3);
    }


}
