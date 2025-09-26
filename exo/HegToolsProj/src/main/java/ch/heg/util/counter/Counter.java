package ch.heg.util.counter;

public interface Counter {
  void inc() throws CounterException;

  void add(int step) throws CounterException;

  int getValue();

  void reset(int value) throws CounterException;
}
