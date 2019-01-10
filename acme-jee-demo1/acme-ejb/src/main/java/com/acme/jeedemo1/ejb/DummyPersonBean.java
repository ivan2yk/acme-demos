package com.acme.jeedemo1.ejb;

import com.acme.jeedemo1.model.Person;

import javax.ejb.Stateless;
import java.time.LocalDate;

/**
 * Created by Ivan on 10/01/2019.
 */
@Stateless
public class DummyPersonBean implements PersonEJB {

    @Override
    public Person getPersonById(Long id) {
        Person person = new Person();
        person.setId(id);
        person.setFirstName("Ivan");
        person.setLastName("Leiva");
        person.setBirthday(LocalDate.of(1990, 5,17));
        return person;
    }

    @Override
    public String sayHi() {
        return "Hello from EJB...!";
    }

}
