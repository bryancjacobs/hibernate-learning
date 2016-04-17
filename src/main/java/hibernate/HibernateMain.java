package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by bjacobs on 4/9/16.
 */
public class HibernateMain {

    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    private Session session;

    public HibernateMain() {
        session = factory.openSession();
    }

    public static void main(String[] args) {
        HibernateMain main = new HibernateMain();

        System.out.println("getAll()");
        main.getAll().forEach(System.out::println);

        System.out.println("\ngetById(3)");
        System.out.println(main.getById(3));


        System.out.println("create(person)");
        Person person = new Person();
        person.setFirst("bob");
        person.setLast("johnson");
        System.out.println(main.create(person));

        main.close();
    }

    public Person getById(Integer id) {
        return session.get(Person.class, id);
    }

    public List<Person> getAll() {
        return (List<Person>) session.createQuery("from Person").list();
    }

    public Person create(Person person) {
        Integer id = (Integer) session.save(person);
        person.setId(id);
        return person;
    }

    public void close() {

        session.close();

        factory.close();
    }

}
