package com.example.demo.app01.s03;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var basePackage = "com.example.demo.app01.s03";

        try(var context = new AnnotationConfigApplicationContext(basePackage)) {
            var sequenceDao = context.getBean(SequenceDao.class);

            System.out.println(sequenceDao.getNextValue("IT"));
            System.out.println(sequenceDao.getNextValue("IT"));

            System.out.println(sequenceDao.getSequence("IT"));

        }
    }
}
