package pe.aldairrev.library.services;

import java.util.List;

import pe.aldairrev.library.models.Book;

public interface BookService {
    public List<Book> getAll();
    public Book find(Long id);
    public Book create(Book t);
    public Book update(Book t);
    public Book delete(Long id);
}
