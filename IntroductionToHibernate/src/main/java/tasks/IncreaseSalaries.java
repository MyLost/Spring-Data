package tasks;

import entities.Employee;
import entities.Project;
import utils.Constants;

import javax.persistence.*;
import java.util.List;

public class IncreaseSalaries {



    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

       em.createQuery(Constants.UPDATE_EMPLOYEE_E_SET_E_SALARY_E_SALARY_1_12_WHERE_E_DEPARTMENT_ID_IN_1_2_14_3)
              .executeUpdate();

        TypedQuery<Employee> querySelect = em.createQuery(Constants.SELECT_QUERY, Employee.class);

        List<Employee> resultList = querySelect.getResultList();

        resultList.stream().forEach(employee -> {
            System.out.println(String.format(Constants.S_S_2_F, employee.getFirstName(), employee.getLastName(), employee.getSalary()));
        });

        em.getTransaction().commit();
        em.close();
    }
}
