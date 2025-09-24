package ch.heg.util.counter;

public class UpperLimitedPositiveSimpleCounter extends SimpleCounter {
    int maxValue;

    public UpperLimitedPositiveSimpleCounter(int initialValue, int maxValue) throws CounterException {
        if(initialValue < 0 ){
        // lancer exception
            throw new CounterException("une valeur plus grande que 0 est attendue, votre valeur est : ("+initialValue+")");
        }

        if (initialValue > maxValue ) {
            //lancer exception
            throw new CounterException("une valeur initiale plus petite que le max : "+maxValue+" est attendue, votre valeur est : ("+initialValue+")");

        }
        super(initialValue);
        this.maxValue = maxValue;


        // ....

    }


    @Override
    public void inc() throws CounterException {
        if (this.getValue()+1 > maxValue) {
            throw new CounterException("La valeur du compteur dépasse la valeur maximale " + maxValue + "\nValeur avant incrément:"+this.getValue());
        }
        super.inc();
    }

    @Override
    public void add(int step) throws CounterException {
        if (this.getValue() + step > maxValue || this.getValue() + step < 0) {
            // Pas de différence dans le message d'erreur
            throw new CounterException("La valeur du compteur dépasse la valeur maximale ou est plus petit que 0" + maxValue + "\nValeur après ajout du step:"+(this.getValue() + step) + "\nstep: " + step);
        }
        super.add(step);
    }

    @Override
    public void reset(int value) throws CounterException {
        if (value > maxValue || value < 0) {
            throw new CounterException("La valeur du compteur dépasse la valeur maximale ou est plus petit que 0\nNouvelle valeur:" + value );
        }
        super.reset(value);
    }


}
