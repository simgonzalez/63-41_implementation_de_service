package ch.heg.util.counter;

public class SimpleCounter implements Counter {

    private int value;

    protected SimpleCounter(int value) {
        this.value = value;
    }

    public SimpleCounter() {
        value = 0;
    }

    public void inc() throws CounterException {
        value++;
    }

    public void add(int step) throws CounterException {
        value += step;
    }
    public int getValue() {
        return value;
    }

    @Override
    public void reset(int value) throws CounterException {
        this.value = value;

    }


}
