package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.models.Post;
import org.wcci.blog.storage.repositories.AuthorRepository;
import org.wcci.blog.storage.repositories.CategoryRepository;
import org.wcci.blog.storage.repositories.PostRepository;

import java.util.Collection;

@Service
class PostStorageJpaImpl implements PostStorage {

    PostRepository postRepository;
    AuthorRepository authorRepository;
    CategoryRepository categoryRepository;

    public PostStorageJpaImpl(AuthorRepository authorRepository, CategoryRepository categoryRepository, PostRepository postRepository) {
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.postRepository = postRepository;

    }

    @Override
    public Collection<Post> getAll() {
        return (Collection<Post>) postRepository.findAll();
    }

    @Override
    public void store(Post post) {

        authorRepository.save(post.getAuthor());
        categoryRepository.save(post.getCategory());
        postRepository.save(post);


    }

    @Override
    public Post findPostById(long id) {

        return postRepository.findById(id).get();

    }

    @Override
    public Post findPostByTitle(String title) {

        return postRepository.findByTitle(title);
    }

    @Override
    public Post findPostById(Long id) {
        return postRepository.findById(id).get();
    }

}

