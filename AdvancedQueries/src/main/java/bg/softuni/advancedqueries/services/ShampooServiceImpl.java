package bg.softuni.advancedqueries.services;

import bg.softuni.advancedqueries.entities.Shampoo;
import bg.softuni.advancedqueries.entities.Size;
import bg.softuni.advancedqueries.repositories.ShampooRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService {

    ShampooRepository shampooRepository;

    ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }
    @Override
    public List<Shampoo> findByBrand(String brand) {
        return shampooRepository.findByBrand(brand);
    }

    @Override
    public List<Shampoo> findByBrandAndSize(String brand, Size size) {
        return shampooRepository.findByBrandAndSize(brand, size);
    }

    @Override
    public List<Shampoo> findByBrandAndSize(String brand, String size) {

        Size parsed = Size.valueOf(size.toUpperCase());
        return shampooRepository.findByBrandAndSize(brand, parsed);
    }
}
