import ch.heg.util.admin.Person;
import ch.heg.util.counter.CounterException;
import ch.heg.util.counter.SingletonCounter;
import lombok.SneakyThrows;

@SneakyThrows
static void main() {
    while (true) {
        SingletonCounter.getInstance().inc();
        if (SingletonCounter.getInstance().getValue() % 5 == 0) {
            break;
        }
    }
    System.out.println(SingletonCounter.getInstance().getValue());

}