package ch.heg.revision;


import ch.heg.util.admin.Person;
import ch.heg.util.counter.SingletonCounter;

public class Main {
    public static void main(String[] args) {

        while(true){
            SingletonCounter.getInstance().inc();
            if(SingletonCounter.getInstance().getValue() % 5 ==0 ){
                break;
            }
        }
        System.out.println(SingletonCounter.getInstance().getValue());
    }

    Person pr = new Person("fred@hesg.ch", "Fred");

}
