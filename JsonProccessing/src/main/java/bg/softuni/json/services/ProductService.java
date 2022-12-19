package bg.softuni.json.services;

import bg.softuni.json.dtos.product.ProductWithoutBuyerDto;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    void seedProducts() throws IOException;

    List<ProductWithoutBuyerDto> findAllWithoutBuyer(double bottom, double top);
}