package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjacobs on 4/9/16.
 */
public class JdbcMain {

    private static final String DB_URL = "jdbc:postgresql://localhost/postgres";

    private static final String USER = "bjacobs";

    private static final String PASS = null;

    Connection conn;

    Statement stmt;

    public JdbcMain() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
    }

    public static void main(String[] args) throws SQLException {


        JdbcMain main = new JdbcMain();

        System.out.println("getAll()");
        main.getAll().forEach(System.out::println);

        System.out.println();
        System.out.println("getById(3)");
        System.out.println(main.getById(3));

        main.close();
    }


    public List<Person> getAll() throws SQLException {
        String sql = "SELECT id, first, last FROM person";

        ResultSet rs = stmt.executeQuery(sql);

        List<Person> persons = new ArrayList<>();

        while (rs.next()) {
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setFirst(rs.getString("first"));
            person.setLast(rs.getString("last"));

            persons.add(person);
        }

        rs.close();

        return persons;
    }

    public Person getById(Integer id) throws SQLException {

        String sql = "select id,first,last from person where id  = " +  id;

        sql = "select id,first,last from person where id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        Person person = new Person();

        while (rs.next()) {
            person.setId(rs.getInt("id"));
            person.setFirst(rs.getString("first"));
            person.setLast(rs.getString("last"));
        }

        rs.close();
        ps.close();

        return person;
    }

    public Person create(Person person) {
        return null;
    }

    public void close() throws SQLException {
        if (conn != null) conn.close();

        if(stmt != null) stmt.close();
    }
}
