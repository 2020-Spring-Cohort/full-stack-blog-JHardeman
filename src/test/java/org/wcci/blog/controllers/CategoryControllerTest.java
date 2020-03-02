package org.wcci.blog.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.models.Category;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.PostStorage;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

public class CategoryControllerTest {

    private MockMvc mockMvc;
    private CategoryController underTest;
    private CategoryStorage categoryStorage;
    private Model mockModel;
    private PostStorage postStorage;
    private AuthorStorage authorStorage;


    @BeforeEach
    public void setUp() {
        mockModel = mock(Model.class);
        categoryStorage = mock(CategoryStorage.class);
        underTest = new CategoryController(categoryStorage);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    public void shouldReturnViewNamedCategoryViewWhenDisplaySingleCategoryIsCalled() {
        String viewName = underTest.displaySingleCategory(1L, mockModel);
        assertThat(viewName).isEqualTo("category");
    }

    @Test
    public void shouldGoToIndividualEndPoint() throws Exception {
        Category testCategory = new Category("TEST");
        when(categoryStorage.findCategoryByName("TEST")).thenReturn(testCategory);
        mockMvc.perform(get("/categories/TEST"))
                .andExpect(status().isOk())
                .andExpect(view().name("category-view"))
                .andExpect(model().attributeExists("category"))
                .andExpect(model().attribute("category", testCategory));
    }

    @Test
    public void categoriesEndPointDisplaysAllCategories() throws Exception {
        Category testCategory = new Category("Test");

        List<Category> categoryCollection = Collections.singletonList(testCategory);
        when(categoryStorage.findAllCategories()).thenReturn(categoryCollection);
        mockMvc.perform(get("/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("categories-view"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("categories", categoryCollection));
    }

    @Test
    public void addCategoryShouldRedirectToCategoriesEndPoint() {
        String result = underTest.addCategory("Test");
        assertThat(result).isEqualTo("redirect:categories");
    }

    @Test
    public void addCategoryShouldStoreANewCategory() {
        underTest.addCategory("Test");
        verify(categoryStorage).store(new Category("Test"));
    }
}
