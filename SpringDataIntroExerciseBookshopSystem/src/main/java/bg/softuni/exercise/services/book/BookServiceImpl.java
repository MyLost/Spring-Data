package bg.softuni.exercise.services.book;

import bg.softuni.exercise.entities.Author;
import bg.softuni.exercise.entities.Book;
import bg.softuni.exercise.entities.Category;
import bg.softuni.exercise.enums.AgeRestriction;
import bg.softuni.exercise.enums.EditionType;
import bg.softuni.exercise.repositories.book.BookRepository;
import bg.softuni.exercise.services.author.AuthorService;
import bg.softuni.exercise.services.category.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import static bg.softuni.exercise.utils.Constants.BOOKS_FILE_NAME;
import static bg.softuni.exercise.utils.Constants.RESOURCE_PATH;

@Service
public class BookServiceImpl implements BookService {

    private AuthorService authorService;

    private CategoryService categoryService;

    private BookRepository bookRepository;

    public BookServiceImpl(AuthorService authorService, CategoryService categoryService, BookRepository bookRepository) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookRepository = bookRepository;
    }

    @Override
    public void populateBooks() throws IOException {
        Files.readAllLines(Path.of(RESOURCE_PATH + BOOKS_FILE_NAME))
                .forEach(row -> {
                    String[] data = row.split("\\s+");

                    Author author = authorService.getRandomAuthor();
                    EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
                    LocalDate releaseDate = LocalDate.parse(data[1],
                            DateTimeFormatter.ofPattern("d/M/yyyy"));
                    int copies = Integer.parseInt(data[2]);
                    BigDecimal price = new BigDecimal(data[3]);
                    AgeRestriction ageRestriction = AgeRestriction
                            .values()[Integer.parseInt(data[4])];
                    String title = Arrays.stream(data)
                            .skip(5)
                            .collect(Collectors.joining(" "));
                    Set<Category> categories = categoryService.getRandomCategories();


                    Book book = new Book(title, editionType, price, releaseDate,
                            ageRestriction, author, categories, copies);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllByReleaseDateAfter(LocalDate dateAfter) {

       return bookRepository.findAllByReleaseDateAfter(dateAfter).get();

    }

    @Override
    public Iterable<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName) {
        return this.bookRepository.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(firstName, lastName)
                .orElseThrow(NoSuchElementException::new);
    }
}
