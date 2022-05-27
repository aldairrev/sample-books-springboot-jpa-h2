package pe.aldairrev.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.aldairrev.library.models.Book;
import pe.aldairrev.library.repositories.BookRepository;

@Service
public class BookMemoryServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @Override
    public Book create(Book t) {
        Optional<Book> bookByIsbn = repository.findByIsbn(t.getIsbn());
        if (bookByIsbn.isPresent()) {
            String message = "This isbn already exist in our database.";
            throw new IllegalStateException(message);
        }
        return repository.save(t);
    }

    @Override
    public Book delete(Long id) {
        Optional<Book> book = repository.findById(id);
        if (!book.isPresent()) {
            String message = "Book with id: " + id + ", does no exist.";
            throw new IllegalStateException(message);
        }
        repository.deleteById(id);
        return book.get();
    }

    @Override
    public Book find(Long id) {
        Optional<Book> book = repository.findById(id);
        if (!book.isPresent()) {
            String message = "Book with id: " + id + ", does no exist.";
            throw new IllegalStateException(message);
        }
        return book.get();
    }

    @Override
    public List<Book> getAll() {
        return repository.findAll();
    }

    @Override
    public Book update(Book t) {
        Optional<Book> old_book = repository.findById(t.getId());
        if (!old_book.isPresent()) {
            String message = "Book with id: " + t.getId() + ", does no exist.";
            throw new IllegalStateException(message);
        }

        Optional<Book> bookByIsbn = repository.findByIsbn(t.getIsbn());
        boolean isSameEmail = old_book.get().getIsbn().equals(t.getIsbn());
        if (bookByIsbn.isPresent() && !isSameEmail) {
            String message = "This email already exist in our database.";
            throw new IllegalStateException(message);
        }
        
        return repository.save(t);
    }
}