package org.wcci.blog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.PostStorage;
import org.wcci.blog.storage.TagStorage;

@Component
public class Populator implements CommandLineRunner {
    private PostStorage postStorage;
    private CategoryStorage categoryStorage;
    private AuthorStorage authorStorage;
    private TagStorage tagStorage;

    public Populator(PostStorage postStorage, CategoryStorage categoryStorage, AuthorStorage authorStorage,
                     TagStorage tagStorage) {
        this.postStorage = postStorage;
        this.categoryStorage = categoryStorage;
        this.authorStorage = authorStorage;
        this.tagStorage = tagStorage;
    }

    @Override
    public void run(String...args){
        Author author1 = new Author("Jon");
        authorStorage.store(author1);
        Author author2 = new Author("Mike");
        authorStorage.store(author2);

        Category music = new Category("Music");
        categoryStorage.store(music);
        Category sports = new Category("Sports");
        categoryStorage.store(sports);

        Tag tag1 = new Tag("Cool");
        Tag tag2 = new Tag("Awesome");
        Tag tag3 = new Tag("Dope");
        tagStorage.store(tag1);
        tagStorage.store(tag2);
        tagStorage.store(tag3);


    }
}
