import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String WORD = "LYMPH";
    private static List<String> DICTIONARY;
    public static void main(String[] args) throws IOException {
        DICTIONARY = Files.readAllLines(new File("words.txt").toPath());
        DICTIONARY = DICTIONARY.stream().map(String::toUpperCase).toList();

        System.out.println("Write a guess:");
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;
        for(String line = scanner.nextLine() ; line != null ; line = scanner.nextLine()) {
            if(line.length() != 5) {
                System.out.println("5 characters only... Please try again:");
                continue;
            }
            line = line.toUpperCase();
            if(line.equals(WORD)) {
                System.out.println("Success!!!");
                return;
            }
            if(!DICTIONARY.contains(line)) {
                System.out.println("Word not in dictionary... Try again:");
            } else {
                attempts++;
                printWordResult(line);
                if(attempts > 7) {
                    System.out.println("Game over!");
                    return;
                }
            }
        }

    }

    private static void printWordResult(String word) {
        for(int iter = 0 ; iter < word.length() ; iter++) {
            char currentChar = word.charAt(iter);
            if(currentChar == WORD.charAt(iter)) {
                System.out.print("G"); // Green
                continue;
            }
            if(WORD.indexOf(currentChar) > -1) {
                System.out.print("Y"); // Yellow
                continue;
            }
            System.out.print("B"); // Black
        }
        System.out.println();
    }
}