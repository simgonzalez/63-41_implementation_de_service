package ch.heg.util.counter;

import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UpperLimitedPositiveSimpleCounterTest {
  UpperLimitedPositiveSimpleCounter upperLimitedPositiveSimpleCounter;
  private static final int VALUE_TOO_HIGH = 11;
  private static final int VALUE_TOO_LOW = -1;
  private static final int DEFAULT_VALUE = 0;
  private static final int MAX_VALUE = 10;

  @Before
  public void setUp() throws Exception {
    upperLimitedPositiveSimpleCounter =
        new UpperLimitedPositiveSimpleCounter(DEFAULT_VALUE, MAX_VALUE);
  }

  @SneakyThrows
  @Test
  public void inc_successful() {
    upperLimitedPositiveSimpleCounter.inc();

    assertEquals(1, upperLimitedPositiveSimpleCounter.getValue());
  }

  @SneakyThrows
  @Test
  public void inc_throwsWhenMoreThanLimit() {
    for (int i = 0; i < MAX_VALUE; i++) {
      upperLimitedPositiveSimpleCounter.inc();
    }

    assertThrows(CounterException.class, () -> upperLimitedPositiveSimpleCounter.inc());
  }

  @SneakyThrows
  @Test
  public void add_successful() {
    upperLimitedPositiveSimpleCounter.add(5);
    assertEquals(5, upperLimitedPositiveSimpleCounter.getValue());
  }

  @SneakyThrows
  @Test
  public void add_throwsWhenMoreThanLimit() {
    assertThrows(CounterException.class, () -> upperLimitedPositiveSimpleCounter.add(VALUE_TOO_HIGH));
  }

  @SneakyThrows
  @Test
  public void add_throwsWhenLowerThanZero() {
    assertThrows(CounterException.class, () -> upperLimitedPositiveSimpleCounter.add(VALUE_TOO_LOW));
  }


  @SneakyThrows
  @Test
  public void reset_successful() {
    upperLimitedPositiveSimpleCounter.reset(5);

    assertEquals(5, upperLimitedPositiveSimpleCounter.getValue());
  }

  @Test
  public void reset_throwsWhenMoreThanLimit() {
    assertThrows(CounterException.class, () -> upperLimitedPositiveSimpleCounter.reset(VALUE_TOO_HIGH));
  }

  @Test
  public void reset_throwsWhenLowerThanZero() {
    assertThrows(CounterException.class, () -> upperLimitedPositiveSimpleCounter.reset(VALUE_TOO_LOW));
  }
}
