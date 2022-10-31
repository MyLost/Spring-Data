import entities.Town;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<Town> query = em.createQuery("SELECT t FROM Town t where length(t.name) > :maxNameLength", Town.class);
        query.setParameter("maxNameLength", 5);
        List<Town> resultList = query.getResultList();
        System.out.println(resultList.size());


        Query updateQuery = em.createQuery("UPDATE Town t SET t.name = upper(t.name) where length(t.name) > 5");
        int result = updateQuery.executeUpdate();

        System.out.println(result);

        em.getTransaction().commit();

    }
}
