package org.bookclub.jsf;

import org.bookclub.service.RandomizerService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;

@Named
@RequestScoped
public class NameGeneratorComponent implements Serializable {

    private final String componentInfo;
    private final String generatedName;

    @Inject
    private RandomizerService randomizerService; // This is ApplicationScoped

    public NameGeneratorComponent() {
        // This will be different every request
        this.componentInfo = "Request created at: " + LocalDateTime.now();

        // You could also generate a random name for every request
        this.generatedName = "User-" + new Random().nextInt(100);
    }

    public String getComponentInfo() {
        return componentInfo;
    }

    public String getGeneratedName() {
        return generatedName;
    }

    public String getRandomizerInfo() {
        return randomizerService.getInfo();
    }
}
