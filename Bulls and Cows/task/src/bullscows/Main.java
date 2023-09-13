package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private final static int maxLength = 36;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = 0;

        System.out.println("Input the length of the secret code:");
        try {
            length = Integer.parseInt(scanner.next());
        }
        catch (Exception e) {
            System.out.println("Error");
            return;
        }
        if (length > maxLength || length < 1) {
            System.out.println("Error");
            return;
        }
        else {
            System.out.println("Input the number of possible symbols in the code:");
            int numberOfSymbols = scanner.nextInt();
            if (length > numberOfSymbols || numberOfSymbols > 36) {
                System.out.println("Error");
                return;
            }
            String secretString = generateSecretString(length, numberOfSymbols);

            System.out.println(prepareSecretMessage(length, numberOfSymbols));

            int turn = 1;
            System.out.printf("Okay, let's start a game!\nTurn %d:\n", turn);
            String answer = scanner.next();

            while (!isGuessed(secretString, answer)) {
                turn++;
                System.out.printf("Turn %d:\n", turn);
                answer = scanner.next();
            }
            System.out.println("Congratulations! You guessed the secret code.");
        }
    }

    static String generateSecretString(int length, int numberOfSymbols) {
        Random random = new Random();
        int lowerBound = '0';
        int upperBound;
        if (numberOfSymbols <= 10) {
            upperBound = lowerBound + numberOfSymbols - 1;
        }
        else {
            upperBound = numberOfSymbols - 11 + 'a';
        }
        StringBuilder secretString = new StringBuilder();
        while (secretString.length() < length) {
            int curDigit = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            if (curDigit > '9' && curDigit < 'a') {
                continue;
            }
            String curSymb = String.valueOf((char) curDigit);
            if (secretString.indexOf(curSymb) < 0) {
                secretString.append(curSymb);
            }
        }
        return secretString.toString();
    }

    static String prepareSecretMessage(int length, int numberOfSymbols) {
        StringBuilder preparedSecretMessage = new StringBuilder("The secret is prepared: ");
        preparedSecretMessage.append("*".repeat(Math.max(0, length)));
        preparedSecretMessage.append(" (");

        boolean areThereLetters = numberOfSymbols > 10;
        char firstDigit = '0';
        char lastDigit = areThereLetters? '9' : (char) ('0'+ numberOfSymbols - 1);
        preparedSecretMessage.append(firstDigit).append("-").append(lastDigit);

        if (areThereLetters) {
            char firstLetter = 'a';
            char lastLetter = (char) ('a' + numberOfSymbols - 11);
            preparedSecretMessage.append(", ").append(firstLetter).append("-").append(lastLetter);
        }

        preparedSecretMessage.append(").");
        return  preparedSecretMessage.toString();
    }

    static boolean isGuessed(String secretString, String answer) {
        int cow = 0;
        int bull = 0;
        for (int i = 0; i < secretString.length(); i++) {
            if(answer.charAt(i) == secretString.charAt(i)) {
                bull++;
            }
            else if(secretString.contains(String.valueOf(answer.charAt(i)))) {
                cow++;
            }
        }

        StringBuilder numberOfBulls = new StringBuilder();
        if (bull == 1) {
            numberOfBulls.append("1 bull");
        }
        else if (bull > 1) {
            numberOfBulls.append(bull).append(" bulls");
        }

        StringBuilder numberOfCows = new StringBuilder();
        if (cow == 1) {
            numberOfCows.append("1 cow");
        }
        else if (cow > 1) {
            numberOfCows.append(cow).append(" cows");
        }

        String andWord = "";
        if (cow > 0 && bull >0) {
            andWord = " and ";
        }

        if (bull == 0 && cow == 0) {
            System.out.println("Grade: None.");
            return false;
        }
        System.out.println("Grade: " + numberOfBulls + andWord + numberOfCows);
        return bull == secretString.length();
    }
}
