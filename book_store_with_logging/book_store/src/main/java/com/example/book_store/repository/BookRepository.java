package com.example.book_store.repository;

import com.example.book_store.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {

}
