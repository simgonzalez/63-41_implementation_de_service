package ch.heg.util.counter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SimpleCounter implements Counter {
  Logger logger = (Logger) LogManager.getLogger(SimpleCounter.class);
  private int value;

  public void inc() throws CounterException {
    value++;
    logger.info("Simple counter incremented from " + value-- + " to " + value);
  }

  public void add(int step) throws CounterException {
    value += step;
    logger.info("Simple counter add " + step +" new value " + value);
  }

  @Override
  public void reset(int value) throws CounterException {
    this.value = value;
    logger.info("Simple counter resetted to "+ value);
  }
}
