package bg.softuni.springDataAdvancedQuerying.service;

import bg.softuni.springDataAdvancedQuerying.entity.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();
}
