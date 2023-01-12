package bg.softuni.springDataAdvancedQuerying.service;

import bg.softuni.springDataAdvancedQuerying.entity.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;

    Set<Category> getRandomCategories();
}
