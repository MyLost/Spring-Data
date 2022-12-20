package bg.softuni.xmlProccessing.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface SeedService {
    default void seedAll() throws IOException, JAXBException {
        seedSuppliers();
        seedParts();
        seedCars();
        seedCustomers();
        seedSales();
    }

    void seedSales();

    void seedCars() throws IOException, JAXBException;

    void seedParts() throws IOException, JAXBException;

    void seedSuppliers() throws IOException, JAXBException;

    void seedCustomers() throws IOException, JAXBException;
}