package com.example.book_store.controller;

import com.example.book_store.aspect.LogData;
import com.example.book_store.model.BookModel;
import com.example.book_store.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @LogData
    @GetMapping("/buku")
    public ResponseEntity getBook() {
        List<BookModel> books = bookService.getAllBooks();

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @LogData
    @GetMapping("/buku/{id}")
    public ResponseEntity getBook(@PathVariable("id") Long id) {
        Optional<BookModel> bookOpt = bookService.getBookById(id);

        return bookOpt.map(bookModel -> new ResponseEntity<>(bookModel, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @LogData
    @PostMapping("/buku")
    public ResponseEntity createBook(@RequestBody BookModel book) {
        if(bookService.validateInput(book))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        bookService.save(book);
        return new ResponseEntity(HttpStatus.OK);
    }

    @LogData
    @PutMapping("/buku/{id}")
    public ResponseEntity editBook(@PathVariable("id") Long id,
                                   @RequestBody BookModel book) {
        book.setId(id);
        if(bookService.validateInput(book))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<BookModel> bookOpt = bookService.getBookById(id);
        if(bookOpt.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        bookService.save(book);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @LogData
    @DeleteMapping("/buku/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        Optional<BookModel> bookOpt = bookService.getBookById(id);

        if(bookOpt.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
