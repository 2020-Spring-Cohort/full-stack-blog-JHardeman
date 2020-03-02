package org.wcci.blog.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storage.TagStorage;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

public class TagControllerTest {
    private TagController underTest;
    private Model model;
    private TagStorage mockStorage;
    private Tag testTag;


    @BeforeEach
    void setUp() {
        mockStorage = mock(TagStorage.class);
        underTest = new TagController(mockStorage);
        model = mock(Model.class);
        Category testCategory = new Category("Test");
        testTag = new Tag("Cool");
        when(mockStorage.findTagById(1L)).thenReturn(testTag);
    }

    @Test
    public void displayTagReturnsTagTemplate(){
        String result = underTest.displayTag(1L, model);
        assertThat(result).isEqualTo("tags-view");

    }
    @Test
    public void displayTagInteractsWithDependenciesCorrectly(){
        underTest.displayTag(1L,model);
        verify(mockStorage).findTagById(1L);
        verify(model).addAttribute("tag", testTag);
    }

    @Test
    public void displayPostMappingIsCorrect() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        List<Tag> mockTag = Collections.singletonList(testTag);
        when(mockStorage.findAllTags()).thenReturn(mockTag);
        mockMvc.perform(MockMvcRequestBuilders.get("/tags/"))
                .andExpect(status().isOk())
                .andExpect(view().name("tags-view"))
                .andExpect(model().attributeExists("tags"))
                .andExpect(model().attribute("tags", mockTag));
    }



}