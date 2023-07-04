package com.example.demo.app01.s03;


public interface SequenceDao {
  Sequence getSequence(String sequenceId);

  int getNextValue(String sequenceId);
}
