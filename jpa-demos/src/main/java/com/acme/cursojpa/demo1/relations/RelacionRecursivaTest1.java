package com.acme.cursojpa.demo1.relations;

import com.acme.cursojpa.demo1.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Ivan on 22/09/2018.
 */
public class RelacionRecursivaTest1 {

    public static void loadData() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:h2:mem:test");
            conn.createStatement().executeUpdate("insert into PERSON (code, name, padre_id) values ('70819090', 'Nicolas', null)");
            conn.createStatement().executeUpdate("insert into PERSON (code, name, padre_id) values ('50607800', 'Fernando', 1)");
            conn.createStatement().executeUpdate("insert into PERSON (code, name, padre_id) values ('46567850', 'Ivan', 2)");
            conn.createStatement().executeUpdate("insert into PERSON (code, name, padre_id) values ('20343440', 'hijo', 3)");
            conn.createStatement().executeUpdate("insert into PERSON (code, name, padre_id) values ('20343442', 'nieto', 4)");
            conn.createStatement().executeUpdate("insert into PERSON (code, name, padre_id) values ('20343443', 'bisnieto', 5)");
            conn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String... arrgs) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-main");
        loadData();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Person person = em.find(Person.class, 1L);

        em.getTransaction().commit();

        while (person != null) {
            System.out.println(person);
            person = person.getPadre();
        }

        em.close();
        emf.close();
    }

}
