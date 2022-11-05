package tasks;

import entities.Employee;
import utils.Constants;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeesMaximumSalaries {



    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        em.createQuery(Constants.UPDATE_EMPLOYEE_E_SET_E_SALARY_E_SALARY_10)
                .executeUpdate();

        TypedQuery<Employee> querySelect = em.createQuery(Constants.SELECT_E_FROM_EMPLOYEE_E_GROUP_BY_E_DEPARTMENT_ID_HAVING_MAX_E_SALARY_NOT_BETWEEN_30000_AND_70000, Employee.class);

        List<Employee> resultList = querySelect.getResultList();

        resultList.stream().forEach(employee -> {
            System.out.println(String.format(Constants.S_2_F, employee.getDepartment().getName(), employee.getSalary()));
        });

        em.getTransaction().commit();
        em.close();
    }

}
