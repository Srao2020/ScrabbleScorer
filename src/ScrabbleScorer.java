import java.io.File;
//import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Scrabble Scorer validates and scores scrabble words
 * @version 1/30/23
 * @author Srao2020
 */

public class ScrabbleScorer {
    private static final String ALPHA = "ABCDEFJHIJKLMNOPQRSTUVWXYZ";
    private static final int[] scores = {1, 3, 3, 2,
            1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10,
            1, 1, 1, 1, 4, 4, 8, 4, 10};
    private ArrayList<ArrayList<String>> dictionary;

    /**
     * Constructor to initialize the dictionary.
     */

    public ScrabbleScorer() {
        dictionary = new ArrayList<>();
        for (int i = 0; i < 26; i++)
            dictionary.add(new ArrayList<String>());
        buildDictionary();
    }

    /**
     * Builds the dictionary by reading the words from the file.
     */

    public void buildDictionary() {
        try {
            Scanner in = new Scanner(new File("SCRABBLE_WORDS.txt"));
            while (in.hasNext()) {
                String word = in.nextLine().toUpperCase();
                int index = ALPHA.indexOf(word.charAt(0));
                dictionary.get(index).add(word);
            }
            in.close();
            for (ArrayList<String> words : dictionary) {
                Collections.sort(words);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        //System.out.println(dictionary);
    }

    /**
     * Validates if the given word is in the dictionary.
     * @param word word to be validated
     * @return true if the word is in the dictionary, false otherwise
     */

    public boolean isValidWord(String word) {
        word = word.toUpperCase();
        int index = ALPHA.indexOf(word.charAt(0));
        return Collections.binarySearch(dictionary.get(index), word) >= 0;
    }

    /**
     * Gets the score of the given word.
     * @param word word to get score for
     * @return score of the word
     */

    public int getWordScore(String word) {
        int score = 0;
        for (char c : word.toUpperCase().toCharArray()) {
            int index = ALPHA.indexOf(c);
            score += scores[index];
        }
        return score;
    }

    /**
     * Main method to run the program.
     * @param args command line arguments
     */

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
