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

        System.out.println("Witaj w grze w wisielca!");
        System.out.println("Sproboj zgadnac slowo.");

        while (attemptsLeft > 0 && guessedWord.toString().contains("_")) {
            System.out.println("Do tej pory zgadles: " + guessedWord);
            System.out.println("Pozostalo prob: " + attemptsLeft);
            System.out.print("Wprowadz litere: ");
            String input = scanner.nextLine().toLowerCase();
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Niepoprawne dane, wprowadz jedna litere.");
                continue;
            }
            char guess = input.charAt(0);

            if (guessedLetters.contains(guess)) {
                System.out.println("Juz zgadles te litere.");
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
            System.out.println("Swietnie, zgadles slowo!: " + selectedWord);
        } else {
            System.out.println("Niesetety, slowo to: " + selectedWord);
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
