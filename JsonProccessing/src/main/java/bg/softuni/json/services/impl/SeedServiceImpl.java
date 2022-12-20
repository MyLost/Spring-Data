package bg.softuni.json.services.impl;

import bg.softuni.json.services.CategoryService;
import bg.softuni.json.services.ProductService;
import bg.softuni.json.services.SeedService;
import bg.softuni.json.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SeedServiceImpl implements SeedService {
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public SeedServiceImpl(
            UserService userService,
            ProductService productService,
            CategoryService categoryService){
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedData() throws IOException {
        userService.seedUsers();
        categoryService.seedCategories();
        productService.seedProducts();
    }
}