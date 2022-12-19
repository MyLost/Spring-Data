package bg.softuni.xmlProccessing.service.impl;

import bg.softuni.xmlProccessing.domain.dtos.exportDto.SaleDetailsWrapperDto;
import bg.softuni.xmlProccessing.domain.entities.Car;
import bg.softuni.xmlProccessing.domain.entities.Customer;
import bg.softuni.xmlProccessing.domain.entities.Sale;
import bg.softuni.xmlProccessing.repositories.SaleRepository;
import bg.softuni.xmlProccessing.service.CustomerService;
import bg.softuni.xmlProccessing.service.SaleService;
import bg.softuni.xmlProccessing.util.CustomMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final CustomerService customerService;
    private final ModelMapper mapper;


    @Override public void save(Car car, Customer customer, Double discount) {
        Sale sale = new Sale(car, customer, discount);
        customer.getSales().add(sale);
        customerService.save(customer);
        saleRepository.save(sale);
    }

    @Transactional
    @Override public SaleDetailsWrapperDto getSalesWithDiscount() {
        SaleDetailsWrapperDto toReturn = new SaleDetailsWrapperDto();
        saleRepository
                .findAll()
                .stream()
                .map(CustomMapper::saleToSaleDetails)
                .forEach(s -> toReturn.getSales().add(s));
        return toReturn;
    }

    @Override public long count() {
        return saleRepository.count();
    }
}