package com.example.book_store.service;

import com.example.book_store.model.BookModel;
import com.example.book_store.repository.BookRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    public List<BookModel> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<BookModel> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public boolean validateInput(BookModel book) {
        if(StringUtils.isEmpty(book.getPenulis()) || StringUtils.isEmpty(book.getJudul()) || book.getTahunTerbit() < 0)
            return true;
        return false;

    }

    public void save(BookModel book) {
        bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
