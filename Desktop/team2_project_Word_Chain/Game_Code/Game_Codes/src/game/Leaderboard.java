package game;


import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Simran Kullar
 * @version 4.0
 * Represents a leaderboard for tracking and displaying player rankings based on their scores.
 * This class uses a LinkedList to store player information, allowing efficient insertion and removal operations.
 */
public class Leaderboard {
	/**
     * Stores the players in the leaderboard.
     */
    private LinkedList<Player> players; 
    
    /**
     * Constructs a Leaderboard instance with a predefined list of players.
     * @param players The initial LinkedList of players to be included in the leaderboard.
     */
    public Leaderboard(LinkedList<Player> players) {
        // Assigns a new LinkedList containing all the Player objects from the 'players' argument to the 'players' field of this Leaderboard instance
        // This creates a copy of the provided list, so modifications to this list won't affect the original list passed in
        this.players = new LinkedList<>(players); 
    }
    
    /**
     * Adds a new player to the leaderboard. If the player is already in the leaderboard or is null,
     * the method will not add the player and may print a message indicating the issue.
     * @param player The player to be added to the leaderboard.
     */
    public void addPlayer(Player player) {
        // Check if the player object is null. If so, exit the method early without adding the player
        if (player == null) return; 

        // Iterate through the existing players in the leaderboard
        for (Player existingPlayer : players) {
            // Check if the current player's ID matches the ID of the player we're trying to add
            if (existingPlayer.getId().equals(player.getId())) {
                // If a player with the same ID is found, print a message indicating the player already exists and exit the method
                System.out.println("Player with ID " + player.getId() + " already exists.");
                return;
            }
        }
        // If no existing player has the same ID, add the new player to the list of players in the leaderboard
        this.players.add(player);
    }
    
    /**
     * Changes the rank of a given player to a new position. If the new rank is out of current list bounds,
     * the player is moved to the end of the list. If the player is not found, a message may be printed.
     * @param player  The player whose rank is to be changed.
     * @param newRank The new rank position for the player, where the first rank is 1.
     */
    public void changeRank(Player player, int newRank) {
        // Check if the specified player is not in the leaderboard
    	if(!this.players.contains(player)) {
            // If the player is not found, print a message indicating so and exit the method
        	System.out.println("Player not found.");
        	return;
        }
        // Check if the new rank is outside the valid range (less than 1 or greater than the number of players)
    	if (newRank <= 0 || newRank > this.players.size()) {
            // If the rank is invalid, print a message indicating so and exit the method
            System.out.println("Invalid rank.");
            return;
        }
        // Remove the player from their current position in the list
        this.players.remove(player);
        // Add the player back into the list at the new rank position, adjusting for zero-based index
        // Subtract 1 from newRank since list indices are zero-based but ranks are typically one-based
        this.players.add(newRank - 1, player); 
    }
    
    /**
     * Displays the current leaderboard with players' ranks, IDs, and scores. Iterates through the LinkedList
     * of players and prints their details to the console
     */
    public void displayLeaderboard() {
    	// Print a header for the leaderboard to the console
        System.out.println("LEADERBOARD:");
        // Initialize a counter starting from 1, to display ranks starting from 1
        int i = 1;
        // Iterate through each player in the players list
        for (Player player : players) {
            // For each player, retrieve their highest score across all levels
            int highScore = getMaxHighScore(player.getHighscore());
            // Print the rank, player ID, and their highest score to the console
            System.out.println("Rank " + i + " - Player ID: " + player.getId() + ", Highest Score: " + highScore);
            // Increment the rank counter for the next player
            i++;
        }
    }
    
    /**
     * Updates the entire list of players in the leaderboard by replacing it with a new LinkedList of players
     * @param players The new LinkedList of players to replace the current one
     */
    public void setPlayers(LinkedList<Player> players) {
        // Sets the leaderboard's player list to a new list, ensuring independence from the original list
        this.players = new LinkedList<>(players); 
    }
    
