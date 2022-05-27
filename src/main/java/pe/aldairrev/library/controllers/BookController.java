package pe.aldairrev.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pe.aldairrev.library.models.Book;
import pe.aldairrev.library.services.BookMemoryServiceImpl;

@Controller
public class BookController {
    
    @Autowired
    private BookMemoryServiceImpl service;

    @GetMapping("/books")
    public String index(Model model) {
        model.addAttribute("books", service.getAll());
        return "books/index";
    }

    @GetMapping("/books/create")
    public String create(Model model) {
        model.addAttribute("book_id", service.getAll().size() + 1);
        return "books/create";
    }

    @PostMapping("/books/store")
    public String store(Book book) {
        service.create(book);
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Book book = service.getAll().stream()
            .filter(s -> (s.getId() == id))
            .findFirst()
            .orElse(null);
        if (book == null) {
            return "redirect:/books";
        }

        model.addAttribute("book", book);

        return "books/edit";
    }

    @PostMapping("/books/update")
    public String update(Book book) {

        service.update(book);

        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);

        return "redirect:/books";
    }
}
