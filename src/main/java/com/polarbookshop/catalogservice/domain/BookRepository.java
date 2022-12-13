package com.polarbookshop.catalogservice.domain;

import java.util.Optional;

public interface BookRepository {
    Book save(Book book);

    Iterable<Book> findAll();

    Optional<Book> findByIsbn(String isbn);

    void deleteByIsbn(String isbn);

    boolean existsByIsbn(String isbn);
}
