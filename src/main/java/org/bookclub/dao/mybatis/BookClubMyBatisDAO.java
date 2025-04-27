package org.bookclub.dao.mybatis;

import org.bookclub.entity.BookClub;
import org.bookclub.mybatis.BookClubMapper;
import org.mybatis.cdi.Transactional;

//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//import jakarta.transaction.Transactional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.List;

@ApplicationScoped
public class BookClubMyBatisDAO {
    @Inject
    private BookClubMapper bookClubMapper;

    public List<BookClub> findAll() {
        return bookClubMapper.findAll();
    }

    public BookClub findById(Long id) {
        return bookClubMapper.findById(id);
    }

    @Transactional
    public void save(BookClub bookClub) {
        bookClubMapper.insert(bookClub);
    }

    @Transactional
    public void addReaderToBookClub(Long readerId, Long bookClubId) {
        bookClubMapper.addReaderToBookClub(readerId, bookClubId);
    }
}
