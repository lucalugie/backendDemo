package com.example.demo.service;

import com.example.demo.dto.BookRequest;
import com.example.demo.dto.BookResponse;
import com.example.demo.entity.Book;
import com.example.demo.mapper.BookMapper;
import com.example.demo.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    private List<Book> prepareBookMockList(){
        Book mockBook0 = new Book();
        mockBook0.setId(1L);
        mockBook0.setTitle("Lone Wolf");
        mockBook0.setAuthor("Mock Author1");
        mockBook0.setCategory("Fantasy");
        mockBook0.setPrice(39.99);

        Book mockBook1 = new Book();
        mockBook1.setId(2L);
        mockBook1.setTitle("Beginning After The End");
        mockBook1.setAuthor("Mock Author1");
        mockBook1.setCategory("Fantasy");
        mockBook1.setPrice(49.99);

        Book mockBook2 = new Book();
        mockBook2.setId(3L);
        mockBook2.setTitle("Death Flags");
        mockBook2.setAuthor("Mock Author2");
        mockBook2.setCategory("Horror");
        mockBook2.setPrice(49.99);

        return List.of(mockBook0, mockBook1, mockBook2);
    }

    private List<BookRequest> prepareBookDtoMockList(){
        BookRequest mockBookDto0 = new BookRequest();
        mockBookDto0.setBookId(1L);
        mockBookDto0.setBookTitle("Lone Wolf");
        mockBookDto0.setAuthor("Mock Author1");
        mockBookDto0.setBookCategory("Fantasy");
        mockBookDto0.setBookPrice(39.99);

        BookRequest mockBookDto1 = new BookRequest();
        mockBookDto1.setBookId(2L);
        mockBookDto1.setBookTitle("Beginning After The End");
        mockBookDto1.setAuthor("Mock Author1");
        mockBookDto1.setBookCategory("Fantasy");
        mockBookDto1.setBookPrice(49.99);

        BookRequest mockBookDto2 = new BookRequest();
        mockBookDto2.setBookId(3L);
        mockBookDto2.setBookTitle("Death Flags");
        mockBookDto2.setAuthor("Mock Author2");
        mockBookDto2.setBookCategory("Horror");
        mockBookDto2.setBookPrice(49.99);

        return List.of(mockBookDto0, mockBookDto1, mockBookDto2);
    }

    private BookResponse createBookResponseFromEntity(Book bookEntity) {
        BookResponse bookDto = new BookResponse();
        bookDto.setBookId(bookEntity.getId());
        bookDto.setBookTitle(bookEntity.getTitle());
        bookDto.setAuthor(bookEntity.getAuthor());
        bookDto.setBookPrice(bookEntity.getPrice());
        bookDto.setBookCategory(bookEntity.getCategory());
        return bookDto;
    }

    @DisplayName("getBookById-case: success")
    @Test
    public void testGetBookById() {
        Long id = 1L;
        List <Book> booksEntity = prepareBookMockList();
        List <BookRequest> booksDto = prepareBookDtoMockList();
        BookRequest test = booksDto.get(0);
        assertFalse(booksEntity.isEmpty(), "Expected non-empty list of books");

        // Mock behavior of the bookRepository.findById method
        when(bookRepository.findById(id)).thenReturn(Optional.of(booksEntity.get(0)));
        when(bookMapper.entityToResponseDTO(booksEntity.get(0))).thenReturn(createBookResponseFromEntity(booksEntity.get(0)));

        // Invoke the method
        BookResponse result = bookService.getBookById(id);
        // Assert
        assertNotNull(result);
        assertEquals(id, result.getBookId());
        assertEquals(test.getBookTitle(), result.getBookTitle());
        assertEquals(test.getAuthor(), result.getAuthor());
        assertEquals(test.getBookPrice(), result.getBookPrice());
        assertEquals(test.getBookCategory(), result.getBookCategory());

        // Verify that method was called
        verify(bookRepository, times(1)).findById(id);
        verify(bookMapper, times(1)).entityToResponseDTO(any());
    }
    @DisplayName("findBooksByAuthor - success")
    @Test
    public void testFindBooksByAuthorSuccess() {

        List<Book> books = prepareBookMockList();
        String authorName = "Mock Author1";

        // Mock behavior of the bookRepository.findByAuthor method
        when(bookRepository.findByAuthor(authorName)).thenReturn(books);
        when(bookMapper.entityToResponseDTO(books.get(0))).thenReturn(createBookResponseFromEntity(books.get(0)));
        when(bookMapper.entityToResponseDTO(books.get(1))).thenReturn(createBookResponseFromEntity(books.get(1)));
        // Invoke the method / Act
        List<BookResponse> result = bookService.findBooksByAuthor(authorName);

        // Assert
        assertNotNull(result);
//        assertEquals(2, result.size());
//        assertEquals(books.size(), result.size());

        // Verify that method was called
        verify(bookRepository).findByAuthor(authorName);
        verify(bookMapper).entityToResponseDTO(books.get(0));
        verify(bookMapper).entityToResponseDTO(books.get(1));
    }

    @DisplayName("updateBookById - success")
    @Test
    void testUpdateBookByIdSuccess() {
        // Arrange
        Long id = 1L;
        List <Book> books = prepareBookMockList();
        List <BookRequest> booksDto = prepareBookDtoMockList();
        assertFalse(books.isEmpty(), "Expected non-empty list of books");

        Book existingBook = books.get(0);
        BookRequest updatedBook = booksDto.get(1);

        // Mock behavior of the bookRepository.findById and save method
        when(bookRepository.findById(id)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any())).thenAnswer(invocation ->
                invocation.getArgument(0)
        );
        when(bookMapper.entityToResponseDTO(existingBook)).thenAnswer(invocation ->
            createBookResponseFromEntity(invocation.getArgument(0))
        );

        // Invoke the method
        BookResponse result = bookService.updateBookById(id, updatedBook);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getBookId());
        assertEquals(updatedBook.getBookTitle(), result.getBookTitle());
        assertEquals(updatedBook.getAuthor(), result.getAuthor());
        assertEquals(updatedBook.getBookPrice(), result.getBookPrice());
        assertEquals(updatedBook.getBookCategory(), result.getBookCategory());

        // Verify that  methods were called
        verify(bookRepository).findById(id);
        verify(bookRepository).save(existingBook);
        verify(bookRepository, times(1)).findById(id);
        verify(bookRepository, times(1)).save(any());
        verify(bookMapper, times(1)).entityToResponseDTO(existingBook);
    }

    @DisplayName("updateBookById - book not found")
    @Test
    void testUpdateBookByIdBookNotFound() {

        Long id = 99L;
        List <BookRequest> books = prepareBookDtoMockList();
        BookRequest updatedBook = books.get(1);

        // Mock behavior of the bookRepository.findById method
        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        // Invoke the method
        BookResponse result = bookService.updateBookById(id, updatedBook);

        // Assert
        assertNull(result);

        // Verify that method was called
        verify(bookRepository).findById(id);
        verify(bookRepository, never()).save(any());
//        verify(bookMapper, never()).entityToDto(any());
    }






}
