package org.example;

import db.Connection;
import entities.gringotts.GringottDepositsEntity;
import entities.gringotts.GringottsEntity;
import entities.gringotts.MagicWandEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

/*
    Project use project lombok for generating getter, setters and constructors!
    Entities for each task are in different package!
    Entity manager is obtained from local class named Connection!
 */

public class Main {
    public static void main(String[] args) {

        EntityManager entityManager = new Connection("example-gringotts").getEntityManager();

        entityManager.getTransaction().begin();

        GringottsEntity gringottOne = new GringottsEntity();
        gringottOne.setAge(20);
        gringottOne.setFirstName("Gringo");
        gringottOne.setLastName("Gringov");
        gringottOne.setNotes("This is some awesome gringott!");

        MagicWandEntity magicWandForGringottOne = new MagicWandEntity();
        magicWandForGringottOne.setMagicWandCreator("Alex");
        magicWandForGringottOne.setMagicWandSize(35);

        entityManager.persist(magicWandForGringottOne);

        gringottOne.setMagicWand(magicWandForGringottOne);
        entityManager.persist(gringottOne);

        GringottDepositsEntity gringottOneDeposit = new GringottDepositsEntity();
        gringottOneDeposit.setDepositAmount(500.00);
        gringottOneDeposit.setDepositCharge(300.00);
        gringottOneDeposit.setGringott(gringottOne);
        gringottOneDeposit.setDepositGroup("Gringotts");
        gringottOneDeposit.setIsDepositExpired(false);
        gringottOneDeposit.setDepositInterest(100.00);
        gringottOneDeposit.setDepositExpirationDate(LocalDate.of(2002,12,31));
        gringottOneDeposit.setDepositStartDate(LocalDate.now());

        entityManager.persist(gringottOneDeposit);

        GringottDepositsEntity gringottOneSecondDeposit = new GringottDepositsEntity();
        gringottOneSecondDeposit.setDepositAmount(400.00);
        gringottOneSecondDeposit.setDepositCharge(200.00);
        gringottOneSecondDeposit.setGringott(gringottOne);
        gringottOneSecondDeposit.setDepositGroup("Gringotts");
        gringottOneSecondDeposit.setIsDepositExpired(false);
        gringottOneSecondDeposit.setDepositInterest(50.00);
        gringottOneSecondDeposit.setDepositExpirationDate(LocalDate.of(2002,12,31));
        gringottOneSecondDeposit.setDepositStartDate(LocalDate.now());

        entityManager.persist(gringottOneSecondDeposit);

        GringottDepositsEntity gringottOneThirdDeposit = new GringottDepositsEntity();
        gringottOneThirdDeposit.setDepositAmount(700.00);
        gringottOneThirdDeposit.setDepositCharge(400.00);
        gringottOneThirdDeposit.setGringott(gringottOne);
        gringottOneThirdDeposit.setDepositGroup("Gringotts");
        gringottOneThirdDeposit.setIsDepositExpired(false);
        gringottOneThirdDeposit.setDepositInterest(60.00);
        gringottOneThirdDeposit.setDepositExpirationDate(LocalDate.of(2002,12,31));
        gringottOneThirdDeposit.setDepositStartDate(LocalDate.now());

        entityManager.persist(gringottOneThirdDeposit);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}