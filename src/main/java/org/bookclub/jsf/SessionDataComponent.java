package org.bookclub.jsf;

//import jakarta.enterprise.context.SessionScoped;
//import jakarta.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;

@Named
@SessionScoped
public class SessionDataComponent implements Serializable {

    private final String sessionInfo;
    private final String sessionGeneratedName;

    public SessionDataComponent() {
        this.sessionInfo = "Session created at: " + LocalDateTime.now();
        this.sessionGeneratedName = "SessionUser-" + new Random().nextInt(10000);
    }

    public String getSessionInfo() {
        return sessionInfo;
    }

    public String getSessionGeneratedName() {
        return sessionGeneratedName;
    }
}
