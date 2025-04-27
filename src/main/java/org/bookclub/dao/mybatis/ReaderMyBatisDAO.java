package org.bookclub.dao.mybatis;

import org.bookclub.entity.Reader;
import org.bookclub.mybatis.ReaderMapper;
import org.mybatis.cdi.Transactional;

//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ReaderMyBatisDAO {
    @Inject
    private ReaderMapper readerMapper;

    public List<Reader> findAll() {
        return readerMapper.findAll();
    }

    public Reader findById(Long id) {
        return readerMapper.findById(id);
    }

    @Transactional
    public void save(Reader reader) {
        readerMapper.insert(reader);
    }

    @Transactional
    public void addBookToReader(Long readerId, Long bookId) {
        readerMapper.addBookToReader(readerId, bookId);
    }
}
