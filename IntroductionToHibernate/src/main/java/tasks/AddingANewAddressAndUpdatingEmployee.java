package tasks;

import entities.Address;
import entities.Employee;
import entities.Town;
import utils.Constants;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class AddingANewAddressAndUpdatingEmployee {



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        Address address = new Address();
        address.setText(Constants.VITOSHKA_15);

        em.persist(address);

        System.out.println(Constants.PLEASE_ENTER_EMPLOYEE_LAST_NAME_TO_WHOM_ASSIGNED_NEW_ADDRESS);
        String employeeLastName = scanner.nextLine();

        TypedQuery<Employee> query = em.createQuery(Constants.SELECT_E_FROM_EMPLOYEE_E_WHERE_E_LAST_NAME_EMPLOYEE_LAST_NAME, Employee.class);
        query.setParameter(Constants.EMPLOYEE_LAST_NAME, employeeLastName);
        List<Employee> resultList = query.getResultList();

        if(resultList.size() > 0) {
           resultList.stream().forEach(employee -> {
               employee.setAddress(address);
           });
            System.out.println(String.format(Constants.D_EMPLOYEES_ARE_AFFECTED, resultList.size()));
        }

        em.getTransaction().commit();
        em.close();
    }
}
