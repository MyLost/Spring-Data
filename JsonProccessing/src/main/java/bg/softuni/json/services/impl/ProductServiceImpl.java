package bg.softuni.json.services.impl;

import bg.softuni.json.constatns.PathFiles;
import bg.softuni.json.dtos.product.ProductDto;
import bg.softuni.json.dtos.product.ProductImportDto;
import bg.softuni.json.dtos.product.ProductWithoutBuyerDto;
import bg.softuni.json.entities.Category;
import bg.softuni.json.entities.Product;
import bg.softuni.json.entities.User;
import bg.softuni.json.repositories.CategoryRepository;
import bg.softuni.json.repositories.ProductRepository;
import bg.softuni.json.repositories.UserRepository;
import bg.softuni.json.services.ProductService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.LongStream;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;
    private final Gson gson;

    @Autowired
    public ProductServiceImpl(
            ProductRepository productRepository,
            UserRepository userRepository,
            CategoryRepository categoryRepository,
            ModelMapper mapper,
            Gson gson) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.gson = gson;
    }


    @Override
    public void seedProducts() throws IOException {
        if (productRepository.count() == 0) {

            final FileReader fileReader = new FileReader(PathFiles.PRODUCTS_FILE_PATH.toFile());

            final List<Product> products = Arrays.stream(gson.fromJson(fileReader, ProductImportDto[].class))
                    .map(ProductImportDto -> mapper.map(ProductImportDto, Product.class))
                    .map(this::setRandomSeller)
                    .map(this::setRandomBuyer)
                    .map(this::setRandomCategories)
                    .toList();


            fileReader.close();

            this.productRepository.saveAllAndFlush(products);
        }
    }

    @Override
    public List<ProductWithoutBuyerDto> findAllWithoutBuyer(double bottom, double top) {
        BigDecimal bottom_price = BigDecimal.valueOf(bottom);
        BigDecimal top_price = BigDecimal.valueOf(top);

        return this.productRepository
                .findByBuyerNullAndPriceBetweenOrderByPrice(bottom_price, top_price)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(product -> mapper.map(product, ProductDto.class))
                .map(ProductDto::productWithoutBuyerDto)
                .toList();
    }

    private Product setRandomCategories(Product product) {

        final Random random = new Random();

        long high = this.categoryRepository.count();

        final long numberOfCategories = random.nextLong(high);

        final Set<Category> categories = new HashSet<>();

        LongStream.range(0, numberOfCategories)
                .forEach(number -> {
                    Category category = this.categoryRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
                    categories.add(category);
                });

        product.setCategories(categories);

        return product;
    }

    private Product setRandomBuyer(Product product) {
        if(product.getPrice().compareTo(BigDecimal.valueOf(700L)) > 0) {

            User buyer = getRandomUser();

            while (buyer.equals(product.getSeller())) {
                buyer = getRandomUser();
            }

            product.setBuyer(buyer);
        }

        return product;
    }

    private User getRandomUser() {
        return this.userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
    }

    private Product setRandomSeller(Product product) {
        final User seller = getRandomUser();

        product.setSeller(seller);

        return product;
    }
}