package com.example.library.controller;

import com.example.library.dto.BookDTO;
import com.example.library.model.Book;
import com.example.library.service.AuthorService;

import com.example.library.service.CategorieService;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategorieService categorieService;


    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/addmewbook")
    public String saveBookFromForm(
            @RequestParam("title") String title,
            @RequestParam("isbn") String isbn,
            @RequestParam("publicationYear") int publicationYear,
            @RequestParam("authorId") Long authorId,
            @RequestParam("categorieId") Long categorieId,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
    ) {
        bookService.saveBook(title, isbn, publicationYear, authorId, categorieId, imageFile);
        return "redirect:/books";
    }

    @GetMapping
    public String listBooks(Model model) {
        List<BookDTO> books = bookService.getAllBookDTOs();
        model.addAttribute("books", books);
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("categories", categorieService.getAllCategories());
        return "books"; // resources/templates/books.html
    }
    @GetMapping("/addmewbook")
    public String showAddBookForm(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("categories", categorieService.getAllCategories());
        return "addmewbook"; // سيبحث عن resources/templates/addnewbook.html
    }
    // تعديل كتاب
    @PostMapping("/update")
    public String updateBook(@RequestParam("id") Long id,
                             @RequestParam("title") String title,
                             @RequestParam("isbn") String isbn,
                             @RequestParam("publicationYear") int publicationYear,
                             @RequestParam("authorId") Long authorId,
                             @RequestParam("categorieId") Long categorieId,
                             @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {

        bookService.updateBook(id, title, isbn, publicationYear, authorId, categorieId, imageFile);
        return "redirect:/books";
    }


    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteBookAjax(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "success";
    }

}
