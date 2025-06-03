package com.example.library.dto;

public class UpdateLoanRequestDto {
    private String borrowDate;
    private String returnDate;
    private Long bookId;
    private Long clientId;

    // Getters
    public String getBorrowDate() {
        return borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getClientId() {
        return clientId;
    }

    // Setters
    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
