package com.acme.cursojpa.demo1.persistentcontext;

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
public class FlushOrderTest2 {

    public static void main(String... args) {
        caso1();
        caso2();
    }

    /*
    - Las operaciones no se ejecutan en el mismo orden en que se muestran en el codigo.
    - Jpa - orden de propagacion de cambios: primero ejecuta las insert luego update y finalmente los delete.
    - em.getTransaction().commit(); al llegar a dicha linea se produce el flush.
     */
    private static void caso1() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-main");
        loadData();
        EntityManager em = emf.createEntityManager();

        System.out.println("--- caso 1 ---");

        //tx1
        System.out.println("tx1");
        em.getTransaction().begin();

        Person person1 = em.find(Person.class, 1L);
        System.out.println(person1);

        em.remove(person1);//delete

        em.persist(new Person());//insert

        Person juana = em.find(Person.class, 2L);
        juana.setPadre(null);
        juana.setName("juana luisa");//update

        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    private static void caso2() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-main");
        loadData();
        EntityManager em = emf.createEntityManager();

        System.out.println("--- caso 2 ---");

        //tx1
        System.out.println("tx1");
        em.getTransaction().begin();

        Person person1 = em.find(Person.class, 6L);
        System.out.println(person1);

        em.remove(person1);//delete
        em.flush();
        em.persist(new Person());//insert

        Person juana = em.find(Person.class, 2L);
        juana.setPadre(null);
        juana.setName("juana luisa");//update

        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    private static void loadData() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:h2:mem:test");
            conn.createStatement().executeUpdate("delete from PERSON");
            conn.commit();
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

}
