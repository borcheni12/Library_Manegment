package com.example.library.service;

import com.example.library.dto.BookDTO;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.Categorie;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {


    @Autowired private AuthorRepository authorRepository;
    @Autowired private CategorieRepository categorieRepository;
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<BookDTO> getAllBookDTOs() {
        return bookRepository.findAll().stream().map(book ->
                new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getIsbn(),
                        book.getPublicationYear(),
                        book.getAuthor() != null ? book.getAuthor().getName() : "Unknown Author",
                        book.getCategorie() != null ? book.getCategorie().getName() : "Uncategorized",
                        book.getImagePath(),
                        book.getAuthor() != null ? book.getAuthor().getId() : null,
                        book.getCategorie() != null ? book.getCategorie().getId() : null
                )
        ).collect(Collectors.toList());
    }


    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book saveBook(String title, String isbn, int publicationYear,
                         Long authorId, Long categorieId, MultipartFile imageFile) {

        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPublicationYear(publicationYear);

        Author author = authorRepository.findById(authorId).orElse(null);
        Categorie categorie = categorieRepository.findById(categorieId).orElse(null);

        book.setAuthor(author);
        book.setCategorie(categorie);

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String filename = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                Path imagePath = Paths.get("uploads/" + filename);
                Files.createDirectories(imagePath.getParent());
                Files.write(imagePath, imageFile.getBytes());
                book.setImagePath("/uploads/" + filename);
            } catch (IOException e) {
                throw new RuntimeException("Image upload failed", e);
            }
        }

        return bookRepository.save(book);
    }

    public Book updateBook(Long id, String title, String isbn, int publicationYear,
                           Long authorId, Long categorieId, MultipartFile imageFile) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPublicationYear(publicationYear);

        Author author = authorRepository.findById(authorId).orElse(null);
        Categorie categorie = categorieRepository.findById(categorieId).orElse(null);
        book.setAuthor(author);
        book.setCategorie(categorie);

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String filename = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                Path imagePath = Paths.get("uploads/" + filename);
                Files.createDirectories(imagePath.getParent());
                Files.write(imagePath, imageFile.getBytes());

                book.setImagePath("/uploads/" + filename);
            } catch (IOException e) {
                throw new RuntimeException("Image upload failed", e);
            }
        }

        return bookRepository.save(book);
    }

    private BookDTO convertToDTO(Book book) {

        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getPublicationYear(),
                book.getAuthor() != null ? book.getAuthor().getName() : "Unknown Author",
                book.getCategorie() != null ? book.getCategorie().getName() : "Uncategorized",
                book.getImagePath(),
                book.getAuthor() != null ? book.getAuthor().getId() : null,
                book.getCategorie() != null ? book.getCategorie().getId() : null
        );
    }




    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
