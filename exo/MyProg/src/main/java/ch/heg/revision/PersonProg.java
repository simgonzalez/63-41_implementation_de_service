import ch.heg.util.admin.Person;
import ch.heg.util.admin.PersonHelper;
import lombok.SneakyThrows;

@SneakyThrows
void main() {
    Person fred = new Person("fred@hesg.ch", "Fred");
    String fredJson = PersonHelper.toJson(fred);
    IO.println(fredJson);

    List<Person> persons = new ArrayList<>();
    persons.add(fred);
    persons.add(new Person("sg@gmail.com", "Sg"));
    String personsJson = PersonHelper.toJson(persons);
    IO.println(personsJson);

    Person p = PersonHelper.fromJson(fredJson);
    IO.println(p);

    List<Person> persons2 = PersonHelper.fromJsonList(personsJson);
    IO.println(persons2);
}