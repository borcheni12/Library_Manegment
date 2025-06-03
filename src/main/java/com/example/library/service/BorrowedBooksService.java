package com.example.library.service;

import com.example.library.dto.BorrowedBookDTO;
import com.example.library.model.Book;
import com.example.library.model.BorrowedBooks;
import com.example.library.model.Client;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowedBooksRepository;
import com.example.library.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowedBooksService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BorrowedBooksRepository borrowedBooksRepository;

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Client findClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
    }

    public List<BorrowedBookDTO> getAllBorrowedBooks() {
        List<BorrowedBooks> borrowedBooksList = borrowedBooksRepository.findAll();

        return borrowedBooksList.stream().map(b -> {
            BorrowedBookDTO dto = new BorrowedBookDTO();
            dto.setId(b.getId());

            // ⚠️ تحقق من أن الكتاب والعميل غير null قبل الاستخدام
            if (b.getBook() != null) {
                dto.setBookTitle(b.getBook().getTitle());
            } else {
                dto.setBookTitle("Unknown Book");
            }

            if (b.getClient() != null) {
                dto.setClientName(b.getClient().getName());
            } else {
                dto.setClientName("Unknown Client");
            }

            dto.setBorrowDate(b.getBorrowDate());
            dto.setReturnDate(b.getReturnDate());
            return dto;
        }).collect(Collectors.toList());
    }

    public BorrowedBooks findById(Long id) {
        return borrowedBooksRepository.findById(id).orElse(null);
    }

    public BorrowedBooks save(BorrowedBooks loan) {
        return borrowedBooksRepository.save(loan);
    }



    public BorrowedBooks getBorrowedBookById(Long id) {
        return borrowedBooksRepository.findById(id).orElse(null);
    }

    public BorrowedBooks saveBorrowedBook(BorrowedBooks borrowedBook) {
        return borrowedBooksRepository.save(borrowedBook);
    }

    public void deleteBorrowedBook(Long id) {
        borrowedBooksRepository.deleteById(id);
    }
    public void addBorrowedBook(BorrowedBookDTO dto) {
        BorrowedBooks borrowed = new BorrowedBooks();
        borrowed.setBook(bookRepository.findById(dto.getBookId()).orElseThrow(() -> new RuntimeException("Book not found")));
        borrowed.setClient(clientRepository.findById(dto.getClientId()).orElseThrow(() -> new RuntimeException("Client not found")));
        borrowed.setBorrowDate(dto.getBorrowDate());
        borrowed.setReturnDate(dto.getReturnDate());
        borrowedBooksRepository.save(borrowed);
    }

    public void updateBorrowedBook(BorrowedBookDTO dto) {
        BorrowedBooks borrowed = borrowedBooksRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Loan not found"));
        borrowed.setBook(bookRepository.findById(dto.getBookId()).orElseThrow(() -> new RuntimeException("Book not found")));
        borrowed.setClient(clientRepository.findById(dto.getClientId()).orElseThrow(() -> new RuntimeException("Client not found")));
        borrowed.setBorrowDate(dto.getBorrowDate());
        borrowed.setReturnDate(dto.getReturnDate());
        borrowedBooksRepository.save(borrowed);
    }


}
