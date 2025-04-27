package org.bookclub.mybatis;

import org.bookclub.entity.BookClub;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface BookClubMapper {
    List<BookClub> findAll();
    BookClub findById(Long id);
    void insert(BookClub bookClub);
    void addReaderToBookClub(@Param("readerId") Long readerId, @Param("bookClubId") Long bookClubId);
}
