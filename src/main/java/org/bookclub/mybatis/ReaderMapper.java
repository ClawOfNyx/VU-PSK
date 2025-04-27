package org.bookclub.mybatis;

import org.bookclub.entity.Reader;

import org.mybatis.cdi.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ReaderMapper {
    List<Reader> findAll();
    Reader findById(Long id);
    void insert(Reader reader);
    void addBookToReader(@Param("readerId") Long readerId, @Param("bookId") Long bookId);
}
