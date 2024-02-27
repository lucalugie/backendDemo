package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.dto.BookResponse;
import com.example.demo.dto.BookRequest;
import com.example.demo.entity.Book;
import com.example.demo.mapper.BookMapper;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    @Autowired
    public BookServiceImpl(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookResponse> getAllBooks() {
        List<Book> booksEntity = bookRepository.findAll();
        return booksEntity.stream()
                .map(bookMapper::entityToResponseDTO)
                .collect(Collectors.toList());
    }
    @Override
    public BookResponse saveBook(BookRequest createBookRequest) {
        Book bookEntity = bookMapper.requestDTOToEntity(createBookRequest);
        bookRepository.save(bookEntity);
        return bookMapper.entityToResponseDTO(bookEntity);
    }
    @Override
    public BookResponse getBookById(Long bookId) {
        // Retrieve Book entity from the database
        Optional<Book> bookEntity = bookRepository.findById(bookId);
        // Map Book entity to BookDto
        return bookEntity.map(bookMapper::entityToResponseDTO).orElse(null);
    }
    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookResponse updateBookById(Long id, BookRequest updatedBook) {
        Book bookEntity =  bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(updatedBook.getBookTitle());
                    existingBook.setAuthor(updatedBook.getAuthor());
                    existingBook.setPrice(updatedBook.getBookPrice());
                    existingBook.setCategory(updatedBook.getBookCategory());
                    return bookRepository.save(existingBook);
                })
                .orElse(null);

        return  bookMapper.entityToResponseDTO(bookEntity);
    }


    public List<BookResponse> findBooksWithHighestPrice() {
        List <Book> booksEntity = bookRepository.findTopByOrderByPriceDesc();
        return booksEntity.stream()
                .map(bookMapper::entityToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<BookResponse> findBooksByAuthor(String author){
        List <Book> booksEntity = bookRepository.findByAuthor(author);
        return booksEntity.stream()
                .map(bookMapper::entityToResponseDTO)
                .collect(Collectors.toList());
    }

}
