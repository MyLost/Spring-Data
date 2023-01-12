package bg.softuni.exercise.services.author;

import bg.softuni.exercise.entities.Author;
import bg.softuni.exercise.repositories.author.AuthorRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import static bg.softuni.exercise.utils.Constants.AUTHOR_FILE_NAME;
import static bg.softuni.exercise.utils.Constants.RESOURCE_PATH;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void populateAuthors() throws IOException {

        Files.readAllLines(Path.of(RESOURCE_PATH + AUTHOR_FILE_NAME))
                .forEach(row -> {
                    String[] data = row.split("\\s+");

                    Author author = new Author();
                    author.setFirstName(data[0]);
                    author.setLastName(data[1]);

                    authorRepository.save(author);

                });
    }

    @Override
    public Author getRandomAuthor() {

        final long count = this.authorRepository.count();

        if (count != 0) {
            final long randomAuthorId = new Random().nextLong(1L, count) + 1L;

            return this.authorRepository.findAuthorById(randomAuthorId).orElseThrow(NoSuchElementException::new);
        }

        throw new RuntimeException();

    }

    @Override
    public List<Author> findDistinctByBooksBefore(LocalDate date) {
        return this.authorRepository.findDistinctByBooksReleaseDateBefore(date).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Author> findAllOrderByBooks() {
        return this.authorRepository.findAllDistinctOrderByBooks().orElseThrow(NoSuchElementException::new);
    }
}
