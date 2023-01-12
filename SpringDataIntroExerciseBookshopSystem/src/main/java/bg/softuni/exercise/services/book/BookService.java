package bg.softuni.exercise.services.book;

import bg.softuni.exercise.entities.Book;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {

    public void populateBooks() throws IOException;

    public List<Book> findAllByReleaseDateAfter(LocalDate dateAfter);

    Iterable<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);
}
