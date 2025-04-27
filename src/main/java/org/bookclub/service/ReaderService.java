package org.bookclub.service;

import org.bookclub.entity.Reader;
import org.bookclub.dao.jpa.ReaderJpaDAO;
import org.bookclub.dao.mybatis.ReaderMyBatisDAO;

//import jakarta.enterprise.context.RequestScoped;
//import jakarta.inject.Inject;
//import jakarta.transaction.Transactional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.List;

@RequestScoped
public class ReaderService {
    @Inject
    private ReaderJpaDAO readerJpaDAO;

    @Inject
    private ReaderMyBatisDAO readerMyBatisDAO;

    // -------------- JPA methods --------------
    public List<Reader> getAllReadersJpa() {
        return readerJpaDAO.findAll();
    }

    public Reader getReaderByIdJpa(Long id) {
        return readerJpaDAO.findById(id);
    }

    @Transactional
    public void saveReaderJpa(Reader reader) {
        readerJpaDAO.save(reader);
    }

    @Transactional
    public void addBookToReaderJpa(Long readerId, Long bookId) {
        readerJpaDAO.addBookToReader(readerId, bookId);
    }

    // -------------- MyBatis methods --------------
    public List<Reader> getAllReadersMyBatis() {
        return readerMyBatisDAO.findAll();
    }

    public Reader getReaderByIdMyBatis(Long id) {
        return readerMyBatisDAO.findById(id);
    }

    @Transactional
    public void saveReaderMyBatis(Reader reader) {
        readerMyBatisDAO.save(reader);
    }

    @Transactional
    public void addBookToReaderMyBatis(Long readerId, Long bookId) {
        readerMyBatisDAO.addBookToReader(readerId, bookId);
    }
}
