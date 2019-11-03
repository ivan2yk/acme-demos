package com.acme.cursojpa.demo1;

import com.acme.cursojpa.demo1.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by Ivan on 22/09/2018.
 */
public class LazyLoadingGetReferenceDemo {

    public static void main(String... args) {
        System.out.println("Hello JPA");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-main");

        loadData();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        System.out.println("--- get reference consulta ---");

        Person person = em.getReference(Person.class, 1L);

        System.out.println("--- accessing to id ---");
        System.out.println("ID: " + person.getId());

        System.out.println("--- accessing to name ---");
        System.out.println("Name: " + person.getName());//ejecuta la consulta

        System.out.println("Object: " + person.getClass().getName());//object it not exactly Person type but subtype
        System.out.println("Object instanceof Persona: " + (person instanceof Person));

        em.getTransaction().commit();
        em.close();

        emf.close();//cierra el pool de conexiones y permite finalizar el proceso
    }

    private static void loadData() {

        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:test")) {
            try (Statement statement = conn.createStatement()) {
                statement.executeUpdate("insert into person (id, code, name) values (1, '001', 'Ivan')");
            }
            conn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
