package org.bookclub.mybatis;

import org.bookclub.entity.Book;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> findAll();
    Book findById(Long id);
    void insert(Book book);
}
