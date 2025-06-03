package com.example.library.controller;

import com.example.library.dto.BorrowRequestDto;
import com.example.library.dto.BorrowedBookDTO;
import com.example.library.dto.UpdateLoanRequestDto;
import com.example.library.model.Book;
import com.example.library.model.BorrowedBooks;
import com.example.library.model.Client;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowedBooksRepository;
import com.example.library.repository.ClientRepository;
import com.example.library.service.BookService;
import com.example.library.service.ClientService;
import com.example.library.service.BorrowedBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/borrowed")
public class BorrowedBooksController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ClientService clientService;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BorrowedBooksRepository borrowedBooksRepository;

    @Autowired
    private BorrowedBooksService borrowedBooksService;

    @GetMapping("")
    public String showBorrowedBooks(Model model) {
        model.addAttribute("borrowedBooks", borrowedBooksService.getAllBorrowedBooks());
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("books", bookService.getAllBooks());
        return "loan-history";
    }

    @PostMapping("/add")
    public String addBorrowedBook(@ModelAttribute BorrowedBookDTO dto) {
        borrowedBooksService.addBorrowedBook(dto);
        return "redirect:/borrowed";
    }

    @PostMapping("/update")
    public String updateBorrowedBook(@ModelAttribute BorrowedBookDTO dto) {
        borrowedBooksService.updateBorrowedBook(dto);
        return "redirect:/borrowed";
    }

    @PostMapping("/delete")
    public String deleteBorrowedBook(@RequestParam Long id) {
        borrowedBooksService.deleteBorrowedBook(id);
        return "redirect:/borrowed";
    }











}
