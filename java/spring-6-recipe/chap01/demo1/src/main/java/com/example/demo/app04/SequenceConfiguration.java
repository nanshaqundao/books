package com.example.demo.app04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SequenceConfiguration {

    @Bean
    public DatePrefixGenerator datePrefixGenerator() {
        var dpg = new DatePrefixGenerator();
        dpg.setPattern("yyyyMMdd");
        return dpg;
    }

    @Bean
    public NumberPrefixGenerator numberPrefixGenerator() {
        return new NumberPrefixGenerator();
    }

    @Bean
    public Sequence sequenceGenerator() {
        var sequence = new Sequence();
        sequence.setInitial(100000);
        sequence.setSuffix("A");
        return sequence;
    }

}
