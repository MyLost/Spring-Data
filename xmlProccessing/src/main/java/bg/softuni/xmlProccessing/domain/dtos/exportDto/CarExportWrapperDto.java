package bg.softuni.xmlProccessing.domain.dtos.exportDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarExportWrapperDto {
    @XmlElement(name = "car")
    private List<CarExportDto> cars;

    public CarExportWrapperDto() {
        cars= new ArrayList<>();
    }
}