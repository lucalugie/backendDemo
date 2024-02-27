package com.example.demo.mapper;

import com.example.demo.dto.BookResponse;
import com.example.demo.dto.BookRequest;
import com.example.demo.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "id", target = "bookId")
    @Mapping(source = "title", target = "bookTitle")
    @Mapping(source = "price", target = "bookPrice")
    @Mapping(source = "category", target = "bookCategory")
    BookResponse entityToResponseDTO(Book book);

    @Mapping(source = "bookId", target = "id")
    @Mapping(source = "bookTitle", target = "title")
    @Mapping(source = "bookPrice", target = "price")
    @Mapping(source = "bookCategory", target = "category")
    Book requestDTOToEntity(BookRequest bookDto);
}

//@Mapper(componentModel = "spring")
