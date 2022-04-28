package dev.simmons.service;

import dev.simmons.entities.Book;
import java.util.List;

public interface BookService {
    Book registerBook(Book book);
    List<Book> getAllBooks();
    List<Book> getAllBooksByTitle(String title);
    Book getBookById(int id);
    Book replaceBook(Book book);
    Book checkinBook(int id);
    Book checkoutBook(int id);
    boolean deleteBook(int id);
}
