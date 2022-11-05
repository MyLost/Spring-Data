package tasks;

import entities.Employee;
import utils.Constants;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class EmployeesWithSalaryOver50000 {



    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<String> query = em.createQuery(Constants.SELECT_E_FIRST_NAME_FROM_EMPLOYEE_E_WHERE_E_SALARY_SALARY, String.class);
        query.setParameter(Constants.SALARY, BigDecimal.valueOf(50000));

        List<String> resultList = query.getResultList();

        resultList.forEach(System.out::println);

        em.getTransaction().commit();
        em.close();

    }
}
