package com.acme.cursojpa.demo1.relations;

import com.acme.cursojpa.demo1.domain.Car;
import com.acme.cursojpa.demo1.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Ivan on 22/09/2018.
 */
public class RelacionTest1 {

    public static void main(String... arrgs) {
        Person ivan = new Person();
        ivan.setCode("46567850");
        ivan.setName("Ivan Luis");

        Car car = new Car();
        car.setModelo("suzuki");
        car.setPerson(ivan);//a la hora de persistir se considera esta referencia

        Person juan = new Person();
        juan.setCode("46577851");
        juan.setName("Juan Marcelo");
        juan.getCars().add(car);//a la hora de persistir se ignora esta referencia

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-main");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(juan);
        em.persist(ivan);
        em.persist(car);

        // actualiza entidades con objetos de bd
        em.refresh(car);
        em.refresh(juan);
        em.refresh(ivan);

        em.getTransaction().commit();

        System.out.println("-- Propietario suzuki: " + car.getPerson().getName());
        System.out.println("-- Cuantos carros tiene Juan?: " + juan.getCars().size());
        System.out.println("-- Cuantos carros tiene Ivan?: " + ivan.getCars().size());

        System.out.println("Find test-------------------------------");

        Car suzuki = em.find(Car.class, 1L);

        System.out.println(suzuki);

        em.close();
        emf.close();
    }

}
