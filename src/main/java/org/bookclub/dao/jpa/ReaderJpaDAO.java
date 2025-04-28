package org.bookclub.dao.jpa;

import org.bookclub.entity.Book;
import org.bookclub.entity.Reader;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

@Stateless
public class ReaderJpaDAO {
    @PersistenceContext(unitName = "bookClubPU")
    private EntityManager em;

    public List<Reader> findAll() {
        return em.createQuery(
                        "SELECT DISTINCT r FROM Reader r " +
                                "LEFT JOIN FETCH r.bookClub " +
                                "LEFT JOIN FETCH r.books", Reader.class)
                .getResultList();
    }

    public Reader findById(Long id) {
        return em.find(Reader.class, id);
    }

    @Transactional
    public void save(Reader reader) {
        if (reader.getId() == null) {
            em.persist(reader);
        } else {
            em.merge(reader);
        }
    }

    @Transactional
    public void addBookToReader(Long readerId, Long bookId) {
        Reader reader = em.find(Reader.class, readerId);
        Book book = em.find(Book.class, bookId);

        if (reader != null && book != null) {
            reader.getBooks().add(book);
            book.getReaders().add(reader);
            em.merge(reader);
        }
    }
}
