package bg.softuni.exercise.services.author;

import bg.softuni.exercise.entities.Author;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface AuthorService {

    public void populateAuthors() throws IOException;

    Author getRandomAuthor();

    public List<Author> findDistinctByBooksBefore(LocalDate date);

    List<Author> findAllOrderByBooks();
}
