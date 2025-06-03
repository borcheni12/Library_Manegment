package com.example.library.controller;


import org.springframework.ui.Model;

import com.example.library.model.Author;
import com.example.library.model.Categorie;
import com.example.library.service.AuthorService;
import com.example.library.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/settings")
public class SystemSettingsController {

    @Autowired
    private CategorieService categorieService;

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public String showSettings(Model model) {
        model.addAttribute("categories", categorieService.getAllCategories());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("newCategory", new Categorie());
        model.addAttribute("newAuthor", new Author());
        return "settings";
    }

    @PostMapping("/add-category")
    public String addCategory(@ModelAttribute("newCategory") Categorie categorie) {
        categorieService.saveCategory(categorie);
        return "redirect:/settings";
    }

    @PostMapping("/add-author")
    public String addAuthor(@ModelAttribute("newAuthor") Author author) {
        authorService.saveAuthor(author);
        return "redirect:/settings";
    }

    @PostMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categorieService.deleteCategory(id);
        return "redirect:/settings";
    }

    @PostMapping("/delete-author/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/settings";
    }
}
