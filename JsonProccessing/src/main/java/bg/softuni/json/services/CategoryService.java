package bg.softuni.json.services;

import bg.softuni.json.dtos.category.CategoryCountProductsDto;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    void seedCategories() throws IOException;

    List<CategoryCountProductsDto> allOrderByCountProducts();
}