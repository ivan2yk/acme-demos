package com.acme.cursojpa.demo1.domain;

import javax.persistence.*;

/**
 * Created by Ivan on 22/09/2018.
 */
@Entity
public class Car {

    private Long id;
    private String modelo;
    private Person person;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", modelo='" + modelo + '\'' +
                ", person=" + person +
                '}';
    }
}
