package com.example.demo.app03.s01;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

public class Sequence {
  //    @Autowired
  //    private List<PrefixGenerator> prefixGenerators;

  // private PrefixGenerator[] prefixGenerators;
  // private Map<String, PrefixGenerator> prefixGenerators;
  // the above two can also be used for autowire purpose

  // though it is not recommended to put autowired annotation on fields

  //    @Autowired(required = false)
  //    public void setPrefixGenerator(PrefixGenerator prefixGenerator){
  //        this.prefixGenerator = prefixGenerator;;
  //    }
  //
  //    @Autowired(required = false)
  //    public void setPrefixGenerator(ObjectProvider<PrefixGenerator> prefixGeneratorProvider){
  //        this.prefixGenerator = prefixGeneratorProvider.getIfUnique();;
  //    }

  private final AtomicInteger counter = new AtomicInteger();

  private final PrefixGenerator prefixGenerator;

  private final String suffix;

  private final int initial;

  public Sequence(PrefixGenerator prefixGenerator, String suffix, int initial) {
    this.prefixGenerator = prefixGenerator;
    this.suffix = suffix;
    this.initial = initial;
  }

  public String nextValue() {
    return prefixGenerator.getPrefix() + (initial + counter.getAndIncrement()) + suffix;
  }
}
