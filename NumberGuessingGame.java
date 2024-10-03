import java.util.Scanner;
import java.util.Random;

class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalRounds = 0;
        int totalScore = 0;
        boolean playAgain;

        System.out.println("Welcome to the Number Guessing Game!");

        do {
            // Start a new round
            totalRounds++;
            int attempts = 0;
            int maxAttempts = 7;
            int numberToGuess = random.nextInt(100) + 1; // Random number between 1 and 100
            boolean hasWon = false;

            System.out.println("\nRound " + totalRounds + ":");
            System.out.println("I have generated a number between 1 and 100. Can you guess it?");
            System.out.println("You have " + maxAttempts + " attempts to guess the number.");

            // Loop for user attempts
            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You've guessed the correct number.");
                    totalScore += (maxAttempts - attempts + 1); // Score based on remaining attempts
                    hasWon = true;
                    break;
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Too low! Try again.");
                }

                System.out.println("Attempts remaining: " + (maxAttempts - attempts));
            }

            // End of the round
            if (!hasWon) {
                System.out.println("Sorry, you've used all your attempts. The correct number was: " + numberToGuess);
            }

            // Ask the user if they want to play again
            System.out.print("\nDo you want to play another round? (yes/no): ");
            String userResponse = scanner.next();
            playAgain = userResponse.equalsIgnoreCase("yes");

        } while (playAgain);

        // Display the final score
        System.out.println("\nGame Over! You've played " + totalRounds + " rounds.");
        System.out.println("Your final score is: " + totalScore);

        scanner.close();
    }
}
