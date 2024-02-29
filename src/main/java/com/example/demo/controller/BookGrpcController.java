package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.mapper.BookMapper;
import com.example.demo.proto.BookOuterClass;
import com.example.demo.proto.BookServiceGrpc;
import com.example.demo.repository.BookRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookGrpcController extends BookServiceGrpc.BookServiceImplBase {

    private final BookMapper bookMapper;
    private  final BookRepository bookRepository;

    @Autowired
    public BookGrpcController(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public void findBooksByCategory(
            BookOuterClass.CategoryRequest request,
            StreamObserver<BookOuterClass.CategoryResponse> responseObserver
    ){
        String requestedCategory = request.getCategory();
        List<Book> booksEntity = bookRepository.findByCategory(requestedCategory);
        List<BookOuterClass.Book> booksProtoList = bookMapper.entityListToProtoList(booksEntity);
        BookOuterClass.CategoryResponse response = BookOuterClass.CategoryResponse.newBuilder()
                .addAllBooks(booksProtoList)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findBookById(
            BookOuterClass.IdRequest request,
            StreamObserver<BookOuterClass.Book> responseObserver
    ) {
        long requestedId = request.getId();
        Optional<Book> bookEntity = bookRepository.findById(requestedId);

        if (bookEntity.isPresent()) {
            BookOuterClass.Book bookProto = bookMapper.entityToProto(bookEntity.get());
            responseObserver.onNext(bookProto);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(Status.NOT_FOUND.asRuntimeException());
        }
    }

}
