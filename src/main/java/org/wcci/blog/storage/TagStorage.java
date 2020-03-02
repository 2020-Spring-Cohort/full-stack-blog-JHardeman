package org.wcci.blog.storage;

import org.wcci.blog.models.Tag;

import java.util.Collection;

public interface TagStorage {

    Collection<Tag> getAll();

    void store(Tag tag);

    Tag findTagById(Long id);

    Collection<Tag> findAllTags();

    Tag findTagByName(String name);

    Tag findTagById(long id);
}
