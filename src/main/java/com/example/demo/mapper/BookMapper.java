package com.example.demo.mapper;

import com.example.demo.dto.BookResponse;
import com.example.demo.dto.BookRequest;
import com.example.demo.entity.Book;
import com.example.demo.proto.BookOuterClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
@Component
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    // Entity to DTO
    @Mapping(source = "id", target = "bookId")
    @Mapping(source = "title", target = "bookTitle")
    @Mapping(source = "price", target = "bookPrice")
    @Mapping(source = "category", target = "bookCategory")
    BookResponse entityToResponseDTO(Book book);

    // DTO to Entity
    @Mapping(source = "bookId", target = "id")
    @Mapping(source = "bookTitle", target = "title")
    @Mapping(source = "bookPrice", target = "price")
    @Mapping(source = "bookCategory", target = "category")
    Book requestDTOToEntity(BookRequest bookDto);

    // Entity to Proto
    BookOuterClass.Book entityToProto(Book book);

    // Entity list to Proto list
    default List<BookOuterClass.Book> entityListToProtoList(List<Book> books) {
        return books.stream()
                .map(this::entityToProto)
                .collect(Collectors.toList());
    }

    ///Hi my name
    //say

}


