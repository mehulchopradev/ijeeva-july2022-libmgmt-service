package com.ijeeva.libmgmtservice.controllers;

import com.ijeeva.libmgmtservice.dao.BookRepository;
import com.ijeeva.libmgmtservice.entities.Book;
import com.ijeeva.libmgmtservice.exceptions.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable Long bookId) {
        Optional<Book> optional = this.bookRepository.findById(bookId);
        if (optional.isPresent()) {
            return optional.get();
        }

        throw new BookNotFoundException(String.format("book with id %s not found", bookId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createNewBook(@RequestBody Book book) {
        return this.bookRepository.save(book);
    }

    @PutMapping("/{bookId}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long bookId) {
        Optional<Book> optional = this.bookRepository.findById(bookId);
        if (optional.isPresent()) {
            Book book1 = optional.get();
            book1.setTitle(book.getTitle());
            book1.setPrice(book.getPrice());
            book1.setPages(book.getPages());

            return this.bookRepository.save(book1);
        }

        throw new BookNotFoundException(String.format("book with id %s not found", bookId));
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long bookId) {
        this.bookRepository.deleteById(bookId);
    }
}
