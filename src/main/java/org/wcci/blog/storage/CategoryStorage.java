package org.wcci.blog.storage;


import org.wcci.blog.models.Category;

import java.util.Collection;
import java.util.Optional;

public interface CategoryStorage {

    Collection<Category> findAllCategories();

    void store (Category category);

    Category findCategoryByName(String name);

    Optional<Category> findCategoryById(Long id);
}
