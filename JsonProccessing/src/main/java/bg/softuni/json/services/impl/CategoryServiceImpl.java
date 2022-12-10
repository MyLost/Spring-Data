package bg.softuni.json.services.impl;

import bg.softuni.json.constatns.PathFiles;
import bg.softuni.json.dtos.category.CategoryCountProductsDto;
import bg.softuni.json.dtos.category.CategoryImportDto;
import bg.softuni.json.entities.Category;
import bg.softuni.json.repositories.CategoryRepository;
import bg.softuni.json.services.CategoryService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    private final Gson gson;

    private final ModelMapper mapper;

    @Autowired
    public CategoryServiceImpl(
            CategoryRepository categoryRepository,
            Gson gson,
            ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.gson = gson;
        this.mapper = mapper;
    }

    @Override
    public void seedCategories() throws IOException {
        if (categoryRepository.count() == 0) {
            final FileReader fileReader = new FileReader(PathFiles.CATEGORIES_FILE_PATH.toFile());

            final List<Category> categories = Arrays.stream(gson.fromJson(fileReader, CategoryImportDto[].class))
                    .map(CategoryImportDto -> mapper.map(CategoryImportDto, Category.class))
                    .toList();

            fileReader.close();

            this.categoryRepository.saveAllAndFlush(categories);
        }
    }

    @Override
    public List<CategoryCountProductsDto> allOrderByCountProducts() {
        return this.categoryRepository.getCategoriesSummary()
                .orElseThrow(NoSuchElementException::new);
    }
}