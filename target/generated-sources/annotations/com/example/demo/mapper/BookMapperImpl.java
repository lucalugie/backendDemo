package com.example.demo.mapper;

import com.example.demo.dto.BookRequest;
import com.example.demo.dto.BookResponse;
import com.example.demo.entity.Book;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-27T23:39:22+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.1 (Azul Systems, Inc.)"
)
public class BookMapperImpl implements BookMapper {

    @Override
    public BookResponse entityToResponseDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        BookResponse bookResponse = new BookResponse();

        bookResponse.setBookId( book.getId() );
        bookResponse.setBookTitle( book.getTitle() );
        bookResponse.setBookPrice( book.getPrice() );
        bookResponse.setBookCategory( book.getCategory() );
        bookResponse.setAuthor( book.getAuthor() );

        return bookResponse;
    }

    @Override
    public Book requestDTOToEntity(BookRequest bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( bookDto.getBookId() );
        book.setTitle( bookDto.getBookTitle() );
        book.setPrice( bookDto.getBookPrice() );
        book.setCategory( bookDto.getBookCategory() );
        book.setAuthor( bookDto.getAuthor() );

        return book;
    }
}
