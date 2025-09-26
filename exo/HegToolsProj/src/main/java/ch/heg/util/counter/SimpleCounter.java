package ch.heg.util.counter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SimpleCounter implements Counter {
  private int value;

  public void inc() throws CounterException {
    value++;
  }

  public void add(int step) throws CounterException {
    value += step;
  }

  @Override
  public void reset(int value) throws CounterException {
    this.value = value;
  }
}
