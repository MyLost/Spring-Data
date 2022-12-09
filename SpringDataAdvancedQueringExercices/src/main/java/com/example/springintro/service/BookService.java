package com.example.springintro.service;

import com.example.springintro.model.dto.BookInformation;
import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<Book> findAllByAgeRestriction(String ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesGreaterThan(EditionType editionType, Integer copies);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, Integer copies);

    List<Book> findAllByPriceLessThanAndGreaterThan(BigDecimal lowPrice, BigDecimal highPrice);

    List<Book> findAllByReleaseDateStartingWith(String date);

    List<Book> findAllByReleaseDateNot(LocalDate date);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllTitleContaining(String contains);

    List<Book> findAllByAuthorFirstNameStartingWith(String prefix);

    Integer findAllByTitleLength(Integer length);

    BookInformation findFirstByTitle(String title);

    Integer increaseBookCopies(LocalDate date, int copies);

    int deleteAllByCopiesLessThan(Integer copies);

    List<Book> findAllWrittenBooksByAuthorFirstAndLastName(String firstName, String lastName);

}
