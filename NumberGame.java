import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int maxNumber = 100;
        int maxAttempts = 10;
        int round = 1;
        int totalScore = 0;

        System.out.println("Welcome to the Unique Number Game!");

        do {
            int targetNumber = random.nextInt(maxNumber) + 1;
            int attempts = 0;
            int score = 0;

            System.out.println("\nRound " + round + " - Guess the Number (1 to 100)");
            System.out.println("I have generated a random number between 1 and 100. Make your first guess.");

            while (attempts < maxAttempts) {
                int guess = 0;
                try {
                    guess = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    continue;
                }

                attempts++;
                if (guess == targetNumber) {
                    score = maxAttempts - attempts + 1;
                    break;
                } else if (guess < targetNumber) {
                    System.out.println("Too low. Try again!");
                } else {
                    System.out.println("Too high. Try again!");
                }
            }

            System.out.println("\n------------------------------------");
            if (score > 0) {
                System.out.println("Congratulations! You guessed the number " + targetNumber + " correctly!");
                System.out.println("Your score for Round " + round + " is: " + score);
            } else {
                System.out.println("Oops! You ran out of attempts. The correct number was: " + targetNumber);
            }
            System.out.println("------------------------------------");

            totalScore += score;
            round++;

            System.out.print("Would you like to play another round? (Yes/No): ");
            String playAgain = scanner.nextLine();

            if (!playAgain.equalsIgnoreCase("Yes")) {
                break;
            }
        } while (true);

        System.out.println("\nThank you for playing the Unique Number Game!");
        System.out.println("Your final total score is: " + totalScore);
        scanner.close();
    }
}
