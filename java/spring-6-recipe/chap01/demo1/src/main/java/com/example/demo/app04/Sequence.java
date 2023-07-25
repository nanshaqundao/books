package com.example.demo.app04;

import jakarta.inject.Inject;

import java.util.concurrent.atomic.AtomicInteger;

public class Sequence {

    private final AtomicInteger counter = new AtomicInteger();

    @Inject
    @NumberPrefixAnnotation
    private PrefixGenerator prefixGenerator;

    private String suffix;

    private int initial;

    public Sequence() {
    }

    public Sequence(PrefixGenerator prefixGenerator, String suffix, int initial) {
        this.prefixGenerator = prefixGenerator;
        this.suffix = suffix;
        this.initial = initial;
    }

    public void setPrefixGenerator(PrefixGenerator prefixGenerator) {
        this.prefixGenerator = prefixGenerator;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public String getSequence() {
        return prefixGenerator.getPrefix() + (initial + counter.getAndIncrement()) + suffix;
    }

}