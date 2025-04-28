package org.bookclub.jsf;

import org.bookclub.entity.Book;
import org.bookclub.service.BookService;
import org.primefaces.event.SelectEvent;

//import jakarta.annotation.PostConstruct;
//import jakarta.faces.application.FacesMessage;
//import jakarta.faces.context.FacesContext;
//import jakarta.faces.view.ViewScoped;
//import jakarta.inject.Inject;
//import jakarta.inject.Named;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class BookBean implements Serializable {
    @Inject
    private BookService bookService;

    private List<Book> books;
    private Book selectedBook;
    private Book newBook = new Book();
    private boolean useJpa = false;

    @PostConstruct
    public void init() {
        loadBooks();
    }

    public void loadBooks() {
        if (useJpa) {
            books = bookService.getAllBooksJpa();
        } else {
            books = bookService.getAllBooksMyBatis();
        }
    }

    public void saveBook() {
        if (useJpa) {
            bookService.saveBookJpa(newBook);
        } else {
            bookService.saveBookMyBatis(newBook);
        }
        newBook = new Book();
        loadBooks();
    }

    public void selectBook(SelectEvent<Book> event) {
        Book book = event.getObject();
        if (useJpa) {
            this.selectedBook = bookService.getBookByIdJpa(book.getId());
        } else {
            this.selectedBook = bookService.getBookByIdMyBatis(book.getId());
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book getNewBook() {
        return newBook;
    }

    public void setNewBook(Book newBook) {
        this.newBook = newBook;
    }

    public boolean isUseJpa() {
        return useJpa;
    }

    public void setUseJpa(boolean useJpa) {
        this.useJpa = useJpa;
    }

    public Book getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
    }

    public String getDataAccessType() {
        return useJpa ? "JPA/ORM" : "MyBatis";
    }
}
