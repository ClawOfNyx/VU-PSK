package org.bookclub.service;

import org.bookclub.entity.Book;
import org.bookclub.dao.jpa.BookJpaDAO;
import org.bookclub.dao.mybatis.BookMyBatisDAO;

//import jakarta.enterprise.context.RequestScoped;
//import jakarta.inject.Inject;
//import jakarta.transaction.Transactional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.List;

@RequestScoped
public class BookService {
    @Inject
    private BookJpaDAO bookJpaDAO;

    @Inject
    private BookMyBatisDAO bookMyBatisDAO;

    // -------------- JPA methods --------------
    public List<Book> getAllBooksJpa() {
        return bookJpaDAO.findAll();
    }

    public Book getBookByIdJpa(Long id) {
        return bookJpaDAO.findById(id);
    }

    @Transactional
    public void saveBookJpa(Book book) {
        bookJpaDAO.save(book);
    }

    // -------------- MyBatis methods --------------
    public List<Book> getAllBooksMyBatis() {
        return bookMyBatisDAO.findAll();
    }

    public Book getBookByIdMyBatis(Long id) {
        return bookMyBatisDAO.findById(id);
    }

    @Transactional
    public void saveBookMyBatis(Book book) {
        bookMyBatisDAO.save(book);
    }
}
