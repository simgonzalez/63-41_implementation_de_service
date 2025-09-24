package ch.heg.util.counter;

public class SingletonCounter {

    private static SingletonCounter INSTANCE = new SingletonCounter();
    private long value;

    private SingletonCounter() {
        value=8;
    }

    public static SingletonCounter getInstance() {
        return INSTANCE;
    }

    public void inc() {
        value++;
    }

    public long getValue() {
        return value;
    }

    public void reset(long value) {
        this.value = value;
    }


}