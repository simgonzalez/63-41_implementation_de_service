package ch.heg.util.counter;

import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleCounterTest {
  private SimpleCounter simpleCounter;

  @Before
  public void setUp() {
      simpleCounter = new SimpleCounter();
  }

  @Test
  public void counterCreation() {
      assertNotNull(simpleCounter);
      assertEquals(0, simpleCounter.getValue());
  }

  @SneakyThrows
  @Test
  public void inc() {
      simpleCounter.inc();

      assertEquals(1, simpleCounter.getValue());
  }

  @SneakyThrows
  @Test
  public void add() {
      simpleCounter.add(10);

      assertEquals(10, simpleCounter.getValue());
  }

  @SneakyThrows
  @Test
  public void reset() {
      simpleCounter.reset(10);

      assertEquals(10, simpleCounter.getValue());
  }
}
