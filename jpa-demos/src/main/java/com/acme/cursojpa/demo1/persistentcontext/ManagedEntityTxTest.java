package com.acme.cursojpa.demo1.persistentcontext;

import com.acme.cursojpa.demo1.domain.Car;
import com.acme.cursojpa.demo1.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Ivan on 22/09/2018.
 */
public class ManagedEntityTxTest {

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

        //tx4. La modificacion de la entidad se produce fuera de la tx
        // la modificacion de la entidad se ha producido mientras el em esta activo -> em conoce la modificacion de entidad
        System.out.println("tx4");
        Person person3 = em.find(Person.class, 1l);
        person3.setCode("46567852");

        em.getTransaction().begin();
        em.getTransaction().commit();

        em.close();
        emf.close();
    }

}
