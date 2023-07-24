package com.example.demo.app03.s01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

// @Configuration
// public class SequenceConfiguration {
//
//    @Bean
//    public DatePrefixGenerator datePrefixGenerator(){
//        var gen = new DatePrefixGenerator("yyyyMMdd");
//
//        return gen;
//    }
//
//    @Bean
//    public Sequence sequenceGenerator(PrefixGenerator prefixGenerator){
//        var generator = new Sequence("A",10000);
//        generator.setPrefixGenerator(prefixGenerator);
//        return generator;
//    }
// }
@Configuration
@Import(PrefixConfiguration.class)
public class SequenceConfiguration {

  @Bean
  public Sequence sequence(PrefixGenerator prefixGenerator) {
    return new Sequence(prefixGenerator, "A", 100000);
  }
}
