package org.bookclub.service;

import org.bookclub.entity.BookClub;
import org.bookclub.dao.jpa.BookClubJpaDAO;
import org.bookclub.dao.mybatis.BookClubMyBatisDAO;

//import jakarta.enterprise.context.RequestScoped;
//import jakarta.inject.Inject;
//import jakarta.transaction.Transactional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.List;

@RequestScoped
public class BookClubService {
    @Inject
    private BookClubJpaDAO bookClubJpaDAO;

    @Inject
    private BookClubMyBatisDAO bookClubMyBatisDAO;

    // -------------- JPA methods --------------
    public List<BookClub> getAllBookClubsJpa() {
        return bookClubJpaDAO.findAll();
    }

    public BookClub getBookClubByIdJpa(Long id) {
        return bookClubJpaDAO.findById(id);
    }

    @Transactional
    public void saveBookClubJpa(BookClub bookClub) {
        bookClubJpaDAO.save(bookClub);
    }

    @Transactional
    public void addReaderToBookClubJpa(Long readerId, Long bookClubId) {
        bookClubJpaDAO.addReaderToBookClub(readerId, bookClubId);
    }

    // -------------- MyBatis methods --------------
    public List<BookClub> getAllBookClubsMyBatis() {
        return bookClubMyBatisDAO.findAll();
    }

    public BookClub getBookClubByIdMyBatis(Long id) {
        return bookClubMyBatisDAO.findById(id);
    }

    @Transactional
    public void saveBookClubMyBatis(BookClub bookClub) {
        bookClubMyBatisDAO.save(bookClub);
    }

    @Transactional
    public void addReaderToBookClubMyBatis(Long readerId, Long bookClubId) {
        bookClubMyBatisDAO.addReaderToBookClub(readerId, bookClubId);
    }
}
