package org.bookclub.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book_clubs")
public class BookClub implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "bookClub", fetch = FetchType.EAGER)
    private Set<Reader> readers = new HashSet<>();

    public BookClub() {
    }

    public BookClub(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Reader> getReaders() {
        return readers;
    }

    public void setReaders(Set<Reader> readers) {
        this.readers = readers;
    }
}

