package bg.softuni.modelMapperAplication;

import bg.softuni.modelMapperAplication.dtos.EmployeeDTO;
import bg.softuni.modelMapperAplication.entities.Address;
import bg.softuni.modelMapperAplication.entities.Employee;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Main implements CommandLineRunner {

    private final ModelMapper modelMapper;

    public Main(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public void run(String... args) throws Exception {

        Address address = new Address("Bulgaria", "Sofia");
        Employee employee = new Employee("First", BigDecimal.TEN, address);

        PropertyMap<Employee, EmployeeDTO> propertyMap = new PropertyMap<Employee, EmployeeDTO>() {
            @Override
            protected void configure() {
                map().setCity(source.getAddress().getCity());
            }
        };

        modelMapper.addMappings(propertyMap);
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);

        System.out.println(employeeDTO);

    }
}
