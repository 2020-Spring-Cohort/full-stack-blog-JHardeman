package org.wcci.blog.storage;

import org.wcci.blog.models.Author;

import java.util.Collection;
import java.util.Optional;

public interface AuthorStorage {

    Collection<Author> getAll();
    void store(Author author);

    Author findAuthorByName(String name);

    Optional<Author> findAuthorById(Long authorId);
}
