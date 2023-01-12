package bg.softuni.exercise.repositories.author;

import bg.softuni.exercise.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<List<Author>> findDistinctByBooksReleaseDateBefore(LocalDate date);

    Optional<Author> findAuthorById(long randomAuthorId);
    @Query("Select a from Author a order by a.books.size")
    Optional<List<Author>> findAllDistinctOrderByBooks();
}
