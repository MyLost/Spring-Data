package db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Connection {

    private  EntityManagerFactory entityManagerFactory;
    private  EntityManager entityManager;

    public Connection(String persistenceUnit) {
        entityManagerFactory  = Persistence.createEntityManagerFactory(persistenceUnit);
        entityManager  = entityManagerFactory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        if(entityManager != null) {
            return entityManager;
        }
        throw new RuntimeException("Call connection with specific unit!");
    }
}
