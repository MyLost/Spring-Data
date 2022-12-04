package tasks;

import entities.Employee;
import entities.Project;
import utils.Constants;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GetEmployeeWithProject {



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        System.out.println(Constants.PLEASE_ENTER_EMPLOYEE_ID);
        Integer employeeId = Integer.parseInt(scanner.nextLine());

        TypedQuery<Employee> query = em.createQuery(Constants.SELECT_E_FROM_EMPLOYEE_E_WHERE_E_ID_ID, Employee.class);
        query.setParameter(Constants.GetEmployeeWithProject_ID, employeeId);
        List<Employee> resultList = query.getResultList();

        resultList.stream().forEach(employee -> {
            System.out.println(String.format(Constants.S_S_S_N_S, employee.getFirstName(), employee.getLastName(), employee.getJobTitle(),
                    employee.getProjects().stream().sorted(Comparator.comparing(Project::getName)).map(Project::getName).collect(Collectors.joining(System.lineSeparator()))));
        });

        em.getTransaction().commit();
        em.close();
    }
}
