package tasks;

import entities.Employee;
import entities.Town;
import entities.Address;
import utils.Constants;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.List;
import java.util.Scanner;


public class RemoveTowns {



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        System.out.println(Constants.PLEASE_ENTER_TOWN_NAME);
        String townName = scanner.nextLine();

        em.getTransaction().begin();

        TypedQuery<Town> townQuery = em.createQuery(Constants.SELECT_T_FROM_TOWN_T_WHERE_T_NAME_TOWN_NAME, Town.class)
                .setParameter(Constants.TOWN_NAME, townName);

        Town town = townQuery.getSingleResult();

        if(town != null) {

            TypedQuery<Address> addressQuery = em.createQuery(Constants.SELECT_A_FROM_ADDRESS_A_WHERE_A_TOWN_ID_ID, Address.class)
                    .setParameter(Constants.RemoveTowns_ID, town.getId());

            List<Address> address = addressQuery.getResultList();

            address.stream().forEach(address1 -> {
                Integer numberOfEmployees = em.createQuery(Constants.UPDATE_EMPLOYEE_E_SET_E_ADDRESS_ID_NULL_WHERE_E_ADDRESS_ID_ADDRESS_ID)
                        .setParameter(Constants.ADDRESS_ID, address1.getId())
                        .executeUpdate();
            });

            Integer numberOfDeletedAddresses = em.createQuery(Constants.DELETE_ADDRESS_A_WHERE_A_TOWN_ID_ID)
                    .setParameter(Constants.ID1, town.getId())
                    .executeUpdate();

            System.out.println(String.format(Constants.D_ADDRESS_IN_S_DELETED, numberOfDeletedAddresses, townName));

        } else {

            System.out.println(String.format(Constants.TOWN_WITH_NAME_S_NOT_FOUND, townName));
        }

        em.getTransaction().commit();
        em.close();
    }
}
