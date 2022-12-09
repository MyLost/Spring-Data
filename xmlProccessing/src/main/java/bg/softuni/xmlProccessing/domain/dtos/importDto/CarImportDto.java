package bg.softuni.xmlProccessing.domain.dtos.importDto;

import java.util.ArrayList;
import java.util.List;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import bg.softuni.xmlProccessing.domain.entities.Part;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarImportDto {
    @XmlElement(name = "make")
    private String make;
    @XmlElement(name = "model")
    private String model;
    @XmlElement(name = "travelled-distance")
    private Long travelledDistance;
    private List<Part> parts;

    public CarImportDto() {
        parts = new ArrayList<>();
    }
}