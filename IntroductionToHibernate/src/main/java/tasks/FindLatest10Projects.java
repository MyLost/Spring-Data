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

public class FindLatest10Projects {


    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<Project> query = em.createQuery(Constants.SELECT_P_FROM_PROJECT_P_ORDER_BY_P_START_DATE_DESC, Project.class);
        query.setMaxResults(10);
        List<Project> resultList = query.getResultList();

            resultList.stream().forEach(project -> {
            System.out.println(String.format(Constants.PROJECT_NAME_S_N_PROJECT_DESCRIPTION_S_N_PROJECT_START_DATE_S_N_PROJECT_END_DATE_S,
                    project.getName(), project.getDescription(), project.getStartDate(), project.getEndDate()));
        });

            em.getTransaction().commit();
            em.close();
    }
}
