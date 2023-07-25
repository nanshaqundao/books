package com.example.demo.app04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new GenericXmlApplicationContext("appContext04.xml");

        Sequence generator = (Sequence) context.getBean("sequenceGenerator");

        System.out.println(generator.getSequence());
        System.out.println(generator.getSequence());
    }
}
