package bg.softuni.json.services.impl;

import bg.softuni.json.constatns.MenuLines;
import bg.softuni.json.constatns.Messages;
import bg.softuni.json.constatns.PathFiles;
import bg.softuni.json.dtos.category.CategoryCountProductsDto;
import bg.softuni.json.dtos.product.ProductWithoutBuyerDto;
import bg.softuni.json.dtos.user.UserSoldProductsDto;
import bg.softuni.json.dtos.user.UsersCountWrapperDto;
import bg.softuni.json.services.CategoryService;
import bg.softuni.json.services.ExecutorService;
import bg.softuni.json.services.ProductService;
import bg.softuni.json.services.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

@Service
public class ExecutorServiceImpl implements ExecutorService {
    private final Scanner scanner;
    private final Gson gson;
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ExecutorServiceImpl(
            Scanner scanner,
            Gson gson, UserService userService,
            ProductService productService,
            CategoryService categoryService) {
        this.scanner = scanner;
        this.gson = gson;
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public String executeCommand() throws IOException {

        printMainMenu();

        int mainMenu = Integer.parseInt(scanner.nextLine());

        String result = switch (mainMenu) {
            case 0 -> Messages.END_MENU;
            case 1 -> _01_allProductsWithoutBuyer();
            case 2 -> _02_soldProductWithBuyers();
            case 3 -> _03_categoriesWithSoldProducts();
            case 4 -> _04_usersAndSoldProducts();
            default -> Messages.NO_SUCH_MENU;
        };

        return result.trim();
    }

    private String _04_usersAndSoldProducts() throws IOException {
        UsersCountWrapperDto usersCountWrapperDto = this.userService.findUsersSoldProductsWithCount();

        this.writeJsonToFile(usersCountWrapperDto, PathFiles.USER_AND_PRODUCTS_FILE_PATH);

        return Messages.CHECK_THE_FILE + System.lineSeparator() +
                PathFiles.OUT_PATH + PathFiles.USERS_AND_PRODUCTS;
    }

    private String _03_categoriesWithSoldProducts() throws IOException {
        List<CategoryCountProductsDto> categoryCountProducts = this.categoryService.allOrderByCountProducts();

        this.writeJsonToFile(categoryCountProducts, PathFiles.CATEGORIES_BY_PRODUCTS_FILE_PATH);

        return Messages.CHECK_THE_FILE + System.lineSeparator() +
                PathFiles.OUT_PATH + PathFiles.CATEGORIES_BY_PRODUCTS;
    }

    private String _02_soldProductWithBuyers() throws IOException {
        List<UserSoldProductsDto> userSoldProducts = this.userService.findUsersWithSoldProducts();

        this.writeJsonToFile(userSoldProducts, PathFiles.USER_SOLD_PRODUCTS_FILE_PATH);

        return Messages.CHECK_THE_FILE + System.lineSeparator() +
                PathFiles.OUT_PATH + PathFiles.USERS_SOLD_PRODUCTS;
    }

    private String _01_allProductsWithoutBuyer() throws IOException {

        System.out.println(Messages.BOTTOM_PRICE_RANGE);
        double bottom = Double.parseDouble(scanner.nextLine());

        System.out.println(Messages.TOP_PRICE_RANGE);
        double top = Double.parseDouble(scanner.nextLine());

        List<ProductWithoutBuyerDto> allWithoutBuyer = this.productService.findAllWithoutBuyer(bottom, top);

        this.writeJsonToFile(allWithoutBuyer, PathFiles.PRODUCTS_WITHOUT_BUYER_FILE_PATH);

        return Messages.CHECK_THE_FILE + System.lineSeparator() +
                PathFiles.OUT_PATH + PathFiles.PRODUCT_IN_RANGE;
    }

    public void writeJsonToFile(Object object, Path filePath) throws IOException {
        final FileWriter fileWriter = new FileWriter(filePath.toFile());

        gson.toJson(object, fileWriter);

        fileWriter.flush();
        fileWriter.close();

    }

    private void printMainMenu() {

        StringBuilder mainMenu = new StringBuilder().append(System.lineSeparator()).
                append(MenuLines.MENU_TOP).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_01).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_02).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_03).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_04).append(System.lineSeparator()).
                append(MenuLines.MENU_EXIT).append(System.lineSeparator()).
                append(MenuLines.MENU_BOTTOM);

        System.out.println(mainMenu);
    }
}