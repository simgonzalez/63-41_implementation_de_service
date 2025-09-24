package ch.heg.revision;

import ch.heg.util.admin.Person;
import ch.heg.util.admin.PersonHelper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class PersonProg {

    public static void main(String[] args) throws JsonProcessingException {
        Person fred = new Person("fred@hesg.ch", "Fred");

        System.out.println(PersonHelper.toJson(fred));
    }
}
