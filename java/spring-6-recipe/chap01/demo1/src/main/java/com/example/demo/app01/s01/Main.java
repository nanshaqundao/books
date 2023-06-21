package com.example.demo.app01.s01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
  public static void main(String[] args) {
    var cfg = SequenceConfiguration.class;
    try (var ctx = new AnnotationConfigApplicationContext(cfg)) {
      var sequence = ctx.getBean(Sequence.class);
      System.out.println(sequence.nextValue());
      System.out.println(sequence.nextValue());
      System.out.println(sequence.nextValue());
    }
  }
}
