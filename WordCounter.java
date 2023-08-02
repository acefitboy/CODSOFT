import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Word Counter!");

        System.out.print("Do you want to enter text manually or provide a file? (Enter 'text' or 'file'): ");
        String inputChoice = scanner.nextLine();

        String text;
        if (inputChoice.equalsIgnoreCase("text")) {
            System.out.print("Enter the text: ");
            text = scanner.nextLine();
        } else if (inputChoice.equalsIgnoreCase("file")) {
            System.out.print("Enter the file path: ");
            String filePath = scanner.nextLine();
            text = readFile(filePath);
            if (text == null) {
                System.out.println("Error reading the file. Please make sure the file exists and try again.");
                scanner.close();
                return;
            }
        } else {
            System.out.println("Invalid input. Please choose 'text' or 'file'.");
            scanner.close();
            return;
        }

        int wordCount = countWords(text);
        int uniqueWordCount = countUniqueWords(text);

        System.out.println("Total word count: " + wordCount);
        System.out.println("Total unique word count: " + uniqueWordCount);

        scanner.close();
    }

    private static String readFile(String filePath) {
        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);
            StringBuilder sb = new StringBuilder();
            while (fileScanner.hasNextLine()) {
                sb.append(fileScanner.nextLine()).append("\n");
            }
            fileScanner.close();
            return sb.toString();
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    private static int countWords(String text) {
        String[] words = text.split("[\\s\\p{Punct}]+");
        return words.length;
    }

    private static int countUniqueWords(String text) {
        String[] words = text.split("[\\s\\p{Punct}]+");
        Set<String> uniqueWords = new HashSet<>();
        for (String word : words) {
            uniqueWords.add(word.toLowerCase());
        }
        return uniqueWords.size();
    }
}
