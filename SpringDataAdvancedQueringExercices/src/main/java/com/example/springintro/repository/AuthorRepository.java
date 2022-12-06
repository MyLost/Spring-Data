package com.example.springintro.repository;

import com.example.springintro.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    String SELECT_A_FROM_AUTHOR_A_ORDER_BY_A_BOOKS_SIZE_DESC = "SELECT a FROM Author a ORDER BY a.books.size DESC";

    @Query(SELECT_A_FROM_AUTHOR_A_ORDER_BY_A_BOOKS_SIZE_DESC)
    List<Author> findAllByBooksSizeDESC();

    Optional<List<Author>> findAllByFirstNameEndingWith(String suffix);
}
