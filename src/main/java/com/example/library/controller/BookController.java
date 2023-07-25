package com.example.library.controller;

import com.example.library.domain.Book;
import com.example.library.domain.Person;
import com.example.library.service.BookService;
import com.example.library.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final PersonService personService;

    public BookController(BookService bookService, PersonService personService) {
        this.personService = personService;
        this.bookService = bookService;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "/books/all";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable int id) {
        model.addAttribute("book", bookService.findById(id));

        model.addAttribute("people", PERSON_SERVICE.findAll());
        model.addAttribute("person", new Person());
        return "/books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/books/new";
        }
        BOOK_SERVICE.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable int id) {
        model.addAttribute("book", bookService.findById(id));
        return "/books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@Valid @RequestBody Book book,
                         BindingResult bindingResult, @PathVariable int id) {
        if (bindingResult.hasErrors()) {
            return "/books/edit";
        }
        setRightVersion(book);
        bookService.update(book);
        return "redirect:/books";
    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public Book getById(@PathVariable int id) {
        return bookService.findById(id);
    }

    @PatchMapping("/return/{id}")
    public String returnBook(@PathVariable int id) {
        bookService.freeBook(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/appoint")
    public String appointPerson(@ModelAttribute Person person, @PathVariable int id) {
        Book book = bookService.findById(id);
        book.setPerson(PERSON_SERVICE.findById(person.getId()));
        bookService.update(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }
    private void setRightVersion(Book book){
        Book oldBookInstance = bookService.findById(book.getId());
        book.setVersion(oldBookInstance.getVersion());
    }
}
