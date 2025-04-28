package org.bookclub.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Random;

@Named("randomizerService")
@ApplicationScoped
public class RandomizerService {

    private final int randomValue;

    public RandomizerService() {
        randomValue = new Random().nextInt(1000);
    }

    public String getInfo() {
        return "Randomizer value: " + randomValue;
    }
}
