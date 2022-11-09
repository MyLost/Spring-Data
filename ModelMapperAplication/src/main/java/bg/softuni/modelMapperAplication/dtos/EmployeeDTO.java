package bg.softuni.modelMapperAplication.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {

    private String firstName;

    private BigDecimal salary;

    private String city;

}
