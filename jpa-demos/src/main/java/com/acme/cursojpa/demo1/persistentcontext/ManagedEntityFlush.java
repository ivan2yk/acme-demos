package com.acme.cursojpa.demo1.persistentcontext;

import com.acme.cursojpa.demo1.domain.Car;
import com.acme.cursojpa.demo1.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Ivan on 22/09/2018.
 */
public class ManagedEntityFlush {

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

        em.getTransaction().commit();

        //tx2
        System.out.println("tx2");
        em.getTransaction().begin();

        Person person1 = em.find(Person.class, 1L);
        person1.setCode("46567850");//incluso si no se hace un merge - la entidad se actualiza

        em.getTransaction().commit();

        //tx3
        System.out.println("tx3");

        Person person2 = em.find(Person.class, 1l);
        person2.setCode("46567851");

        em.flush();//flush explicito. will produce TransactionRequiredException: no transaction is in progress
        em.close();
        emf.close();
    }

}
