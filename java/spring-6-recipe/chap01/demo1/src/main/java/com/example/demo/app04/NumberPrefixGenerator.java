package com.example.demo.app04;


import java.util.concurrent.ThreadLocalRandom;

@NumberPrefixAnnotation
public class NumberPrefixGenerator implements PrefixGenerator {

    public String getPrefix() {
        var randomGenerator = ThreadLocalRandom.current();
        var randomInt = randomGenerator.nextInt(100);
        return String.format("%03d", randomInt);
    }

}
