package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wcci.blog.models.Author;
import org.wcci.blog.storage.AuthorStorage;

import java.util.Collection;
import java.util.Optional;

@Controller
public class AuthorController {
    private AuthorStorage authorStorage;

    public AuthorController(AuthorStorage authorStorage) {
        this.authorStorage = authorStorage;

    }

    @RequestMapping("/authors")
    public String displayAuthors(Model model) {
        Collection<Author> authors = authorStorage.getAll();
        model.addAttribute("authors", authors);
        return "authors-view";
    }

    @RequestMapping("/{authorId}")
    public String displaySingleAuthor(@PathVariable Long authorId, Model model) {
        Optional<Author> retrievedAuthor = authorStorage.findAuthorById(authorId);
        model.addAttribute("author", retrievedAuthor.get());
        return "author";
    }

    @PostMapping("add-author")
    public String AddAuthorForm(@RequestParam String authorName) {
        authorStorage.store(new Author(authorName));
        return "redirect:/authors";
    }


}
