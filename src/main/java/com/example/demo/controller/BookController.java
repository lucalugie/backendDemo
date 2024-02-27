package com.example.demo.controller;

import com.example.demo.dto.BookRequest;
import com.example.demo.dto.BookResponse;
import com.example.demo.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/books")

public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public BookResponse saveBook(@RequestBody BookRequest book) {
        return bookService.saveBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @PatchMapping("/{id}")
    public BookResponse updateBookById(@PathVariable Long id, @RequestBody BookRequest updatedBook){
        return bookService.updateBookById(id, updatedBook);
    }

    @GetMapping("/highestPrice")
    public ResponseEntity<List <BookResponse>> getBooksWithHighestPrice() {
        List<BookResponse> booksWithHighestPrice = bookService.findBooksWithHighestPrice();

        if (!booksWithHighestPrice.isEmpty()) {
            return ResponseEntity.ok(booksWithHighestPrice);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/author/{authorName}")
    public ResponseEntity<List<BookResponse >> getBooksByAuthor(@PathVariable String authorName) {
        List<BookResponse> booksByAuthor = bookService.findBooksByAuthor(authorName);

        if (booksByAuthor != null && !booksByAuthor.isEmpty()) {
            return ResponseEntity.ok(booksByAuthor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
