package org.wcci.blog.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Post;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storage.PostStorage;
import org.wcci.blog.storage.repositories.TagRepository;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PostControllerTest {
    private PostController underTest;
    private Model model;
    private PostStorage mockStorage;
    private Post testPost;
    private Author author;
    private Category category;
    private Tag tags;

    @BeforeEach
    void setUp(){
        mockStorage = mock(PostStorage.class);
        TagRepository tagRepository = mock(TagRepository.class);
        underTest = new PostController(mockStorage, tagRepository);
        model = mock(Model.class);
        Category testCategory = new Category("Music");
        Tag testTag = new Tag("Cool");
        testPost = new Post("title", "body", author, category, tags);
        when(mockStorage.findPostById(1L)).thenReturn(testPost);

    }

    @Test
    public void displayPostInteractsWithDependenciesCorrectly(){
        underTest.displayPost(1L, model);
        verify(mockStorage).findPostById(1);
        verify(model).addAttribute("post", testPost);
    }

    @Test
    public void displayPostMappingIsCorrect() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/posts/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("post-view"))
                .andExpect(model().attributeExists("post"))
                .andExpect(model().attribute("post", testPost));


    }

}
