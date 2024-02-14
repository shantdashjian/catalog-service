package com.cloudnativebookshop.catalogservice.domain;

import java.util.Optional;

public interface BookRepository {
	Iterable<Book> findAll();

	public Optional<Book> findByIsbn(String isbn);

	boolean existsByIsbn(String isbn);

	Book save(Book book);

	void deleteByIsbn(String isbn);
}
