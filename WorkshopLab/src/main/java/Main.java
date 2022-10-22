import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException {

        User user = new User("dimitar", 35, LocalDate.now());

        System.out.println(user);

        MyConnector.createConection("midas", "king@midas12", "Custom_ORM");
        EntityManager entityManager = new EntityManager(MyConnector.getConnection());

        entityManager.persist(user);
    }
}