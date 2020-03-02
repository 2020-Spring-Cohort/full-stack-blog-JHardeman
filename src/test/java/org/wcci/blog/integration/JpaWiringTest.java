package org.wcci.blog.integration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.wcci.blog.storage.repositories.AuthorRepository;
import org.wcci.blog.storage.repositories.CategoryRepository;
import org.wcci.blog.storage.repositories.PostRepository;
import org.wcci.blog.storage.repositories.TagRepository;


@DirtiesContext
@DataJpaTest
public class JpaWiringTest {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TestEntityManager entityManager;

}