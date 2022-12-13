package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // POST,
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book post(@RequestBody @Valid Book book) {
        return bookService.addBookToCatalog(book);
    }
    // GET,
    @GetMapping
    public Iterable<Book> get() {
        return bookService.viewAllBooksInCatalog();
    }

    @GetMapping("{isbn}")
    public Book getByIsbn(@PathVariable String isbn) {
        return bookService.viewBookDetails(isbn);
    }
    // PUT,
    @PutMapping("{isbn}")
    public Book put(@PathVariable String isbn, @RequestBody @Valid Book book) {
        return bookService.editBookDetails(isbn, book);
    }
    // DELETE
    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String isbn) {
        bookService.removeBookFromCatalog(isbn);
    }
}
