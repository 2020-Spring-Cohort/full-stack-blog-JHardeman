package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storage.PostStorage;
import org.wcci.blog.models.Post;
import org.wcci.blog.storage.repositories.TagRepository;


@Controller
public class PostController {

    private PostStorage postStorage;

    public PostController(PostStorage postStorage, TagRepository tagRepository) {

        this.postStorage = postStorage;
    }

    @RequestMapping("/posts")
    public String displayPosts(Model model) {
        model.addAttribute("posts", postStorage.getAll());
        return "post-view";
    }

    @RequestMapping("/post/{id}")
    public String displayPost(@PathVariable Long id, Model model){
        Post retrievedPost = postStorage.findPostById(id);
        model.addAttribute("post", retrievedPost);
        return "post";
    }

    @PostMapping("/add-post")
    public String addReview(@RequestParam String title, String body, Author author, Category category, Tag... tag) {
        postStorage.store(new Post(body, title, author, category));
        return "redirect:post";
    }


}