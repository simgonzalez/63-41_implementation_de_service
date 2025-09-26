import ch.heg.util.counter.Counter;
import ch.heg.util.counter.CounterException;
import ch.heg.util.counter.SimpleCounter;
import ch.heg.util.counter.UpperLimitedPositiveSimpleCounter;

void main(String[] args) throws CounterException {
    Counter counter = new SimpleCounter();
    counter.add(10);
    IO.println(counter.getValue());

    // counter avec limite
    new UpperLimitedPositiveSimpleCounter(10, 10);
    new UpperLimitedPositiveSimpleCounter(0, 10);
}
