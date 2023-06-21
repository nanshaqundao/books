package com.example.demo.app01.s01;

import org.springframework.stereotype.Component;

public interface SequenceDao {
  Sequence getSequence(String sequenceId);

  int getNextValue(String sequenceId);
}
