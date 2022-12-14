package com.example.springintro;

import com.example.springintro.model.dto.BookInformation;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

/*
    Stored procedure for task 14 is:
    ====================================================================================================================
    CREATE PROCEDURE bookshop.author_proc(f_name varchar(255), l_name varchar(255))
    BEGIN
	select * from books as b WHERE b.author_id = (select id from authors as a where a.first_name = f_name and a.last_name = l_name);
    END
    ====================================================================================================================
    In some task data is hardcoded! Example can be extended like data be provided from console to each method!
 */
public class ExercisesRunner {

    private BookService bookService;

    private AuthorService authorService;
    Scanner scanner = null;

    public ExercisesRunner(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
        scanner = new Scanner(System.in);
    }

    public void runExercises() {

            System.out.println("Exercise runner run...");

            System.out.println("Please select number from 1 to 14 to execute desired task! If you want exit type END!");
            String command = scanner.nextLine();

            while(!"END".equals(command)) {

                switch (command) {
                    case "1" -> BooksTitlesByAgeRestriction();
                    case "2" -> goldenBooks();
                    case "3" -> BooksByPrice ();
                    case "4" -> NotReleasedBooks();
                    case "5" -> BooksReleasedBeforeDate();
                    case "6" -> AuthorsSearch();
                    case "7" -> BooksSearch();
                    case "8" -> BookTitlesSearch();
                    case "9" -> CountBooks();
                    case "11" -> ReducedBook();
                    case "12" -> IncreaseBookCopies();
                    case "13" -> RemoveBooks();
                    case "14" -> StoredProcedure();
                    default -> {
                        System.out.println("Wrong input! Try again ot type END for exit!");
                        }
                    }
                command = scanner.nextLine();
                }
        }

        public void BooksTitlesByAgeRestriction() {

            System.out.println(String.format("Please provide input for task %s!!!","Books Titles by Age Restriction"));

            String input = scanner.nextLine();

            bookService.findAllByAgeRestriction(input).forEach(book -> {
                System.out.println(book.getTitle());
            });
        }

        public void goldenBooks() {

            this.bookService.findAllByEditionTypeAndCopiesLessThan(EditionType.GOLD, 5000)
                    .stream()
                    .map(Book::getTitle)
                    .forEach(System.out::println);
        }

        public void BooksByPrice () {

            this.bookService.findAllByPriceLessThanAndGreaterThan(BigDecimal.valueOf(5),BigDecimal.valueOf(40))
                    .forEach(book -> {
                        System.out.println(String.format("%s - $%.2f", book.getTitle(), book.getPrice()));
            });
        }

        public void NotReleasedBooks() {

            this.bookService.findAllByReleaseDateNot(LocalDate.of(2000,1, 1))
                    .stream()
                    .map(Book::getTitle)
                    .forEach(System.out::println);
        }

        public void BooksReleasedBeforeDate() {

        this.bookService.findAllByReleaseDateBefore(LocalDate.of(1992, 04,12))
                .forEach(book -> System.out.println(String.format("%s %s %.2f", book.getTitle(), book.getEditionType(), book.getPrice())));
        }

        public void AuthorsSearch() {

            this.authorService.findAllByFirstNameEndingWith("e")
                    .forEach(author -> System.out.println(String.format("%s %s", author.getFirstName(), author.getLastName())));
        }

        public void BooksSearch() {

            this.bookService.findAllTitleContaining("sK")
                    .stream()
                    .map(Book::getTitle)
                    .forEach(System.out::println);
        }

        public void BookTitlesSearch() {

            this.bookService.findAllByAuthorFirstNameStartingWith("Ric")
                    .forEach(book-> System.out.println(String.format("%s (%s %s)", book.getTitle(), book.getAuthor().getFirstName(), book.getAuthor().getLastName())));
        }

        public void CountBooks() {

            System.out.println(String.format("There are %d books with longer title than 12 symbols" , this.bookService.findAllByTitleLength(12)
                    .intValue()));
        }

        public void ReducedBook() {

            BookInformation bookInformation = this.bookService.findFirstByTitle("Things Fall Apart ");
            System.out.println(bookInformation);
        }

        public void  IncreaseBookCopies() {

            this.bookService.increaseBookCopies(LocalDate.of(2005, 10, 12), 100);
        }

        public void RemoveBooks() {

            this.bookService.deleteAllByCopiesLessThan(5000);
        }


        public void StoredProcedure() {

            var books = this.bookService.findAllWrittenBooksByAuthorFirstAndLastName("Amanda", "Rice");
            var bookSize = this.bookService.findAllWrittenBooksByAuthorFirstAndLastName("Amanda", "Rice")
                    .size();
            System.out.println(String.format("%s %s has written %d books",books.get(0).getAuthor().getFirstName(), books.get(0).getAuthor().getLastName(), bookSize));
        }
}
