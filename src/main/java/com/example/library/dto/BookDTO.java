package com.example.library.dto;

public class BookDTO {
    private Long id;
    private String title;
    private String isbn;
    private int publicationYear;
    private String authorName;
    private String categorieName;
    private String imagePath;
    private Long authorId;
    private Long categoryId;

    public BookDTO(Long id, String title, String isbn, int publicationYear,
                   String authorName, String categorieName, String imagePath,
                   Long authorId, Long categoryId) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.authorName = authorName;
        this.categorieName = categorieName;
        this.imagePath = imagePath;
        this.authorId = authorId;
        this.categoryId = categoryId;
    }

    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getIsbn() { return isbn; }
    public int getPublicationYear() { return publicationYear; }
    public String getAuthorName() { return authorName; }
    public String getCategorieName() { return categorieName; }
    public String getImagePath() { return imagePath; }
    public Long getAuthorId() { return authorId; }
    public Long getCategoryId() { return categoryId; }
}
