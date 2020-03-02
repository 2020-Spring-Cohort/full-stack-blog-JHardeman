package org.wcci.blog.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Post;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storage.repositories.AuthorRepository;
import org.wcci.blog.storage.repositories.CategoryRepository;
import org.wcci.blog.storage.repositories.PostRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PostStorageJpaImpTest {

    private AuthorRepository authorRepository;
    private CategoryRepository categoryRepository;
    private PostRepository postRepository;
    private PostStorage postStorage;
    private Post post;
    private Tag tags;

    @BeforeEach
    void setUp(){
        authorRepository = mock(AuthorRepository.class);
        categoryRepository = mock(CategoryRepository.class);
        postRepository = mock(PostRepository.class);
        postStorage = new PostStorageJpaImpl(authorRepository, categoryRepository, postRepository);
        Category category = new Category("Test");
        Author author = new Author("Jon");
        Post post = new Post("title", "body", author, category, tags);

    }

    @Test
    public void shouldFindPostById(){
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        Post retrievedPost = postStorage.findPostById(1L);
        assertThat(retrievedPost).isEqualTo(post);
    }

    @Test
    public void shouldStorePost(){
        postStorage.store(post);
        verify(authorRepository).save(post.getAuthor());
        verify(categoryRepository).save(post.getCategory());
        verify(postRepository).save(post);
    }


}
