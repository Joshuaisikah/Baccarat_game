import java.util.Scanner; // Importing the Scanner class to take user input.

public class Baccarat {
    private static final int INITIAL_BALANCE = 1000; // Setting the initial balance for the player.
    private static final int MIN_BET = 10; // Setting the minimum bet amount.

    private static int wins = 0; // Initializing a variable to count the number of wins.
    private static int losses = 0; // Initializing a variable to count the number of losses.
    private static int totalWinRolls = 0; // Initializing a variable to keep track of the total number of rolls when the player wins.
    private static int totalLossRolls = 0; // Initializing a variable to keep track of the total number of rolls when the player loses.
    private static int gamesPlayed = 0; // Initializing a variable to count the number of games played.

    public static void main(String[] args) { // Starting point of the program.
        playBaccarat(); // Calling the function to start playing Baccarat.
    }

    private static void playBaccarat() { // Function to play the Baccarat game.
        Scanner scanner = new Scanner(System.in); // Creating a Scanner object to take user input.
        int playerBalance = INITIAL_BALANCE; // Setting the player's initial balance.

        System.out.println("Welcome to Baccarat!"); // Welcoming the player to the game.

        int timesToPlay = getValidInput(scanner, "Enter the number of times to play: "); // Getting the number of times the player wants to play.

        while (gamesPlayed < timesToPlay && playerBalance >= MIN_BET) { // Looping until the specified number of games is reached or the player runs out of balance.
            gamesPlayed++; // Incrementing the number of games played.

            System.out.println("\nGame #" + gamesPlayed); // Displaying the current game number.
            System.out.println("Your current balance: $" + playerBalance); // Displaying the player's current balance.

            int maxBet = playerBalance / (timesToPlay - gamesPlayed + 1); // Calculating the maximum bet the player can place in the current round.
            int betAmount = getValidBetAmount(scanner, playerBalance, maxBet); // Getting a valid bet amount from the player.

            String gameOutcome = playRound(); // Simulating a round of the game.
            int numberOfRolls = 2; // Assuming 2 cards are dealt per round.

            if (gameOutcome.equals("Player wins")) { // Checking if the player wins the round.
                wins++; // Incrementing the number of wins.
                totalWinRolls += numberOfRolls; // Adding the number of rolls to the total rolls when the player wins.
                playerBalance += betAmount; // Adding the winning amount to the player's balance.
            } else if (gameOutcome.equals("Banker wins")) { // Checking if the banker wins the round.
                losses++; // Incrementing the number of losses.
                totalLossRolls += numberOfRolls; // Adding the number of rolls to the total rolls when the player loses.
                playerBalance -= betAmount; // Subtracting the bet amount from the player's balance.
            }

            System.out.println("Game outcome: " + gameOutcome); // Displaying the outcome of the game.
            System.out.println("Your current balance: $" + playerBalance); // Displaying the player's updated balance.
        }

        // Displaying the results after all games are played.
        System.out.println("\nResults after " + gamesPlayed + " games:");
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
        double averageRollsPerWin = (wins != 0) ? totalWinRolls / (double) wins : 0;
        double averageRollsPerLoss = (losses != 0) ? totalLossRolls / (double) losses : 0;
        System.out.println("Average Rolls per Win: " + averageRollsPerWin);
        System.out.println("Average Rolls per Loss: " + averageRollsPerLoss);
        double winningPercentage = (gamesPlayed != 0) ? (double) wins / gamesPlayed * 100 : 0;
        System.out.println("Winning Percentage: " + winningPercentage + "%");
    }

    private static int getValidInput(Scanner scanner, String prompt) { // Function to get valid input from the user.
        while (true) { // Loop until valid input is received.
            try {
                System.out.print(prompt); // Displaying the prompt.
                return Integer.parseInt(scanner.nextLine()); // Parsing and returning the user input as an integer.
            } catch (NumberFormatException e) { // Catching exceptions for invalid number format.
                System.out.println("Invalid input. Please enter a valid number."); // Displaying an error message.
            }
        }
    }

    private static int getValidBetAmount(Scanner scanner, int playerBalance, int maxBet) { // Function to get a valid bet amount from the user.
        while (true) { // Loop until valid bet amount is received.
            try {
                System.out.print("Place your bet (Minimum bet: $" + MIN_BET + ", Maximum bet: $" + maxBet + "): "); // Displaying the prompt.
                int betAmount = Integer.parseInt(scanner.nextLine()); // Parsing and getting the bet amount entered by the user.
                if (betAmount < MIN_BET || betAmount > maxBet || betAmount > playerBalance) { // Checking if the bet amount is valid.
                    throw new IllegalArgumentException("Invalid bet amount."); // Throwing an exception for invalid bet amount.
                }
                return betAmount; // Returning the valid bet amount.
            }catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String playRound() {
        return (Math.random() < 0.5) ? "Player wins" : "Banker wins";
    }
}