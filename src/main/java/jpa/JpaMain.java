package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by bjacobs on 4/24/16.
 */
public class JpaMain {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("jpa");

    private EntityManager entityManager;

    JpaMain() {
        entityManager = FACTORY.createEntityManager();
    }

    public static void main(String[] args) {
        JpaMain jpa = new JpaMain();

        jpa.getAll().forEach(System.out::println);

        System.out.println(jpa.getById(1));

        Person p = new Person();
        p.setFirst("jack");
        p.setLast("johnson");

        System.out.println(jpa.create(p));

        jpa.close();

    }

    public Person getById(Integer id) {
        return entityManager.find(Person.class, id);
    }

    public List<Person> getAll(){
        return entityManager.createQuery("from Person").getResultList();
    }

    public Person create(Person person){

        entityManager.persist(person);

        return person;

    }

    public void close(){

        entityManager.close();

        FACTORY.close();
    }
}
