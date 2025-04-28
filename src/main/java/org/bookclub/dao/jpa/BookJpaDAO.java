package org.bookclub.dao.jpa;

import org.bookclub.entity.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

@Stateless
public class BookJpaDAO {
    @PersistenceContext(unitName = "bookClubPU")
    private EntityManager em;

    public List<Book> findAll() {
        return em.createQuery("SELECT c FROM Book c", Book.class).getResultList();
    }

    public Book findById(Long id) {
        return em.find(Book.class, id);
    }

    @Transactional
    public void save(Book book) {
        if (book.getId() == null) {
            em.persist(book);
        } else {
            em.merge(book);
        }
    }
}
