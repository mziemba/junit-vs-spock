package tech.viacom.junitvsspock;

import com.google.common.base.Strings;

public class PersonService {

    public Person hidePhoneNumber(Person person) {
        final String hiddenPhoneNumber = person.getPhoneNumber() != null
                ? Strings.repeat("*", person.getPhoneNumber().length())
                : null;
        return new Person(person.getFirstName(), person.getLastName(), hiddenPhoneNumber, person.getAge());
    }
}
