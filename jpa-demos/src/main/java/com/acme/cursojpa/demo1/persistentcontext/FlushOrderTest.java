package com.acme.cursojpa.demo1.persistentcontext;

import com.acme.cursojpa.demo1.domain.Car;
import com.acme.cursojpa.demo1.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Ivan on 22/09/2018.
 */
public class FlushOrderTest {

    public static void main(String... args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-main");
        EntityManager em = emf.createEntityManager();

        Car car = new Car();
        Person person = new Person();
        person.setName("Ivan");
        car.setPerson(person);//propietario

        //tx1
        System.out.println("tx1");
        em.getTransaction().begin();

        em.persist(car);
        em.persist(new Person());
        em.persist(new Person());
        em.persist(new Person());
        em.persist(new Person());
        em.persist(new Person());
        em.persist(new Person());
        em.getTransaction().commit();

        //tx2
        System.out.println("tx2");
        em.getTransaction().begin();

        Person person1 = em.find(Person.class, 1l);
        System.out.println(person1);

        em.remove(person1);
        em.flush();

        em.persist(new Person());

        Person juana = em.find(Person.class, 2L);
        juana.setName("juana luisa");

        em.getTransaction().commit();

        em.close();
        emf.close();
    }

}
