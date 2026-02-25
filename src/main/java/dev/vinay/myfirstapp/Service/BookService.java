package dev.vinay.myfirstapp.Service;

import dev.vinay.myfirstapp.Repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.vinay.myfirstapp.Entity.Book;
import java.util.List;

@Service
public class BookService
{
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book)
    {
        if(bookRepository.existsByIsbn(book.getIsbn()))
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ISBN already exists");
        }

        return bookRepository.save(book);
    }

    public List<Book> getAllBooks()
    {

        return bookRepository.findAll();
    }

    public Book getBookById(Long id)
    {
        return bookRepository.findById(id).
                orElseThrow (() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Book not found"));
    }

    public Book updateBook(Long id, Book updatedBook)
    {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setPrice(updatedBook.getPrice());

        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id)
    {
        if(!bookRepository.existsById(id))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found");
        }

        bookRepository.deleteById(id);
    }
}
