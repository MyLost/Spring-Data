package bg.softuni.exercise.services.category;

import bg.softuni.exercise.entities.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {

    public void populateCategories() throws IOException;

    Set<Category> getRandomCategories();
}
