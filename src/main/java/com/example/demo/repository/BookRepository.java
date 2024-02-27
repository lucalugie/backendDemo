package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    long countByAuthor(String author);
    Optional<Book> findFirstByAuthorOrderByPriceDesc(String author);
    List<Book> findTopByOrderByPriceDesc();

    List<Book> findByAuthor(String author);
}
