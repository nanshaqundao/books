package com.example.demo.app01.s02;




public interface SequenceDao {
  Sequence getSequence(String sequenceId);

  int getNextValue(String sequenceId);
}
