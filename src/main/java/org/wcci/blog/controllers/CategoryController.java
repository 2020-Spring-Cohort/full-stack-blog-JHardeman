package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.models.Category;

import java.util.Optional;

@Controller
public class CategoryController {
    private CategoryStorage categoryStorage;

    public CategoryController(CategoryStorage categoryStorage) {

        this.categoryStorage = categoryStorage;
    }

    @RequestMapping("/categories")
    public String displayCategories(Model model) {
        model.addAttribute("categories", categoryStorage.findAllCategories());
        return "categories-view";
    }

    @GetMapping("/categories/{id}")
    public String displaySingleCategory(@PathVariable Long id, Model model) {
        Optional<Category> retrievedCategory = categoryStorage.findCategoryById(id);
        model.addAttribute("category", retrievedCategory);

        return "category";

    }
    @PostMapping("/add-category")
    public String  addCategory(@RequestParam String name) {
        categoryStorage.store(new Category(name));
        return "redirect:categories";


    }
}