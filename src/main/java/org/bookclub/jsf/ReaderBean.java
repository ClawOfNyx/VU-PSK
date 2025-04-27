package org.bookclub.jsf;

import org.bookclub.entity.BookClub;
import org.bookclub.service.BookClubService;
import org.bookclub.entity.Book;
import org.bookclub.service.BookService;
import org.bookclub.entity.Reader;
import org.bookclub.service.ReaderService;

//import jakarta.annotation.PostConstruct;
//import jakarta.faces.application.FacesMessage;
//import jakarta.faces.context.FacesContext;
//import jakarta.faces.view.ViewScoped;
//import jakarta.inject.Inject;
//import jakarta.inject.Named;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ReaderBean implements Serializable {
    @Inject
    private ReaderService readerService;

    @Inject
    private BookService bookService;

    @Inject
    private BookClubService bookClubService;

    private List<Reader> readers;
    private List<Book> availableBooks;
    private List<BookClub> availableBookClubs;
    private Reader newReader = new Reader();
    private Reader selectedReader;
    private Long selectedBookId;
    private Long selectedBookClubId;
    private Long newReaderBookClubId;
    private boolean useJpa = false;

    @PostConstruct
    public void init() {
        loadReaders();
        loadAvailableBooks();
        loadAvailableBookClubs();
    }

    public void loadReaders() {
        if (useJpa) {
            readers = readerService.getAllReadersJpa();
        } else {
            readers = readerService.getAllReadersMyBatis();
        }
    }

    public void loadAvailableBooks() {
        if (useJpa) {
            availableBooks = bookService.getAllBooksJpa();
        } else {
            availableBooks = bookService.getAllBooksMyBatis();
        }
    }

    public void loadAvailableBookClubs() {
        if (useJpa) {
            availableBookClubs = bookClubService.getAllBookClubsJpa();
        } else {
            availableBookClubs = bookClubService.getAllBookClubsMyBatis();
        }
    }

    public void saveReader() {
        if (useJpa) {
            // If bookClub is selected, set it
            if (newReaderBookClubId != null) {
                BookClub bookClub = bookClubService.getBookClubByIdJpa(newReaderBookClubId);
                newReader.setBookClub(bookClub);
            }
            readerService.saveReaderJpa(newReader);
        } else {
            readerService.saveReaderMyBatis(newReader);
            // Handle bookClub assignment separately for MyBatis after saving reader
            if (newReaderBookClubId != null) {
                bookClubService.addReaderToBookClubMyBatis(newReader.getId(), newReaderBookClubId);
            }
        }
        newReader = new Reader();
        newReaderBookClubId = null;
        loadReaders();
    }

    public void selectReader(Reader reader) {
        this.selectedReader = reader;
    }

    public void addBookToReader() {
        if (selectedReader != null && selectedBookId != null) {
            if (useJpa) {
                readerService.addBookToReaderJpa(selectedReader.getId(), selectedBookId);
            } else {
                readerService.addBookToReaderMyBatis(selectedReader.getId(), selectedBookId);
            }

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Reader enrolled in book"));

            loadReaders();
            selectedBookId = null;
        }
    }

    public void addReaderToBookClub() {
        if (selectedReader != null && selectedBookClubId != null) {
            if (useJpa) {
                bookClubService.addReaderToBookClubJpa(selectedReader.getId(), selectedBookClubId);
            } else {
                bookClubService.addReaderToBookClubMyBatis(selectedReader.getId(), selectedBookClubId);
            }

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Reader assigned to bookClub"));

            loadReaders();
            selectedBookClubId = null;
        }
    }

    public List<Reader> getReaders() {
        return readers;
    }

    public Reader getNewReader() {
        return newReader;
    }

    public void setNewReader(Reader newReader) {
        this.newReader = newReader;
    }

    public boolean isUseJpa() {
        return useJpa;
    }

    public void setUseJpa(boolean useJpa) {
        this.useJpa = useJpa;
    }

    public String getDataAccessType() {
        return useJpa ? "JPA/ORM" : "MyBatis";
    }

    public Reader getSelectedReader() {
        return selectedReader;
    }

    public void setSelectedReader(Reader selectedReader) {
        this.selectedReader = selectedReader;
    }

    public Long getSelectedBookId() {
        return selectedBookId;
    }

    public void setSelectedBookId(Long selectedBookId) {
        this.selectedBookId = selectedBookId;
    }

    public Long getSelectedBookClubId() {
        return selectedBookClubId;
    }

    public void setSelectedBookClubId(Long selectedBookClubId) {
        this.selectedBookClubId = selectedBookClubId;
    }

    public Long getNewReaderBookClubId() {
        return newReaderBookClubId;
    }

    public void setNewReaderBookClubId(Long newReaderBookClubId) {
        this.newReaderBookClubId = newReaderBookClubId;
    }

    public List<Book> getAvailableBooks() {
        return availableBooks;
    }

    public List<BookClub> getAvailableBookClubs() {
        return availableBookClubs;
    }
}
