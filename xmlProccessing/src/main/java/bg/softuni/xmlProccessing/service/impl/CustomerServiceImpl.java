package bg.softuni.xmlProccessing.service.impl;

import bg.softuni.xmlProccessing.domain.dtos.exportDto.CustomerExportDto;
import bg.softuni.xmlProccessing.domain.dtos.exportDto.CustomerExportWrapperDto;
import bg.softuni.xmlProccessing.domain.dtos.exportDto.CustomerWithSalesWrapperDto;
import bg.softuni.xmlProccessing.domain.entities.Customer;
import bg.softuni.xmlProccessing.repositories.CustomerRepository;
import bg.softuni.xmlProccessing.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final Random random;
    private final ModelMapper mapper;

    @Override public long count() {
        return customerRepository.count();
    }

    @Override public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override public Customer getRandomCustomer() {
        long index = random.nextLong(customerRepository.count()) + 1L;
        return customerRepository.findById(index).orElseThrow(RuntimeException::new);
    }

    @Override public CustomerExportWrapperDto findAllOrderedByBirthDateAndYoung() {
        CustomerExportWrapperDto toReturn = new CustomerExportWrapperDto();
        customerRepository
                .findAllByOrderByBirthDateAscIsYoungDriverDesc()
                .stream()
                .map(c -> mapper.map(c, CustomerExportDto.class))
                .forEach(s -> toReturn.getCustomers().add(s));
        return toReturn;
    }

    @Override public CustomerWithSalesWrapperDto getTotalSalesByCustomer() {
        CustomerWithSalesWrapperDto toReturn = new CustomerWithSalesWrapperDto();
        customerRepository
                .findCustomerWithSales()
                .forEach(s -> toReturn.getCustomers().add(s));
        return toReturn;
    }
}