package com.example.springintro.repository;

import com.example.springintro.model.dto.BookInformation;
import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    String SELECT_COUNT_B_ID_FROM_BOOK_B_WHERE_LENGTH_B_TITLE_LENGTH = "select count(b.id) from Book b where length(b.title) > :length";
    String UPDATE_BOOK_B_SET_B_COPIES_B_COPIES_COPIES_WHERE_B_RELEASE_DATE_DATE = "Update Book b set b.copies = b.copies + :copies where b.releaseDate > :date";
    String SELECT_NEW_COM_EXAMPLE_SPRINGINTRO_MODEL_DTO_BOOK_INFORMATION_B_TITLE_B_EDITION_TYPE_B_AGE_RESTRICTION_B_PRICE_FROM_BOOK_B_WHERE_B_TITLE_TITLE = "select new com.example.springintro.model.dto.BookInformation(b.title, b.editionType, b.ageRestriction, b.price)from Book b where b.title = :title";
    String CALL_AUTHOR_PROC_FIRST_NAME_LAST_NAME = "call author_proc(:firstName, :lastName)";

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    Optional<List<Book>> findAllByEditionTypeAndCopiesGreaterThan(EditionType editionType, Integer copies);

    Optional<List<Book>> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, Integer copies);

    Optional<List<Book>> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowPrice, BigDecimal highPrice);

    Optional<List<Book>> findAllByReleaseDateNot(LocalDate date);

    Optional<List<Book>> findAllByTitleContaining(String contains);

    Optional<List<Book>> findAllByAuthorLastNameStartingWith(String prefix);

    @Query(SELECT_COUNT_B_ID_FROM_BOOK_B_WHERE_LENGTH_B_TITLE_LENGTH)
    Optional<Integer> findAllByTitleLength(Integer length);

    @Query(SELECT_NEW_COM_EXAMPLE_SPRINGINTRO_MODEL_DTO_BOOK_INFORMATION_B_TITLE_B_EDITION_TYPE_B_AGE_RESTRICTION_B_PRICE_FROM_BOOK_B_WHERE_B_TITLE_TITLE)
    Optional<BookInformation> findFirstByTitle(String title);

    @Modifying
    @Transactional
    @Query(UPDATE_BOOK_B_SET_B_COPIES_B_COPIES_COPIES_WHERE_B_RELEASE_DATE_DATE)
    Optional<Integer> increaseBookCopies(LocalDate date, int copies);

    @Transactional
    int deleteAllByCopiesLessThan(Integer copies);

    @Query(value= CALL_AUTHOR_PROC_FIRST_NAME_LAST_NAME, nativeQuery = true)
    Optional<List<Book>> findAllWrittenBooksByAuthorFirstAndLastName(String firstName, String lastName);
}
