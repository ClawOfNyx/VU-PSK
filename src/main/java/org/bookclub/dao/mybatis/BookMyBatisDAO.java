package org.bookclub.dao.mybatis;

import org.bookclub.entity.Book;
import org.bookclub.mybatis.BookMapper;
import org.mybatis.cdi.Transactional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class BookMyBatisDAO {
    @Inject
    private BookMapper bookMapper;

    public List<Book> findAll() {
        return bookMapper.findAll();
    }

    public Book findById(Long id) {
        return bookMapper.findById(id);
    }

    @Transactional
    public void save(Book book) {
        bookMapper.insert(book);
    }
}
