package bg.softuni.exercise;

import bg.softuni.exercise.services.author.AuthorService;
import bg.softuni.exercise.services.book.BookService;
import bg.softuni.exercise.services.category.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Main implements CommandLineRunner {

    private AuthorService authorService;

    private CategoryService categoryService;

    private BookService bookService;

    private ConsoleApplication consoleApplication;


    public Main(AuthorService authorService, CategoryService categoryService, BookService bookService, ConsoleApplication consoleApplication) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
        this.consoleApplication = consoleApplication;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Running....");

        seedDatabase();

        consoleApplication.startApplication();
    }

    public void seedDatabase() throws IOException {

        authorService.populateAuthors();

        categoryService.populateCategories();

        bookService.populateBooks();

    }
}
