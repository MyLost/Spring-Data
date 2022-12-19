package bg.softuni.xmlProccessing.service;

import bg.softuni.xmlProccessing.domain.dtos.exportDto.SaleDetailsWrapperDto;
import bg.softuni.xmlProccessing.domain.entities.Car;
import bg.softuni.xmlProccessing.domain.entities.Customer;

public interface SaleService {

    void save(Car car, Customer customer, Double discount);

    SaleDetailsWrapperDto getSalesWithDiscount();

    long count();
}