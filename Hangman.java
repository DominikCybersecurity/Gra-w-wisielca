import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    private static ArrayList<String> wordList = new ArrayList<>();
    private static String selectedWord;
    private static StringBuilder guessedWord;
    private static int attemptsLeft;
    private static ArrayList<Character> guessedLetters = new ArrayList<>();

    public static void main(String[] args) {
        initializeWordList();
        startGame();
    }

    private static void initializeWordList() {
        wordList.add("losos");
        wordList.add("pstrag");
        wordList.add("sledz");
        wordList.add("karp");
        wordList.add("tunczyk");
        wordList.add("makrela");
        wordList.add("wegorz");
        wordList.add("dorsz");
        wordList.add("sandacz");
        wordList.add("szczupak");
    }

    private static void startGame() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        selectedWord = wordList.get(random.nextInt(wordList.size()));
        guessedWord = new StringBuilder(repeat('_', selectedWord.length()));
        attemptsLeft = 6; // setting 6 attempts as default

        System.out.println("Welcome to Hangman!");
        System.out.println("Try to guess the word.");

        while (attemptsLeft > 0 && guessedWord.toString().contains("_")) {
            System.out.println("Guessed word so far: " + guessedWord);
            System.out.println("Attempts left: " + attemptsLeft);
            System.out.print("Enter a letter: ");
            String input = scanner.nextLine().toLowerCase();
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Invalid input. Please enter a single letter.");
                continue;
            }
            char guess = input.charAt(0);

            if (guessedLetters.contains(guess)) {
                System.out.println("You already guessed that letter.");
            } else {
                guessedLetters.add(guess);
                if (selectedWord.contains(String.valueOf(guess))) {
                    for (int i = 0; i < selectedWord.length(); i++) {
                        if (selectedWord.charAt(i) == guess) {
                            guessedWord.setCharAt(i, guess);
                        }
                    }
                } else {
                    attemptsLeft--;
                }
            }
        }

        if (guessedWord.toString().equals(selectedWord)) {
            System.out.println("Congratulations! You guessed the word: " + selectedWord);
        } else {
            System.out.println("Sorry, you ran out of attempts. The word was: " + selectedWord);
        }
        scanner.close();
    }

    private static String repeat(char c, int times) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < times; i++) {
            builder.append(c);
        }
        return builder.toString();
    }
}
