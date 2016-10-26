package tech.viacom.junitvsspock;

import com.google.gson.Gson;

public class PersonSerializer {

    public String serialize(Person person) {
        return new Gson().toJson(person);
    }
}
