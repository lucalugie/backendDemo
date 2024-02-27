package com.example.demo.controller;//package com.example.demoLugie.controller;
//
//import com.example.demoLugie.entity.Book;
//import com.example.demoLugie.service.BookService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//
//public class BookControllerTest {
//    @Mock
//    private BookService bookService;
//
//    @InjectMocks
//    private BookController bookController;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private List<Book> mockBookList;
//
//    @BeforeEach
//    void setup() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
//        mockBookList = Arrays.asList(
//                createMockBook(1L, "Lone Wolf", "Mock Author1", "Fantasy", 39.99),
//                createMockBook(2L, "Beginning After The End", "Mock Author1", "Fantasy", 49.99),
//                createMockBook(3L, "Death Flags", "Mock Author2", "Horror", 49.99)
//        );
//    }
//
//    @DisplayName("getBookById-case: success")
//    @Test
//    public void testGetBookById() throws Exception {
//
//        when(bookService.getBookById(1L)).thenReturn(mockBookList.get(0));
//
//        // Perform the request and assert the response
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Lone Wolf"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Mock Author1"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(39.99));
//    }
//
//    @DisplayName("updateBook-case: success")
//    @Test
//    void testUpdateBook() throws Exception {
//
//        Mockito.when(bookService.updateBookById(1L, mockBookList.get(0))).thenReturn(mockBookList.get(0));
//
//        String jsonResponse = mockMvc.perform(MockMvcRequestBuilders.patch("/api/books/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(mockBookList.get(0))))
////                .andExpect(MockMvcResultMatchers.status().isOk())
//
//                .andReturn().getResponse().getContentAsString();
//
//        System.out.println("Actual JSON response: " + jsonResponse);
//
////                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
////                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("New Title"))
////                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("New Author"))
////                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("New Category"))
////                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(49.99));
//    }
//
//
//    private Book createMockBook(Long id, String title, String author, String category, double price) {
//        Book mockBook = new Book();
//        mockBook.setId(id);
//        mockBook.setTitle(title);
//        mockBook.setAuthor(author);
//        mockBook.setCategory(category);
//        mockBook.setPrice(price);
//        return mockBook;
//    }
//
//
//    private static String asJsonString(Object obj) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.writeValueAsString(obj);
//    }
//
//}
