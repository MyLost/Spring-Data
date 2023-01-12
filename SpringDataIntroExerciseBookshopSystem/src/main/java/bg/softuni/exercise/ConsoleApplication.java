package bg.softuni.exercise;

import bg.softuni.exercise.services.author.AuthorService;
import bg.softuni.exercise.services.book.BookService;
import bg.softuni.exercise.services.category.CategoryService;

import java.util.Scanner;

import static bg.softuni.exercise.utils.Constants.*;

public class ConsoleApplication {


    private BookService bookService;

    private CategoryService categoryService;

    private AuthorService authorService;

    public ConsoleApplication(BookService bookService, AuthorService authorService, CategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    public void startApplication() {

        Scanner scanner = new Scanner(System.in);

        System.out.println(STARTING_CONSOLE_APPLICATION);

        System.out.println(TO_STARS_TASK_CHOOSE_NUMBER_BEFORE_TASK_NAME);

        System.out.println(GET_ALL_BOOKS_AFTER_THE_YEAR_2000_PRINT_ONLY_THEIR_TITLES);

        System.out.println(GET_ALL_AUTHORS_WITH_AT_LEAST_ONE_BOOK_WITH_RELEASE_DATE_BEFORE_1990_PRINT_THEIR_FIRST_NAME_AND_LAST_NAME);

        System.out.println(GET_ALL_AUTHORS_ORDERED_BY_THE_NUMBER_OF_THEIR_BOOKS_DESCENDING_PRINT_THEIR_FIRST_NAME_LAST_NAME_AND_BOOK_COUNT);

        System.out.println(GET_ALL_BOOKS_FROM_AUTHOR_GEORGE_POWELL_ORDERED_BY_THEIR_RELEASE_DATE_DESCENDING_THEN_BY_BOOK_TITLE_ASCENDING_PRINT_THE_BOOK_S_TITLE_RELEASE_DATE_AND_COPIES);
        int choice =  Integer.parseInt(scanner.nextLine());

        switch(choice) {
            case 1 -> printAllBookTitleAfterDate();
            case 2 -> printAuthorFirstAndLastNameWhichHaveBookBeforeDate();
            case 3 -> getAllOrderByBooks();
            case 4 -> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc();
        };
    }

    private void printAllBookTitleAfterDate() {
        bookService.findAllByReleaseDateAfter(BOOK_YEAR_AFTER)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void printAuthorFirstAndLastNameWhichHaveBookBeforeDate() {
        authorService.findDistinctByBooksBefore(BOOK_YEAR_BEFORE)
                .forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));
    }

    private void getAllOrderByBooks() {
        this.authorService.findAllOrderByBooks()
                .forEach(author -> System.out.println(author.toStringWithCount()));
    }

    private void findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc() {
        this.bookService
                .findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc("George", "Powell")
                .forEach(book -> System.out.println(book.getTitle() + " "
                        + book.getReleaseDate() + " "
                        + book.getCopies()));
    }

}



