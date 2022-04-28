package dev.simmons.Library;

import java.util.List;
import dev.simmons.entities.Book;
import dev.simmons.repos.BookRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookRepoTest {
    @Autowired
    private BookRepo repo;

    @Test
    public void createBook() {
        Book book = new Book();
        book.setId(0);
        book.setTitle("Frankenstein");
        book.setAuthor("Mary Shelly");
        book.setReturnDate(0);

        Book received = repo.save(book);
        Assertions.assertNotNull(received);
    }

    @Test
    public void getAllBooks() {
        List<Book> books = repo.findAll();
        System.out.println(books);
    }

    @Test
    public void getBookById() {
        Book book = repo.findById(45).orElse(null);

        Assertions.assertNotNull(book);

    }

    @Test
    public void getBookByTitle() {
        List<Book> titledBooks = repo.findBookByTitle("Frankenstein");
        System.out.println(titledBooks);
    }

    @Test
    public void getBookByAuthor() {
        List<Book> authoredBooks = repo.findBooksByAuthor("Mary Shelly");
        System.out.println(authoredBooks);
    }
}
