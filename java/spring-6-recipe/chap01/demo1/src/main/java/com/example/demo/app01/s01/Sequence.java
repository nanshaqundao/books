package com.example.demo.app01.s01;

import java.util.concurrent.atomic.AtomicInteger;

public class Sequence {
  private final AtomicInteger counter = new AtomicInteger();

  private String prefix;
  private String suffix;

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }

  public void setInitial(int initial) {
    counter.set(initial);
  }

  public String nextValue() {
    return prefix + counter.getAndIncrement() + suffix;
  }
}
