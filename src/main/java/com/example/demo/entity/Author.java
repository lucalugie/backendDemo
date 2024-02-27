package com.example.demo.entity;//package com.example.demoLugie.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.Set;
//
//@Entity
//@Table(name = "author")
//@Getter
//@Setter
//public class Author {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//
//    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
//    private Set<Book> books;
//}
