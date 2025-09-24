package ch.heg.util.counter;

public class Counter implements ICounter {

    private int value;

    protected Counter(int value) {
        this.value = value;
    }

    public Counter() {
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
