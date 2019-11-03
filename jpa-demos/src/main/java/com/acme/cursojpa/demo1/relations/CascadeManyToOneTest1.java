package com.acme.cursojpa.demo1.relations;

import com.acme.cursojpa.demo1.domain.Car;
import com.acme.cursojpa.demo1.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Ivan on 22/09/2018.
 */
public class CascadeManyToOneTest1 {

    public static void main(String... args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-main");
        EntityManager em = emf.createEntityManager();

        Car car = new Car();
        Person person = new Person();
        person.setName("Ivan");
        car.setPerson(person);//propietario

        //tx1
        System.out.println("primera tx");
        em.getTransaction().begin();

        em.persist(car);

        em.getTransaction().commit();

        //tx2
        System.out.println("segunda tx");
        em.getTransaction().begin();

        person = new Person();
        person.setName("Juana");
        car.setPerson(person);

        em.merge(car);

        em.getTransaction().commit();

        //query
        Car carResponse = em.find(Car.class, 1L);
        System.out.println(carResponse);

        em.close();
        emf.close();
    }

}
