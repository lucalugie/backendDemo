package com.example.demo.controller;

import com.example.demo.proto.BookServiceGrpc;
import org.springframework.stereotype.Service;

@Service
public class BookGrpcController extends BookServiceGrpc.BookServiceImplBase {
//    @Override
//    public void findBooksByCategory();
}
