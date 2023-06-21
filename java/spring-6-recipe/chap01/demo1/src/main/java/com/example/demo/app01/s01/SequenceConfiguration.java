package com.example.demo.app01.s01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SequenceConfiguration {

  @Bean
  public Sequence sequence() {
    var sequence = new Sequence();
    sequence.setPrefix("30");
    sequence.setSuffix("A");
    sequence.setInitial(100000);
    return sequence;
  }
}
