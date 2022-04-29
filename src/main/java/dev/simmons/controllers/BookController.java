package dev.simmons.controllers;

import dev.simmons.aspects.Secured;
import dev.simmons.entities.Book;
import dev.simmons.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Component
@Controller
public class BookController {
    @Autowired
    private BookService service;

    @GetMapping("/books")
    @ResponseBody
    public List<Book> getAllBooks(@PathParam("title") String title) {
        List<Book> books = null;
        if (title != null) {
            books = service.getAllBooksByTitle(title);
        } else {
            books = this.service.getAllBooks();
        }
        return books;
    }

    @GetMapping("/books/{id}")
    @ResponseBody
    public Book getById(@PathVariable int id) {
        return this.service.getBookById(id);
    }

    @PostMapping("/books")
    @ResponseBody
    @Secured
    public Book insertBook(@RequestBody Book book) {
        return this.service.registerBook(book);
    }

    @PostMapping("/thing")
    @ResponseBody
    public String insertBook(@RequestBody String thing) {
        return "I love this " + thing;
    }

    @PatchMapping("/books/{id}/checkout")
    @ResponseBody
    public Book checkoutBook(@PathVariable int id) {
        return service.checkoutBook(id);
    }

    @PatchMapping("/books/{id}/checkin")
    @ResponseBody
    public Book checkinBook(@PathVariable int id) {
        return service.checkinBook(id);
    }
}
