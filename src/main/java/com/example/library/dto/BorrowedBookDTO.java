package com.example.library.dto;

import java.time.LocalDate;

public class BorrowedBookDTO {
    private Long id;
    private Long bookId;
    private Long clientId;
    private String bookTitle;
    private String clientName;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    // Getters
    public Long getId() {
        return id;
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getClientId() {
        return clientId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getClientName() {
        return clientName;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
