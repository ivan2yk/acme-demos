package com.acme.jeedemo1.ejb;

import com.acme.jeedemo1.model.Person;

import javax.ejb.Local;

/**
 * Created by Ivan on 10/01/2019.
 */
@Local
public interface PersonEJB {

    Person getPersonById(Long id);

    String sayHi();

}
