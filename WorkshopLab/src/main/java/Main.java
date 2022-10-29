import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/*
    Custom ORM with basic functionality (insert, update and retrieve single or set of objects). It will have options to
    work with already created tables in a database or create new tables if such are not present yet.

    Code show basic connection with database and basic orm functionality. Code can be extended for completeness!
 */
public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {

        User user = new User("Qisho", 14, LocalDate.of(2017, 6, 20));

        System.out.println(user);

        MyConnector.createConection();
        EntityManager entityManager = new EntityManager(MyConnector.getConnection());

        entityManager.persist(user);

        List<User> users = (List<User>) entityManager.find(User.class, "age >= 18 and extract(year from registration) > 2020");

        if(users != null) {
            System.out.println("Founded users are " + users.size());
            users.forEach(elem -> System.out.println(elem));
        }
    }
}