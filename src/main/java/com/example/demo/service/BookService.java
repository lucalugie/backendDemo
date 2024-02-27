package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.BookResponse;
import com.example.demo.dto.BookRequest;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    List <BookResponse> getAllBooks();
    BookResponse saveBook(BookRequest createBookRequest);
    BookResponse getBookById(Long id);
    void deleteBook(Long id);
    BookResponse updateBookById(Long id, BookRequest updatedBook);

    List<BookResponse> findBooksWithHighestPrice();
    List<BookResponse> findBooksByAuthor(String author);
}
