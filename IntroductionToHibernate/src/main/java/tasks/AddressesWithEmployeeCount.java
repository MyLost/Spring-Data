package tasks;

import entities.Employee;
import utils.Constants;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class AddressesWithEmployeeCount {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<Employee> query;
        query = em.createQuery(Constants.SELECT_E_FROM_EMPLOYEE_E_GROUP_BY_E_ADDRESS_ID_ORDER_BY_COUNT_E_ID_DESC, Employee.class);
        query.setMaxResults(10);
        List<Employee> resultList;
        resultList = query.getResultList();

        resultList.forEach(employee -> {
            System.out.printf((Constants.S_S_D) + "%n", employee.getAddress().getText(), employee.getAddress().getTown().getName(), employee.getAddress().getEmployees().size());
        });

        em.getTransaction().commit();
        em.close();
    }
}