package br.com.rafael.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 

import br.com.rafael.models.Book;
import br.com.rafael.services.BookServices;
import br.com.rafael.util.MediaType;

@RestController
@RequestMapping("/api/book/v1")
public class BookController {
    @Autowired
    private BookServices bookServices;
    
    @CrossOrigin(origins = {"http://localhost:8080"})
    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public List<Book> findAll() {
        return this.bookServices.findAll();
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML })
    public Book findById(@PathVariable(value = "id") Long id) {
        return this.bookServices.findById(id);
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    public Book createBook(@RequestBody Book book) throws Exception {
        return this.bookServices.create(book);
    }
}
