package bg.softuni.advancedqueries.services;

import bg.softuni.advancedqueries.entities.Shampoo;
import bg.softuni.advancedqueries.entities.Size;

import java.util.List;

public interface ShampooService {
    List<Shampoo> findByBrand(String brand);

    List<Shampoo> findByBrandAndSize(String brand, Size size);

    List<Shampoo> findByBrandAndSize(String brand, String size);

}
