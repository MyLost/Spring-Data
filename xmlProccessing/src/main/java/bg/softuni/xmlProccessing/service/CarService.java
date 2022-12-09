package bg.softuni.xmlProccessing.service;

import bg.softuni.xmlProccessing.domain.dtos.exportDto.CarExportWrapperDto;
import bg.softuni.xmlProccessing.domain.dtos.exportDto.CarWithPartsListWrapperDto;
import bg.softuni.xmlProccessing.domain.entities.Car;

public interface CarService {
    long count();

    void save(Car car);

    Car getRandomCar();

    CarExportWrapperDto getAllToyotas(String make);

    CarWithPartsListWrapperDto getCarsWithPartsList();
}
