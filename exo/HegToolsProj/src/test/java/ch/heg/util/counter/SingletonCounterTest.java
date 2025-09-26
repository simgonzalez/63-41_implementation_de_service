package ch.heg.util.counter;

import static org.junit.Assert.*;

import org.junit.Test;

public class SingletonCounterTest {

  @Test
  public void getInstance() {
      assertEquals(SingletonCounter.getInstance(),SingletonCounter.getInstance());
  }

  @Test
  public void inc() {
      SingletonCounter.getInstance().inc();
      assertEquals(1, SingletonCounter.getInstance().getValue());
  }

  @Test
  public void reset() {
      SingletonCounter.getInstance().reset(0);
      assertEquals(0, SingletonCounter.getInstance().getValue());
  }
}
