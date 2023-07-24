package com.example.demo.app03.s01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        var cfg = SequenceConfiguration.class;
        try (var context = new AnnotationConfigApplicationContext(cfg)) {
            var generator = context.getBean("sequence", Sequence.class);
            System.out.println(generator.nextValue());
            System.out.println(generator.nextValue());
        }
    }
}
