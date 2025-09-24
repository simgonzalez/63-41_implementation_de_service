import ch.heg.util.admin.Person;
import ch.heg.util.admin.PersonHelper;
import lombok.SneakyThrows;

@SneakyThrows
void main() {
        Person fred = new Person("fred@hesg.ch", "Fred");
        System.out.println(PersonHelper.toJson(fred));
}