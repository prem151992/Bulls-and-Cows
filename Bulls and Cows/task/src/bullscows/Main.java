package bullscows;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;
public class Main {
    public static int duplicateArrayLength(char[] secretCharArray) {
        int arrayLength = 1;
        for (int i = 1; i < secretCharArray.length; i++) {
            boolean unique = true;
            for (int j = 0; j < i; j++) {
                if (secretCharArray[i] == secretCharArray[j]) {
                    unique = false;
                }
            }
            if (unique) {
                arrayLength += 1;
            }
        }
        return arrayLength;
    }

    public static String genSecretCode(int secretCodeLength, int possibleSymbolsNumber, char[] possiblecharsArray) {

        Random randomNumber = new Random();

        char[] secretCodeArray = new char[secretCodeLength];

        for (int i = 0; i < secretCodeLength; i++) {
            int index = randomNumber.nextInt(possibleSymbolsNumber);
            while (i == 0 && index == 0) {
                index = randomNumber.nextInt(possibleSymbolsNumber);
            }
            secretCodeArray[i] = possiblecharsArray[index];
        }

//        System.out.println(String.valueOf(secretCodeArray));

        int uniqueValuesLength = duplicateArrayLength(secretCodeArray);

        while (uniqueValuesLength < secretCodeArray.length) {
            for (int i = 1; i < secretCodeLength; i++) {
                for (int j = 0; j < i; j++) {
                    if (Character.getNumericValue(secretCodeArray[i]) != Character.getNumericValue(secretCodeArray[j])) {
                        continue;
                    } else {
                        int index = randomNumber.nextInt(possibleSymbolsNumber);
                        secretCodeArray[i] = possiblecharsArray[index];
                        break;
                    }
                }

            }
            uniqueValuesLength = duplicateArrayLength(secretCodeArray);
        }




        return String.valueOf(secretCodeArray);
    }

    public static void computeBullsAndCows(String secretCode) {
        char [] secretCodeArray = secretCode.toCharArray();
        int incrementer = 1;
        System.out.println("Okay, let's start a game!");
        boolean guessed = false;
        while (!guessed) {
            System.out.println("Turn " + incrementer + ":");

            Scanner scanner = new Scanner(System.in);

            String inputNumber = scanner.nextLine();

            char[] inputNumberArray = inputNumber.toCharArray();

            int bulls = 0;
            int cows = 0;

            for (int i = 0; i < inputNumberArray.length; i++) {
                if (inputNumberArray[i] == secretCodeArray[i]) {
                    bulls += 1;
                } else {
                    for (int j = 0; j < secretCodeArray.length; j++) {
                        if (inputNumberArray[i] == secretCodeArray[j]) {
                            cows += 1;
                        }
                    }
                }
            }

            String printString = "";

            String bullStr = bulls > 1 ? "bulls" : "bull";
            String cowStr = cows > 1 ? "cows" : "cow";

            if (bulls > 0 && cows > 0) {
                printString = "Grade: " + bulls + " " + bullStr + " and " + cows + " " + cowStr;
            } else if (bulls > 0) {
                printString = "Grade: " + bulls + " " + bullStr;
            } else if (cows > 0) {
                printString = "Grade: " + cows + " " + cowStr;
            } else {
                printString = "Grade: None";
            }

            if (bulls == secretCodeArray.length) {
                guessed = true;
            }
            System.out.println(printString);
            incrementer +=1;
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }
    public static void main(String[] args) {

        try {
            String possiblechars = "0123456789abcdefghijklmnopqrstuvwxyz";

            final char[] possiblecharsArray = possiblechars.toCharArray();

            System.out.println("Please, enter the secret code's length:");
            Scanner scanner = new Scanner(System.in);
            String secretCodeLengthStr = scanner.nextLine();

            int secretCodeLength = Integer.parseInt(secretCodeLengthStr);


            System.out.println("Input the number of possible symbols in the code:");

            String possibleSymbolsNumberStr = scanner.nextLine();

            int possibleSymbolsNumber = Integer.parseInt(possibleSymbolsNumberStr);

            if (secretCodeLength == 0 || secretCodeLength > possibleSymbolsNumber) {
                System.out.println("Error: it's not possible to generate a code with a length of " + secretCodeLength + " with " + possibleSymbolsNumber + " unique symbols.");
                return;
            }

            if (secretCodeLength <= 36 && possibleSymbolsNumber <= 36) {
                String secretCode = genSecretCode(secretCodeLength, possibleSymbolsNumber, possiblecharsArray);

                StringBuilder secretcodePrintStr = new StringBuilder();

                for (int i = 0; i < secretCode.length(); i++) {
                    secretcodePrintStr.append("*");
                }
                if (possibleSymbolsNumber <= 10) {
                    secretcodePrintStr.append(" (0-").append(possiblecharsArray[possibleSymbolsNumber-1]).append(").");
                } else if (possibleSymbolsNumber <= 36) {
                    secretcodePrintStr.append(" (0-9 a-").append(possiblecharsArray[possibleSymbolsNumber-1]).append(").");

                }


                System.out.println("The secret is prepared: " + secretcodePrintStr);

                computeBullsAndCows(secretCode);

            } else {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            }
        } catch (NumberFormatException e) {
//            System.out.println();
            String inputVar = e.getMessage().replace("For input string: ", "");
            System.out.println("Error: " + inputVar + " isn't a valid number.\n");
        }

    }
}
