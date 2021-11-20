package br.com.rosana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rosana.data.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
