package br.com.rafael.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rafael.exceptions.ResourceNotFoundException;
import br.com.rafael.models.Book;
import br.com.rafael.repository.BookRepository;

@Service
public class BookServices {

    @Autowired
    BookRepository bookRepository;

    public Book findById(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        return book;
    }

    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }
    
	public Book create(Book book) {
		return this.bookRepository.save(book);
	}

	public void deletPerson(Long id) {
		this.bookRepository.deleteById(id);;
	}
}
