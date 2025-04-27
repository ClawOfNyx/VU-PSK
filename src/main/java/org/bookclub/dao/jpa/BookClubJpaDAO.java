package org.bookclub.dao.jpa;

import org.bookclub.entity.BookClub;
import org.bookclub.entity.Reader;

//import jakarta.ejb.Stateless;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.transaction.Transactional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

@Stateless
public class BookClubJpaDAO {
    @PersistenceContext(unitName = "bookClubPU")
    private EntityManager em;

    public List<BookClub> findAll() {
        return em.createQuery("SELECT c FROM BookClub c", BookClub.class).getResultList();
    }

    public BookClub findById(Long id) {
        return em.find(BookClub.class, id);
    }

    @Transactional
    public void save(BookClub bookClub) {
        if (bookClub.getId() == null) {
            em.persist(bookClub);
        } else {
            em.merge(bookClub);
        }
    }

    @Transactional
    public void addReaderToBookClub(Long readerId, Long bookClubId) {
        Reader reader = em.find(Reader.class, readerId);
        BookClub bookClub = em.find(BookClub.class, bookClubId);

        if (reader != null && bookClub != null) {
            reader.setBookClub(bookClub);
            bookClub.getReaders().add(reader);
            em.merge(reader);
        }
    }
}
