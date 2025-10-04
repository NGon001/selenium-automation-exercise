package org.automationExercise.pages;

import java.util.Random;

public class Utils {
    public static String generateRandomEmail() {
        Random random = new Random();
        long number = Math.abs(random.nextLong()) % 1_0000_0000_0000_0000L;
        String randomNumber = String.format("%016d", number);
        return "max" + randomNumber + "@gmail.com";
    }
}
