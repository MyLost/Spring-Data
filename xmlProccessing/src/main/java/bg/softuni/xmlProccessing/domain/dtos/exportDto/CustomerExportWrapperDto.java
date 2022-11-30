package bg.softuni.xmlProccessing.domain.dtos.exportDto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerExportWrapperDto {
    @XmlElement(name = "customer")
    private List<CustomerExportDto> customers;

    public CustomerExportWrapperDto() {
        customers = new ArrayList<>();
    }
}