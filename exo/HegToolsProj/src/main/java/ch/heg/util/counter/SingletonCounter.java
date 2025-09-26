package ch.heg.util.counter;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class SingletonCounter {
  private static final SingletonCounter INSTANCE = new SingletonCounter();
  private long value;

  private SingletonCounter() {
    value = 0;
  }

  public static SingletonCounter getInstance() {
    return INSTANCE;
  }

  public void inc() {
    value++;
  }

  public void reset(long value) {
    this.value = value;
  }
}
