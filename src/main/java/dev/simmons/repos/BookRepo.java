package dev.simmons.repos;

import dev.simmons.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;

@Component
@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
    List<Book> findBookByTitle(String title);
    List<Book> findBooksByAuthor(String author);
}
