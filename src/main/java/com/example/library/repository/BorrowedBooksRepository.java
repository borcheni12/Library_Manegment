package com.example.library.repository;

import com.example.library.model.BorrowedBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.ClientRepository;



public interface BorrowedBooksRepository extends JpaRepository<BorrowedBooks, Long> {

}
