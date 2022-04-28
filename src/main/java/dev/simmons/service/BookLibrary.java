package dev.simmons.service;

import dev.simmons.entities.Book;
import dev.simmons.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Component
@Service
public class BookLibrary implements BookService {
    @Autowired
    private BookRepo repo;

    @Override
    public Book registerBook(Book book) {
        return repo.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    @Override
    public List<Book> getAllBooksByTitle(String title) {
        return repo.findBookByTitle(title);
    }

    @Override
    public Book getBookById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Book replaceBook(Book book) {
        return repo.save(book);
    }

    @Override
    public Book checkinBook(int id) {
        Book book = repo.getById(id);
        book.setReturnDate(0);
        return repo.save(book);
    }

    private final long fortnightInMillis = 1000 * 60 * 60 * 24 * 14;
    @Override
    public Book checkoutBook(int id) {
        Book book = repo.getById(id);
        book.setReturnDate(System.currentTimeMillis() + fortnightInMillis);
        return repo.save(book);
    }

    @Override
    public boolean deleteBook(int id) {
        repo.deleteById(id);
        return repo.findById(id).isPresent();
    }
}
