package org.wcci.blog.storage;


import org.springframework.stereotype.Service;
import org.wcci.blog.models.Author;
import org.wcci.blog.storage.repositories.AuthorRepository;


import java.util.Collection;
import java.util.Optional;

@Service
public class AuthorStorageJpaImp implements AuthorStorage {

   AuthorRepository authorRepo;

   public AuthorStorageJpaImp (AuthorRepository authorRepo){
       this.authorRepo = authorRepo;
   }


    @Override
    public Collection<Author> getAll() {
        return (Collection<Author>) authorRepo.findAll();
    }

    @Override
    public void store(Author author) {
       authorRepo.save(author);

    }

    @Override
    public Author findAuthorByName(String name) {
        return authorRepo.findByName(name).get();
    }

    @Override
    public Optional<Author> findAuthorById(Long authorId) {
        return authorRepo.findById(authorId);
    }
}
