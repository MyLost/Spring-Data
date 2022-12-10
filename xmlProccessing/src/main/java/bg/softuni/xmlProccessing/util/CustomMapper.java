package bg.softuni.xmlProccessing.util;

import bg.softuni.xmlProccessing.domain.dtos.exportDto.CarSimpleExportDto;
import bg.softuni.xmlProccessing.domain.dtos.exportDto.SaleDetailsDto;
import bg.softuni.xmlProccessing.domain.entities.Car;
import bg.softuni.xmlProccessing.domain.entities.Part;
import bg.softuni.xmlProccessing.domain.entities.Sale;

import java.math.BigDecimal;

public class CustomMapper {
    public static SaleDetailsDto saleToSaleDetails(Sale sale) {
        return SaleDetailsDto
                .builder()
                .customerName(sale.getCustomer().getName())
                .car(carToCarSimpleExportDto(sale.getCar()))
                .discount(sale.getDiscount())
                .price(sale.getCar().getParts().stream().map(Part::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add))
                .priceWithDiscount((sale.getCar().getParts().stream().map(Part::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add)).multiply(BigDecimal.valueOf(sale.getDiscount())))
                .build();
    }

    private static CarSimpleExportDto carToCarSimpleExportDto(Car car) {
        return CarSimpleExportDto
                .builder()
                .make(car.getMake())
                .model(car.getModel())
                .travelledDistance(car.getTravelledDistance())
                .build();
    }
}