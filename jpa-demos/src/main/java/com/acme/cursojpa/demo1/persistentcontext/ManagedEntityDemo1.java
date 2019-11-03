package com.acme.cursojpa.demo1.persistentcontext;

import com.acme.cursojpa.demo1.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ManagedEntityDemo1 {

    /**
     * Asociado al entity manager existe un 'persistent context' (agrupacion de entidades
     * gestionadas por el em).
     * Una entidad al estar en persistent context se encuentra en estado Managed. Las modificaciones hechas
     * sobre un objecto managed se sincronizan con la bd.
     *
     * @param args
     */
    public static void main(String... args) {
        System.out.println("Hello JPA");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-main");

        loadData();

        EntityManager em = emf.createEntityManager();

        System.out.println("============================================== query");
        em.getTransaction().begin();

        Person person1 = em.find(Person.class, 1L);
        person1.setName("Ivan Luis");

        em.getTransaction().commit();//se produce flush

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
