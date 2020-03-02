package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.models.Category;
import org.wcci.blog.storage.repositories.CategoryRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class CategoryStorageJpaImp implements CategoryStorage {
    private CategoryRepository categoryRepository;

    public CategoryStorageJpaImp(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;

    }

    @Override
    public Collection<Category> findAllCategories(){
        return (Collection<Category>) categoryRepository.findAll();
    }

    @Override
    public void store(Category category){
        categoryRepository.save(category);
    }

    @Override
    public Category findCategoryByName(String name){
        return categoryRepository.findByName(name).get();
    }

    @Override
    public Optional<Category> findCategoryById(Long id){
        return categoryRepository.findById(id);
    }

}
