package bg.softuni.exercise.utils;

import java.time.LocalDate;

public class Constants {

    public static final String BOOKS_FILE_NAME = "books.txt";
    public static final String AUTHOR_FILE_NAME = "authors.txt";

    public static final String CATEGORY_FILE_NAME = "categories.txt";
    public static final String RESOURCE_PATH = "src/main/resources/files/";

    public static final LocalDate BOOK_YEAR_AFTER = LocalDate.of(2000, 1, 1);
    public static final LocalDate BOOK_YEAR_BEFORE = LocalDate.of(1990, 1, 1);



    public static final String STARTING_CONSOLE_APPLICATION = "Starting console application...";
    public static final String TO_STARS_TASK_CHOOSE_NUMBER_BEFORE_TASK_NAME = "To stars task choose number before task name!";
    public static final String GET_ALL_BOOKS_AFTER_THE_YEAR_2000_PRINT_ONLY_THEIR_TITLES = "1. Get all books after the year 2000. Print only their titles.";
    public static final String GET_ALL_AUTHORS_WITH_AT_LEAST_ONE_BOOK_WITH_RELEASE_DATE_BEFORE_1990_PRINT_THEIR_FIRST_NAME_AND_LAST_NAME = "2. Get all authors with at least one book with release date before 1990. Print their first name and last name.";
    public static final String GET_ALL_AUTHORS_ORDERED_BY_THE_NUMBER_OF_THEIR_BOOKS_DESCENDING_PRINT_THEIR_FIRST_NAME_LAST_NAME_AND_BOOK_COUNT = "3. Get all authors, ordered by the number of their books (descending). Print their first name, last name and book count.";
    public static final String GET_ALL_BOOKS_FROM_AUTHOR_GEORGE_POWELL_ORDERED_BY_THEIR_RELEASE_DATE_DESCENDING_THEN_BY_BOOK_TITLE_ASCENDING_PRINT_THE_BOOK_S_TITLE_RELEASE_DATE_AND_COPIES = "4. Get all books from author George Powell, ordered by their release date (descending), then by book title (ascending). Print the book's title, release date and copies.";
}
