package ch.heg.revision;

import ch.heg.util.counter.Counter;
import ch.heg.util.counter.CounterException;
import ch.heg.util.counter.ICounter;
import ch.heg.util.counter.UpperLimitedPositiveCounter;

import java.util.HashMap;
import java.util.Map;

public class CounterProg {

    public static void main(String[] args) throws CounterException {

        ICounter counter = new Counter();
        counter.add(10);
        System.out.println(counter.getValue());

        // counter avec limite
        //ICounter c2 = new UpperLimitedPositiveCounter(100, 10);
        ICounter c2 = new UpperLimitedPositiveCounter(10, 10); // le maximum est accepté
        new UpperLimitedPositiveCounter(0, 10); //
    }
}
