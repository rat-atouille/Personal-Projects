import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.After;
import org.junit.Assert;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Tests the functionality of the Leaderboard class.
 * This class ensures that the Leaderboard correctly handles adding new players,
 * identifying the top player based on high scores, retrieving specific players by their ID,
 * changing players' ranks, and properly displaying the leaderboard.
 * <p>
 * The setUp method initializes necessary components before each test, and the tearDown method
 * resets the environment after each test to ensure test isolation.
 * </p>
 * @author Simran Kullar
 * @version 4.0
 */
public class LeaderboardTest {
	// Fields for the Leaderboard and Player instances to be used in the tests
    private Leaderboard leaderboard;
    private Player player1;
    private Player player2;
    private Player player3;
    private File tempFile;
    
  
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    /**
     * Sets up the testing environment before each test.
     * Initializes the leaderboard with predefined players and getting the System.out output
     * to check for expected printed outputs from the leaderboard methods.
     */
    @Before
    public void setUp() throws IOException {
    	System.setOut(new PrintStream(outContent));

        // Initialize player scores
        int[] player1Scores = {100, 200, 300};
        int[] player2Scores = {150, 250, 350};

        // Initialize players with additional attributes if needed
        player1 = new Player("Player1", 1234, player1Scores, 0, new Boolean[]{false, true, false});
        player2 = new Player("Player2", 1234, player2Scores, 1, new Boolean[]{true, false, true});

        // Add players to the list
        LinkedList<Player> players = new LinkedList<>();
        players.add(player1);
        players.add(player2);

        // Initialize the leaderboard with the list of players
        leaderboard = new Leaderboard(players);

        // Create a temporary file for testing CSV operations
        tempFile = File.createTempFile("testHighScores", ".csv");
    }
    /**
     * Restores the testing environment after each test.
     * Resets the System.out output to its original state to avoid interference between tests.
     * Delete the temporary file after each test.
     */
    @After
    public void tearDown() {
        System.setOut(originalOut);

        if (tempFile != null) {
            tempFile.delete();
        }
    }


    /**
     * Tests the ability of the leaderboard to add a new player.
     * Verifies that after adding a player, the player can be retrieved from the leaderboard,
     * indicating successful addition.
     */
    @Test
    public void testAddPlayer() {
        GamePlay[] player3Games = {null, null, null};
        int[] player3Scores = {200, 300, 400};
        Boolean[] player3Achievements = {true, true, false};

        player3 = new Player("Player3", 1234, player3Scores, 2, player3Achievements);
        leaderboard.addPlayer(player3);

        assertNotNull("Leaderboard should contain player3 after adding", leaderboard.getPlayer(player3.getId()));
    }
    
    /**
     * Verifies that the leaderboard correctly identifies the top player based on their high score
     */
    @Test
    public void testTopPlayerValid() {
        Player topPlayer = leaderboard.getTopPlayer();
        assertEquals("The top player should be Player2 based on high scores", player2, topPlayer);
    }
    
    /**
     * Ensures that the leaderboard does not incorrectly identify a player as the top player
     * when they should not be based on their high score
     */
    @Test
    public void testTopPlayerInvalid() {
        Player topPlayer = leaderboard.getTopPlayer();
        assertNotEquals("The top player should be Player1 based on high scores", player1, topPlayer);
    }
    
    /**
     * Tests the retrieval of a player by a valid ID, ensuring the leaderboard can fetch player details
     * correctly when provided with a correct player ID.
     */
    @Test
    public void testGetPlayerValidId() {
        Player retrievedPlayer = leaderboard.getPlayer("Player1");
        assertNotNull("getPlayer should return a non-null Player object for a valid ID", retrievedPlayer);
        assertEquals("Retrieved player should have the ID 'Player1'", "Player1", retrievedPlayer.getId());
    }

    /**
     * Verifies that attempting to retrieve a player by an invalid ID returns null,
     * indicating the player does not exist in the leaderboard.
     */
    @Test
    public void testGetPlayerInvalidId() {
        Player retrievedPlayer = leaderboard.getPlayer("NonExistentPlayer");
        assertNull("getPlayer should return null for a non-existent player ID", retrievedPlayer);
    }
    
    /**
     * Checks the functionality for changing a player's rank within the leaderboard to a valid new position
     * and ensures the change is reflected accurately in the leaderboard.
     */
    @Test
    public void testChangeRankValid() {
        leaderboard.changeRank(player2, 1);
        assertEquals("Player 2 should be at rank 1 after rank change", player2, leaderboard.getTopPlayer());
    }
    
    /**
     * Tests the case where a rank change is attempted for a player not present in the leaderboard.
     * Ensures the method handles this case gracefully without errors.
     */
    @Test 
    public void testChangeRankPlayerNotFound() {
        leaderboard.addPlayer(player3); 
        leaderboard.changeRank(player3, 5);
        assertNotEquals("Player 3 should be at rank 5 after rank change", player3, leaderboard.getTopPlayer());
    }
    
    /**
     * Verifies that invalid rank changes (to a rank outside the valid range) do not affect the leaderboard.
     */
    @Test
    public void testChangeRankInvalid() {
        leaderboard.changeRank(player1, 5);
        assertNotEquals("Player 1 should be at rank 5 after rank change", player1, leaderboard.getTopPlayer());
    }
    
    /**
     * Tests the leaderboard's display functionality by setting new players and checking the output
     * to ensure it contains the expected information about the new players.
     */
    @Test
    public void testSetPlayersThroughOutput() {
        // Use the already declared 'outContent' to capture System.out output
        LinkedList<Player> newPlayers = new LinkedList<>();
        Player newPlayer = new Player("NewPlayer", 1234, new int[]{100, 200, 300}, 3, new Boolean[]{false, true, true});
        newPlayers.add(newPlayer);
        leaderboard.setPlayers(newPlayers);

        leaderboard.displayLeaderboard();

        String output = outContent.toString().trim(); // Trim to remove leading/trailing whitespace
        assertTrue("The output should contain the new player's ID 'NewPlayer'", output.contains("NewPlayer"));

        // No need to reset System.out here as it's handled by the tearDown method
    }
    
    /**
     * Validates that the leaderboard correctly displays all players' information,
     * including IDs and high scores, by getting the output.
     */
    @Test
    public void testDisplayLeaderboard() {
        leaderboard.displayLeaderboard();

        String output = outContent.toString();

        assertTrue("The output should contain the header 'Leaderboard'", output.contains("LEADERBOARD:"));
      
        assertTrue("The output should contain Player1 ID", output.contains(player1.getId()));
        assertTrue("The output should contain Player2 ID", output.contains(player2.getId()));
        
       assertTrue("The output should contain the high score for Player1", output.contains(String.valueOf(leaderboard.getMaxHighScore(player1.getHighscore()))));
       assertTrue("The output should contain the hogh score for Player2", output.contains(String.valueOf(leaderboard.getMaxHighScore(player2.getHighscore()))));
       
    }
    
    /**
     * Validates that the {@code saveHighScoresToCSV} method correctly writes players' scores for a specific level to a CSV file.
     * It tests the functionality by writing to a temporary file, then reads back the contents to check against expected values.
     * The test asserts the correct scores for the first two players at the specified level index are present in the file.
     * @throws IOException if an error occurs during file operations.
     * However this test cannot be tested through Leaderboard 
     */
    @Ignore
    @Test
    public void testSaveHighScoresToCSV() throws IOException {
        System.out.println("saveHighScoresToCSV()");
        System.out.println("Cannot be tested through Leaderboard.");
    }






   
}