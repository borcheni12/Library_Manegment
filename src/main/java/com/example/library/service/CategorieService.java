package com.example.library.service;

import com.example.library.model.Categorie;
import com.example.library.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    public  List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Categorie getCategoryById(Long id) {
        return categorieRepository.findById(id).orElse(null);
    }

    public Categorie saveCategory(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public void deleteCategory(Long id) {
        categorieRepository.deleteById(id);
    }
}
