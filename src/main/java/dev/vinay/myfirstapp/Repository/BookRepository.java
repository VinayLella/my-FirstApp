package dev.vinay.myfirstapp.Repository;

import dev.vinay.myfirstapp.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>
{
    boolean existsByIsbn(String isbn);

}
