package tasks;

import entities.Employee;
import utils.Constants;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class ContainsEmployee {



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        System.out.println(Constants.PLEASE_ENTER_NAME_I_WILL_CHECK_THAT_NAME_IN_DATABASE_AND_GIVE_YOU_ANSWER_IF_THAT_USER_EXIST_OR_NOT);
        String name = scanner.nextLine();

        em.getTransaction().begin();

        TypedQuery<Employee> query = em.createQuery(Constants.SELECT_E_FROM_EMPLOYEE_E_WHERE_CONCAT_WS_E_FIRST_NAME_E_LAST_NAME_NAME, Employee.class);
        query.setParameter(Constants.NAME, name);
        List<Employee> resultList = query.getResultList();

        if(resultList.size() > 0) {
            System.out.println(Constants.YES);
        } else {
            System.out.println(Constants.NO);
        }

        em.getTransaction().commit();
        em.close();

        System.out.println(Constants.HAVE_A_NICE_DAY);

    }
}
