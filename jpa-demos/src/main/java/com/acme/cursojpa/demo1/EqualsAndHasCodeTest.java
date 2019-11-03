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
public class EqualsAndHasCodeTest {

    public static void main(String... args) {
        System.out.println("Hello JPA");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-main");

        loadData();

        EntityManager em = emf.createEntityManager();

        System.out.println("============================================== query");
        em.getTransaction().begin();

        Person person1 = em.getReference(Person.class, 1L);
        Person person2 = em.getReference(Person.class, 2L);
        Person person3 = em.getReference(Person.class, 3L);

        System.out.println("person 1 == person2 ?: " + (person1.equals(person2)));
        System.out.println("person 1 == person3 ?: " + (person1.equals(person3)));

        em.getTransaction().commit();
        em.close();
        emf.close();//cierra el pool de conexiones y permite finalizar el proceso
    }

    private static void loadData() {

        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:test")) {
            try (Statement statement = conn.createStatement()) {
                statement.executeUpdate("insert into person (id, code, name) values (1, '46567850', 'Ivan')");
                statement.executeUpdate("insert into person (id, code, name) values (2, '46567850', 'Jose')");
                statement.executeUpdate("insert into person (id, code, name) values (3, '46567851', 'Carlos')");
            }
            conn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
