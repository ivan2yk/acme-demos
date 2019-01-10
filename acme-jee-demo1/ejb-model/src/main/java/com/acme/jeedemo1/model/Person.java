package com.acme.jeedemo1.model;

import java.time.LocalDate;

/**
 * Created by Ivan on 10/01/2019.
 */
public class Person {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
