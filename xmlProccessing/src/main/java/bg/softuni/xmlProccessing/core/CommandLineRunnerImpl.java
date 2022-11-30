package bg.softuni.xmlProccessing.core;

import bg.softuni.xmlProccessing.domain.dtos.exportDto.*;
import bg.softuni.xmlProccessing.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

@Component
@RequiredArgsConstructor
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final SeedService seedService;
    private final CustomerService customerService;
    private final SupplierService supplierService;
    private final SaleService saleService;
    private final CarService carService;

    @Override public void run(String... args) throws Exception {
        seedService.seedAll();
//    orderedCustomers();
//        getAllByMaker("Toyota");
//        getLocalSuppliers();
//        getCarsWithPartsList();
//        getTotalSalesByCustomer();
        getSalesWithDiscount();
    }

    private void getSalesWithDiscount() throws JAXBException {
        Marshaller marshaller = JAXBContext.newInstance(SaleDetailsWrapperDto.class)
                .createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller
                .marshal(saleService.getSalesWithDiscount(), System.out);
    }

    private void getTotalSalesByCustomer() throws JAXBException {
        Marshaller marshaller = JAXBContext.newInstance(CustomerWithSalesWrapperDto.class)
                .createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller
                .marshal(customerService.getTotalSalesByCustomer(), System.out);
    }

    private void getCarsWithPartsList() throws JAXBException {
        Marshaller marshaller = JAXBContext.newInstance(CarWithPartsListWrapperDto.class)
                .createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller
                .marshal(carService.getCarsWithPartsList(), System.out);
    }

    private void getLocalSuppliers() throws JAXBException {
        Marshaller marshaller = JAXBContext.newInstance(SupplierSimpleExportWrapperDto.class)
                .createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller
                .marshal(supplierService.getLocalSuppliers(), System.out);
    }

    private void getAllByMaker(String make) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(CarExportWrapperDto.class);
        Marshaller marshaller = context
                .createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller
                .marshal(carService.getAllToyotas(make), System.out);
    }

    private void orderedCustomers() throws JAXBException {
        Marshaller marshaller = JAXBContext.newInstance(CustomerExportWrapperDto.class)
                .createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller
                .marshal(customerService.findAllOrderedByBirthDateAndYoung(), System.out);
    }
}
