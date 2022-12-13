package com.polarbookshop.catalogservice.domain;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // CRUD
    // Create
    public Book addBookToCatalog(Book book) {
        if (bookRepository.existsByIsbn(book.isbn())) {
            throw new BookAlreadyExistsException(book.isbn());
        }
        return bookRepository.save(book);
    }
    // Read
    public Iterable<Book> viewAllBooksInCatalog() {
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }
    // Update
    public Book editBookDetails(String isbn, Book book) {
        return bookRepository.findByIsbn(isbn)
                .map(existingBook -> {
                    var updatedBook = new Book(
                            existingBook.isbn(),
                            book.title(),
                            book.author(),
                            book.price()
                    );
                    return bookRepository.save(updatedBook);
                }).orElseGet(() -> addBookToCatalog(book));
        }
    // Delete
    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

}
