package com.example.springintro.service.impl;

import com.example.springintro.model.dto.BookInformation;
import com.example.springintro.model.entity.*;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository
                .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        return bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
       return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAllByAgeRestriction(String ageRestriction) {
       AgeRestriction ageRestrictionEnumValue = AgeRestriction.valueOf(ageRestriction.toUpperCase());
       return bookRepository.findAllByAgeRestriction(ageRestrictionEnumValue);
    }

    @Override
    public List<Book> findAllByEditionTypeAndCopiesGreaterThan(EditionType editionType, Integer copies) {
        return this.bookRepository.findAllByEditionTypeAndCopiesGreaterThan(editionType, copies).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, Integer copies) {
        return this.bookRepository.findAllByEditionTypeAndCopiesLessThan(editionType, copies).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByPriceLessThanAndGreaterThan(BigDecimal lowPrice, BigDecimal highPrice) {
       return this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(lowPrice, highPrice).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByReleaseDateStartingWith(String date) {
        return null;
    }

    @Override
    public List<Book> findAllByReleaseDateNot(LocalDate date) {
        return this.bookRepository.findAllByReleaseDateNot(date).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore) {
        return this.bookRepository.findAllByReleaseDateBefore(releaseDateBefore);
    }

    @Override
    public List<Book> findAllTitleContaining(String contains) {
        return this.bookRepository.findAllByTitleContaining(contains).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByAuthorFirstNameStartingWith(String prefix) {
        return this.bookRepository.findAllByAuthorLastNameStartingWith(prefix).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Integer findAllByTitleLength(Integer length) {
        return this.bookRepository.findAllByTitleLength(length).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public BookInformation findFirstByTitle(String title) {
        return this.bookRepository.findFirstByTitle(title).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Integer increaseBookCopies(LocalDate date, int copies) {
        return this.bookRepository.increaseBookCopies(date, copies).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public int deleteAllByCopiesLessThan(Integer copies) {
        return this.bookRepository.deleteAllByCopiesLessThan(copies);
    }

    @Override
    public List<Book> findAllWrittenBooksByAuthorFirstAndLastName(String firstName, String lastName) {
        return this.bookRepository.findAllWrittenBooksByAuthorFirstAndLastName(firstName, lastName).orElseThrow(NoSuchElementException::new);
    }

    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

    }
}
