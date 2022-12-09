package bg.softuni.xmlProccessing.service.impl;

import bg.softuni.xmlProccessing.domain.dtos.exportDto.CarExportDto;
import bg.softuni.xmlProccessing.domain.dtos.exportDto.CarExportWrapperDto;
import bg.softuni.xmlProccessing.domain.dtos.exportDto.CarWithPartsListDto;
import bg.softuni.xmlProccessing.domain.dtos.exportDto.CarWithPartsListWrapperDto;
import bg.softuni.xmlProccessing.domain.entities.Car;
import bg.softuni.xmlProccessing.repositories.CarRepository;
import bg.softuni.xmlProccessing.service.CarService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final Random random;
    private final ModelMapper mapper;

    @Override public long count() {
        return carRepository.count();
    }

    @Override public void save(Car car) {
        carRepository.save(car);
    }

    @Override public Car getRandomCar() {
        long id = random.nextLong(carRepository.count()) + 1L;
        return carRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override public CarExportWrapperDto getAllToyotas(String make) {
        CarExportWrapperDto toReturn = new CarExportWrapperDto();
        carRepository
                .findAllByMakeOrderByModelAscTravelledDistanceDesc(make)
                .stream()
                .map(c -> mapper.map(c, CarExportDto.class))
                .forEach(s -> toReturn.getCars().add(s));
        return toReturn;
    }

    @Transactional
    @Override public CarWithPartsListWrapperDto getCarsWithPartsList() {
        CarWithPartsListWrapperDto toReturn = new CarWithPartsListWrapperDto();
        carRepository.
                findAll()
                .stream()
                .map(c -> mapper.map(c, CarWithPartsListDto.class))
                .forEach(s ->toReturn.getCars().add(s));
        return toReturn;
    }
}