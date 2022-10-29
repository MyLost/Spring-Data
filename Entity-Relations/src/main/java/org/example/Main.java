package org.example;

import entityes.Bike;
import entityes.Car;
import entityes.Plane;
import entityes.Vehicle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-relations");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Vehicle car = new Car("Ford Mustang", "Petrol", 2);
        Vehicle bike = new Bike();
        Vehicle plane = new Plane("Airbus","Petrol", 200);


        entityManager.persist(car);
        entityManager.persist(bike);
        entityManager.persist(plane);

        entityManager.getTransaction().commit();

        entityManager.close();

    }
}