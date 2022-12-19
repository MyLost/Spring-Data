package bg.softuni.xmlProccessing.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleSimpleDto {

    private String carMake;
    private String carModel;
    private String customerName;
    private Double discount;
}