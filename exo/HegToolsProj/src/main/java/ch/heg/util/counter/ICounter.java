package ch.heg.util.counter;

public interface ICounter {
    void inc() throws CounterException;

    void add(int step) throws CounterException;

    int getValue();

    void reset(int value) throws CounterException;
}
