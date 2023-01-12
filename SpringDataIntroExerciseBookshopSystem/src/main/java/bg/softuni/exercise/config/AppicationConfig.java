package bg.softuni.exercise.config;

import bg.softuni.exercise.ConsoleApplication;
import bg.softuni.exercise.services.author.AuthorService;
import bg.softuni.exercise.services.book.BookService;
import bg.softuni.exercise.services.category.CategoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppicationConfig {

    private BookService bookService;

    private AuthorService authorService;

    private CategoryService categoryService;

    public AppicationConfig(BookService bookService, AuthorService authorService, CategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }


    @Bean
    public ConsoleApplication provideApplication() {
        return new ConsoleApplication(bookService, authorService, categoryService);
    }
}
