package org.bookclub.jsf;

import org.bookclub.entity.BookClub;
import org.bookclub.service.BookClubService;
import org.primefaces.event.SelectEvent;

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
public class BookClubBean implements Serializable {
    @Inject
    private BookClubService bookClubService;

    private List<BookClub> bookClubs;
    private BookClub selectedBookClub;
    private BookClub newBookClub = new BookClub();
    private boolean useJpa = false;

    @PostConstruct
    public void init() {
        loadBookClubs();
    }

    public void loadBookClubs() {
        if (useJpa) {
            bookClubs = bookClubService.getAllBookClubsJpa();
        } else {
            bookClubs = bookClubService.getAllBookClubsMyBatis();
        }
    }

    public void saveBookClub() {
        if (useJpa) {
            bookClubService.saveBookClubJpa(newBookClub);
        } else {
            bookClubService.saveBookClubMyBatis(newBookClub);
        }
        newBookClub = new BookClub();
        loadBookClubs();
    }

    public void selectBookClub(SelectEvent<BookClub> event) {
        BookClub bookClub = event.getObject();
        if (useJpa) {
            this.selectedBookClub = bookClubService.getBookClubByIdJpa(bookClub.getId());
        } else {
            this.selectedBookClub = bookClubService.getBookClubByIdMyBatis(bookClub.getId());
        }
    }

    public void toggleDataAccessMethod() {
        useJpa = !useJpa;

        loadBookClubs();

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Data Access Changed",
                        "Now using " + getDataAccessType()));
    }

    // Getters and setters
    public List<BookClub> getBookClubs() {
        return bookClubs;
    }

    public BookClub getNewBookClub() {
        return newBookClub;
    }

    public void setNewBookClub(BookClub newBookClub) {
        this.newBookClub = newBookClub;
    }

    public boolean isUseJpa() {
        return useJpa;
    }

    public void setUseJpa(boolean useJpa) {
        this.useJpa = useJpa;
    }

    public BookClub getSelectedBookClub() {
        return selectedBookClub;
    }

    public void setSelectedBookClub(BookClub selectedBookClub) {
        this.selectedBookClub = selectedBookClub;
    }

    public String getDataAccessType() {
        return useJpa ? "JPA/ORM" : "MyBatis";
    }
}
