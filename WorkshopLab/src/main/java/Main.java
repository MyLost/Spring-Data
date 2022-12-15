import entities.Student;
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

//        User user = new User("Eisho", 14, LocalDate.of(2017, 6, 20));
//
//        System.out.println(user);
//
        MyConnector.createConection();
        EntityManager entityManager = new EntityManager(MyConnector.getConnection());

        Student ivan = new Student("Ivan", "Roman", 25, LocalDate.of(2022,2, 26));
        Student mike = new Student("Mike", "Atanasov", 45, LocalDate.of(2021,9, 6));
        Student dragan = new Student("Dragan", "Verner", 18, LocalDate.of(2000,2, 16));
        Student pesho = new Student("Pesho", "Alisov", 30, LocalDate.of(2006,7, 14));

        entityManager.persist(ivan);
        entityManager.persist(mike);
        entityManager.persist(dragan);
        entityManager.persist(pesho);

        List<User> studentsBeforeRemove = (List<User>) entityManager.find(Student.class);
        System.out.println("Student size " + studentsBeforeRemove.size());
        entityManager.delete(Student.class, 2);
        List<User> studentsAfterRemove = (List<User>) entityManager.find(Student.class);
        System.out.println("Student size " + studentsAfterRemove.size());
//
//        entityManager.doCreate(Student.class);
//
//        entityManager.doAlter(Student.class);
//
//        entityManager.persist(user);
//
//        List<User> users = (List<User>) entityManager.find(User.class, "age >= 18 and extract(year from registration) > 2020");
//
//        if(users != null) {
//            System.out.println("Founded users are " + users.size());
//            users.forEach(elem -> System.out.println(elem));
//        }


    }
}