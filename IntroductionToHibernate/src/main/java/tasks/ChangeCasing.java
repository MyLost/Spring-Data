package tasks;

import entities.Town;
import utils.Constants;

import javax.persistence.*;
import java.util.List;

public class ChangeCasing {


    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<Town> query = em.createQuery(Constants.SELECT_T_FROM_TOWN_T_WHERE_LENGTH_T_NAME_MAX_NAME_LENGTH, Town.class);
        query.setParameter(Constants.MAX_NAME_LENGTH, 5);
        List<Town> resultList = query.getResultList();
        System.out.println(resultList.size());


        Query updateQuery = em.createQuery(Constants.UPDATE_TOWN_T_SET_T_NAME_UPPER_T_NAME_WHERE_LENGTH_T_NAME_5);
        int result = updateQuery.executeUpdate();

        System.out.println(result);

        em.getTransaction().commit();

    }
}
