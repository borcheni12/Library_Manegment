package com.example.library.controller;

import com.example.library.model.Categorie;
import com.example.library.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    @GetMapping
    public List<Categorie> getAllCategories() {
        return categorieService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Categorie getCategory(@PathVariable Long id) {
        return categorieService.getCategoryById(id);
    }

    @PostMapping
    public Categorie createCategory(@RequestBody Categorie categorie) {
        return categorieService.saveCategory(categorie);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categorieService.deleteCategory(id);
    }
}
