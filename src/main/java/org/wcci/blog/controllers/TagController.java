package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storage.TagStorage;

@Controller
public class TagController {
    private TagStorage tagStorage;

    public TagController(TagStorage tagStorage) {

        this.tagStorage = tagStorage;
    }

    @RequestMapping("/tags")
    public String displaytag(Model model) {
        model.addAttribute("tags", tagStorage.findAllTags());
        return "tags-view";
    }

    @RequestMapping("/tags/{id}")
    public String displayTag(@PathVariable Long id, Model model) {
        Tag retrievedTag = tagStorage.findTagById(id);
        model.addAttribute("tag", retrievedTag);
        return "tag";
    }

    @PostMapping("/add-tag")
    public String addCategory(@RequestParam String tag) {
        tagStorage.store(new Tag(tag));
        return "redirect:tags";

    }
}