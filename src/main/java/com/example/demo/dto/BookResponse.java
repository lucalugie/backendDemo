package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class BookResponse {
    private Long bookId;
    private String bookTitle;
    private String author;
    private Double bookPrice;
    private String bookCategory;

}