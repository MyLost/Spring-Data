package tasks;

import entities.Employee;
import utils.Constants;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class FindEmployeesByFirstName {



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        System.out.println(Constants.PLEASE_ENTER_PATTERN_FOR_SEARCH_IN_EMPLOYEE_FOR_FIRSTNAME_START_WITH_THIS_PATTERN);
        String pattern = scanner.nextLine();

        em.getTransaction().begin();

        em.createQuery(Constants.UPDATE_EMPLOYEE_E_SET_E_SALARY_E_SALARY_10_WHERE_E_FIRST_NAME_LIKE_CODE)
                .setParameter(Constants.CODE, pattern + Constants.PERCENT)
                .executeUpdate();

        TypedQuery<Employee> querySelect = em.createQuery(Constants.SELECT_E_FROM_EMPLOYEE_E_WHERE_E_FIRST_NAME_LIKE_CODE_ORDER_BY_E_ID, Employee.class);
        querySelect.setParameter(Constants.CODE, pattern + Constants.PERCENT);

        List<Employee> resultList = querySelect.getResultList();

        resultList.stream().forEach(employee -> {
            System.out.println(String.format(Constants.S_S_S_2_F, employee.getFirstName(), employee.getLastName(), employee.getJobTitle() ,employee.getSalary()));
        });

        em.getTransaction().commit();
        em.close();
    }
}
