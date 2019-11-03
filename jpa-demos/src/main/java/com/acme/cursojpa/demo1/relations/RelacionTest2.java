package com.acme.cursojpa.demo1.relations;

import com.acme.cursojpa.demo1.domain.Car;
import com.acme.cursojpa.demo1.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Ivan on 22/09/2018.
 */
public class RelacionTest2 {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-main");
    static EntityManager em = emf.createEntityManager();

    public static void main(String... arrgs) {
        init();

        em.getTransaction().begin();

        Car suzuki = em.find(Car.class, 2L);

        System.out.println(suzuki);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }

    private static void init() {
        Person ivan = new Person();
        ivan.setCode("46567850");
        ivan.setName("Ivan Luis");

        Car car = new Car();
        car.setModelo("suzuki");
        car.setPerson(ivan);//a la hora de persistir se considera esta referencia

        Person juan = new Person();
        juan.setCode("46577851");
        juan.setName("Juan Marcelo");

        Car car2 = new Car();
        car2.setModelo("audi");
        car2.setPerson(juan);

        em.getTransaction().begin();

        em.persist(juan);
        em.persist(ivan);
        em.persist(car);
        em.persist(car2);

        // actualiza entidades con objetos de bd
        em.refresh(juan);
        em.refresh(ivan);
        em.refresh(car);
        em.refresh(car2);

        em.getTransaction().commit();
    }

}
