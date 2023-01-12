package bg.softuni.exercise.services.category;


import bg.softuni.exercise.entities.Author;
import bg.softuni.exercise.entities.Category;
import bg.softuni.exercise.repositories.category.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static bg.softuni.exercise.utils.Constants.AUTHOR_FILE_NAME;
import static bg.softuni.exercise.utils.Constants.RESOURCE_PATH;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void populateCategories() throws IOException {
        Files.readAllLines(Path.of(RESOURCE_PATH + AUTHOR_FILE_NAME))
                .forEach(row -> {

                    Category category = new Category();
                    category.setName(row);

                    categoryRepository.save(category);

                });
    }

    @Override
    public Set<Category> getRandomCategories() {

        final Integer count =(int) this.categoryRepository.count();

        if (count != 0) {
            final Integer randomAuthorId = new Random().nextInt(1, count) + 1;

            return Set.of(this.categoryRepository.findById(randomAuthorId).orElseThrow(NoSuchElementException::new));
        }

        throw new RuntimeException();
    }
}
