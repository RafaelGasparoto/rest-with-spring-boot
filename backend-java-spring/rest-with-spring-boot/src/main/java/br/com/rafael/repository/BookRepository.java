package br.com.rafael.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rafael.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