    /**
     * Retrieves a player from the leaderboard based on their ID
     * @param playerId The ID of the player to retrieve
     * @return The Player object if found, or null if no player with the given ID exists on the leaderboard
     */
    public Player getPlayer(String playerId) {
        // Loop through each player in the 'players' list
        for (Player player : players) {
            // Check if the current player's ID matches the provided 'playerId'
            if (player.getId().equals(playerId)) {
                // If a match is found, return the current player object
                return player;
            }
        }
        // If no player with the matching ID is found after checking the entire list, return null
        return null; // Player not found
    }
    
    /**
     * Calculates the maximum high score from an array of scores. This method iterates through each score in the array,
     * comparing it to the current maximum score found, and updates the maximum score if a higher score is encountered
     * @param scores An array of integers representing the scores from which the highest score is to be determined
     * @return The highest score found in the provided array. Returns 0 if the array is empty or all scores are 0
     */
    public static int getMaxHighScore(int[] scores) {
        // Initialize a variable to hold the maximum score, starting at 0
        int maxScore = 0;
        // Iterate through each score in the provided array 'scores'
        for (int score : scores) {
            // Check if the current score is greater than the current maximum score
            if (score > maxScore) {
                // If true, update 'maxScore' to this higher value
                maxScore = score;
            }
        }

    // After checking all scores, return the highest score found
    return maxScore;
    }

    /**
     * Retrieves the player with the highest score across all levels.
     * This method iterates through all players, comparing their maximum scores to identify the top scorer.
     * If multiple players share the highest score, the first encountered in the list is returned.
     * @return The {@code Player} object with the highest score, or {@code null} if there are no players.
     */
    public Player getTopPlayer() {
        // Initialize a variable to hold the reference to the player with the highest score, starting as null
        Player topPlayer = null;
        // Initialize a variable to track the highest score found, starting at 0
        int topScore = 0;
        // Iterate through each player in the 'players' list
        for (Player player : players) {
        // Retrieve the highest score for the current player by calling the getMaxHighScore method
        int playerMaxScore = getMaxHighScore(player.getHighscore());

            // Check if 'topPlayer' is still null (which means this is the first player being checked)
            // or if the current player's maximum score is greater than the highest score found so far
            if (topPlayer == null || playerMaxScore > topScore) {
                // Update 'topPlayer' to reference the current player
                topPlayer = player;
                // Update 'topScore' to the new highest score found
                topScore = playerMaxScore;
            }
        }
    // After checking all players, return the player with the highest score found
    return topPlayer;
    }
    /**
     * Saves the high scores of all players for a specified level to a CSV file.
     * Each line in the file represents the score of a player at the given level index.
     * In case of an IOException, an error message is printed to {@code System.err}.
     * @param filename The name of the file to which the scores will be saved.
     * @param levelIndex The index of the level for which scores should be saved.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void saveHighScoresToCSV(String filename, int levelIndex) {
        // Retrieve the current list of players from the Data singleton instance
        LinkedList<Player> players = new LinkedList<>(Data.getInstance().dataList);

        // Sort the list of players in descending order based on their scores at the given levelIndex
        players.sort((p1, p2) -> Integer.compare(p2.getHighscore()[levelIndex], p1.getHighscore()[levelIndex]));

        // Try to open a BufferedWriter to write to the specified filename, ensuring resource is closed after use
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            // Iterate through the list of sorted players
            for (Player player : players) {
                // Retrieve the array of high scores for the current player
                int[] scores = player.getHighscore();
                
                // Check if the array is large enough to contain a score at the specified level index
                if (scores.length > levelIndex) {
                    // Write the score at levelIndex to the file followed by a newline character
                    bw.write(scores[levelIndex] + "\n");
                }
            }
        // Catch any IOExceptions that may occur during file writing
        } catch (IOException e) {
            // If an IOException is caught, print an error message to standard error
            System.err.println("Failed to write to CSV file: " + e.getMessage());
        }
    }
    
}