package bg.softuni.xmlProccessing.service;

import bg.softuni.xmlProccessing.domain.dtos.exportDto.CustomerExportWrapperDto;
import bg.softuni.xmlProccessing.domain.dtos.exportDto.CustomerWithSalesWrapperDto;
import bg.softuni.xmlProccessing.domain.entities.Customer;

public interface CustomerService {
    long count();

    void save(Customer customer);

    Customer getRandomCustomer();

    CustomerExportWrapperDto findAllOrderedByBirthDateAndYoung();

    CustomerWithSalesWrapperDto getTotalSalesByCustomer();
}