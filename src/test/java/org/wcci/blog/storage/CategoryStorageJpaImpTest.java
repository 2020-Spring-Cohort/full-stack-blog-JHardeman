package org.wcci.blog.storage;

import org.junit.jupiter.api.Test;
import org.wcci.blog.models.Category;
import org.wcci.blog.storage.repositories.CategoryRepository;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CategoryStorageJpaImpTest {

    @Test
    public void shouldFindAllCategories(){
        CategoryRepository mockCategoryRepository = mock(CategoryRepository.class);
        Category testCategory = new Category("Test");
        CategoryStorage underTest = new CategoryStorageJpaImp(mockCategoryRepository);
        when(mockCategoryRepository.findAll()).thenReturn(Collections.singletonList(testCategory));
        underTest.store(testCategory);
        verify(mockCategoryRepository).save(testCategory);
        assertThat(underTest.findAllCategories().contains(testCategory));


    }
}
