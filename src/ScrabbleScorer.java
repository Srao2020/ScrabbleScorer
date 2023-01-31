import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Scrabble Scorer validates and scores scrabble words
 * @version 1/30/23
 * @author Srao2020
 */

public class ScrabbleScorer {
    private String alpha = "ABCDEFJHIJKLMNOPQRSTUVWXYZ";
    private int[] scores = {1, 3, 3, 2,
            1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10,
            1, 1, 1, 1, 4, 4, 8, 4, 10};
    private ArrayList<ArrayList<String>> dictionary;


    public ScrabbleScorer() {
        dictionary = new ArrayList<>();
        for (int i = 0; i < 26; i++)
            dictionary.add(new ArrayList<String>());
        buildDictionary();
    }

    public void buildDictionary() {
        try {
            Scanner in = new Scanner(new File("SCRABBLE_WORDS.txt"));
            while (in.hasNext()) {
                String word = in.nextLine();
                int index = alpha.indexOf(word.substring(0,1));
                dictionary.get(index).add(word);
            }
            in.close();
            for (int i = 0; i < dictionary.size(); i++) {
                Collections.sort(dictionary.get(i));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        System.out.println(dictionary);
    }

    public boolean isValidWord(String word) {
        if (Collections.binarySearch(dictionary.get(alpha.indexOf(word.substring(0, 1))), word) < 0)
            return false;
        return true;
    }

    public int getWordScore(String word) {
        int score = 0;
        for (int i = 0; i < word.length(); i++) {
            int index = alpha.indexOf(word.substring(i, i + 1));
            score += scores[index];
        }
        return score;
    }

    public static void main(String[] args) {
        ScrabbleScorer app = new ScrabbleScorer();
        System.out.println("* Welcome to the Scrabble Word Scorer app *");
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a word to score or 0 to quit: ");
            String word = in.nextLine().toUpperCase();
            if (word.equals("0")) {
                break;
            }
            if (app.isValidWord(word)) {
                System.out.println(word + " = " + app.getWordScore(word) + " points");
            }
            else
                System.out.println(word + " is not a valid word in the dictionary.");
        }
        System.out.println("Exiting the program thanks for playing");
    }
}
