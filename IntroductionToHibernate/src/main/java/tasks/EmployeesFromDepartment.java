package tasks;

import entities.Employee;
import utils.Constants;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

public class EmployeesFromDepartment {



    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<Employee> query = em.createQuery(Constants.SELECT_E_FROM_EMPLOYEE_E_WHERE_E_DEPARTMENT_NAME_RESEARCH_AND_DEVELOPMENT_ORDER_BY_E_SALARY_E_ID, Employee.class);

        List<Employee> resultList = query.getResultList();

        resultList.forEach(item ->
                System.out.println(String.format(Constants.S_S_FROM_S_$_2_F, item.getFirstName(), item.getLastName(), item.getDepartment().getName(), item.getSalary())));

        em.getTransaction().commit();
        em.close();
    }
}