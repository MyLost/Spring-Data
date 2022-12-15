package org.example;

import entityes.Bike;
import entityes.Car;
import entityes.Plane;
import entityes.Vehicle;
import entityes.relations.Article;
import entityes.relations.PlateNumber;
import entityes.relations.Truck;
import entityes.relations.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/*
    This is code from lab. Code is not full and can extend it for completeness.
    ***************************************************************************
    Relations
    ##################################
    One to One
    One to Many and Many to One
    Many to Many
    Self Referencing
    ##################################
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-relations");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Vehicle car = new Car("Ford Mustang", "Petrol", 2);
        Vehicle bike = new Bike();
        Vehicle plane = new Plane("Airbus","Petrol", 200);

        PlateNumber number = new PlateNumber("HGT-94356");
        PlateNumber number2 = new PlateNumber("GGT-94356");

        Truck truck1 = new Truck(number);
        Truck truck2 = new Truck(number2);

        entityManager.persist(number);
        entityManager.persist(number2);
        entityManager.persist(truck1);
        entityManager.persist(truck2);

        entityManager.persist(car);
        entityManager.persist(bike);
        entityManager.persist(plane);

        Article article = new Article("Some awesome article");
        User user = new User("Mike");
        user.addArticle(article);
        article.setAuthor(user);

        entityManager.persist(user);

//        Car carFromDb = entityManager.find(Car.class, 1L);
//
//        System.out.println("Model -> " + carFromDb.getModel());

        entityManager.getTransaction().commit();

        entityManager.close();

    }
}