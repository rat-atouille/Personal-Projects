import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Import necessary libraries for JUnit testing and file operations.

/**
 * Tests the functionality of the GamePlay class, ensuring it behaves as expected across various scenarios.
 * This includes testing the loading of game facts from CSV files, both valid and invalid paths, and assessing
 * the core game mechanics like generating question chains, setting difficulty levels, retrieving facts for locations,
 * calculating points and lives based on difficulty, and the ability to save and load game states.
 * 
 * @author Victor Tan
 */
public class GamePlayTest {
	
	// Declare variables used in the tests.
    GamePlay gamePlay;
    String validFilePath = "FILEPATH";
    String invalidFilePath = "D:\\path\\to\\nonexistent\\file.csv";
    
    /**
     * Sets up the testing environment before each test. Initializes the GamePlay instance with a default level
     * and preloads facts from a CSV file for consistent testing conditions.
     */
    @BeforeEach
    void setUp() {
        gamePlay = new GamePlay(1); // Initialize GamePlay with a default level for consistent testing.
        gamePlay.loadFactsFromCSV("FILEPATH"); // Load initial data for testing from a predefined path.
    }

    /**
     * Verifies that facts can be successfully loaded from a valid CSV file path, ensuring that
     * the locations and facts data structures within GamePlay are populated as expected.
     */
    @Test
    void testLoadFactsFromCSVWithValidPath() {
        gamePlay.loadFactsFromCSV(validFilePath); // Test loading from a valid file path.
        assertFalse(gamePlay.getLocations().isEmpty()); // Check if locations are loaded.
        assertFalse(gamePlay.getFacts().isEmpty()); // Check if facts are loaded.
    }

    /**
     * Tests the GamePlay's response to attempting to load facts from an invalid CSV file path.
     * Ensures that the locations data structure remains empty to confirm that no invalid data is loaded.
     */
    @Test
    void testLoadFactsFromCSVWithInvalidPath() {
        gamePlay.clearLocations(); // Clear existing locations to test loading from invalid path.
        gamePlay.loadFactsFromCSV(invalidFilePath); // Attempt to load from an invalid path.
        assertTrue(gamePlay.getLocations().isEmpty(), "Locations list should be empty after loading from an invalid path");
    }

    /**
     * Validates the functionality of generating the first link in the game's question chain,
     * ensuring the method returns a valid location that exists within the game's locations list.
     */
    @Test
    void testFirstChain() {
        String capital = gamePlay.firstChain(); // Test generation of the first question chain link.
        assertNotNull(capital); // Ensure a location is returned.
        assertTrue(gamePlay.getLocations().contains(capital)); // Check if the location is valid.
    }

    /**
     * Tests the firstChain method's behavior when there are no locations loaded,
     * expecting a specific return value indicating the absence of data.
     */
    @Test
    void testFirstChainWithEmptyLocations() {
        GamePlay emptyGamePlay = new GamePlay(1); // Setup a GamePlay instance with no locations.
        emptyGamePlay.clearLocations(); // Ensure no locations are loaded.
        emptyGamePlay.loadFactsFromCSV(invalidFilePath); // Attempt to load invalid data.
        assertEquals("No data", emptyGamePlay.firstChain(), "firstChain should return 'No data' when locations are empty");
    }

    /**
     * Tests setting and retrieving the difficulty level of the game,
     * verifying that the level is correctly updated within the GamePlay instance.
     */
    @Test
    void testSetLevelDifficulty() {
        gamePlay.setLevelDifficulty(2); // Set difficulty level to test.
        assertEquals(2, gamePlay.getLevel()); // Verify the level is set correctly.
    }

    /**
     * Verifies the retrieval of a fact for a given location that exists within the game's data,
     * ensuring that a relevant and non-default fact is returned.
     */
    @Test
    void testGetFactWithExistingLocation() {
        gamePlay.loadFactsFromCSV(validFilePath); // Ensure data is loaded for testing.

        String fact = gamePlay.getFact("Utah"); // Attempt to retrieve a fact for a known location.
        assertNotNull(fact, "Fact should not be null for existing location"); // Check fact is retrieved.
        assertNotEquals("Fact not found", fact, "Fact for 'Paris' should be found"); // Ensure a valid fact is returned.
    }

    /**
     * Tests retrieving a fact for a location that doesn't exist in the game data. It ensures that the method
     * correctly handles such cases by returning a predefined error message indicating the fact was not found.
     */
    @Test
    void testGetFactWithNonExistingLocation() {
        gamePlay.loadFactsFromCSV(validFilePath); // Load data for testing.
        String fact = gamePlay.getFact("NonExistingLocation"); // Attempt to get fact for non-existing location.
        assertEquals("Fact not found", fact); // Check the method returns an error message correctly.
    }

    /**
     * Verifies the point calculation functionality of the GamePlay class for different difficulty levels.
     * This test ensures that the correct number of points is returned for each predefined difficulty level,
     * and checks the behavior when an undefined level is queried.
     */
    @Test
    void testGetPoints() {
        assertEquals(6, gamePlay.getPoints(0)); // Test point calculation for easy level.
        assertEquals(4, gamePlay.getPoints(1)); // Test point calculation for normal level.
        assertEquals(2, gamePlay.getPoints(2)); // Test point calculation for hard level.
        assertEquals(0, gamePlay.getPoints(3)); // Test point calculation for non-existent level.
    }

    /**
     * Tests the lives allocation based on different game difficulty levels in the GamePlay class.
     * This test confirms that the correct number of lives is assigned for each level and verifies
     * the method's response to an invalid difficulty level.
     */
    @Test
    void testGetLives() {
        assertEquals(7, gamePlay.getLives(0)); // Test life allocation for easy level.
        assertEquals(5, gamePlay.getLives(1)); // Test life allocation for normal level.
        assertEquals(3, gamePlay.getLives(2)); // Test life allocation for hard level.
        assertEquals(0, gamePlay.getLives(-1)); // Test life allocation for invalid level.
    }

    /**
     * Tests the GamePlay class's answer checking functionality. It verifies that correct answers
     * are recognized as such, incorrect answers are properly identified, and that the method handles
     * null and empty inputs gracefully without erroneous behavior.
     */
    @Test
    void testCheckAns() {
        gamePlay.loadFactsFromCSV(validFilePath); // Load data for testing.
        String question = gamePlay.firstChain(); // Generate a question for testing answer checking.

        assertNotNull(question, "Question should not be null or empty"); // Ensure a question is generated.
        assertNotEquals("", question, "Question should not be empty"); // Verify the question is not empty.

        char lastChar = question.charAt(question.length() - 1); // Logic for generating a valid answer.
        String validAnswer = lastChar + "someValidContinuation"; // Construct a valid answer.

        assertTrue(gamePlay.checkAns(question, validAnswer), "Valid answer should return true"); // Check valid answer.

        assertFalse(gamePlay.checkAns(question, "wrong answer"), "Incorrect answer should return false"); // Check invalid answer.

        assertFalse(gamePlay.checkAns(null, null), "Null question and answer should return false"); // Test null inputs.
        assertFalse(gamePlay.checkAns("", ""), "Empty question and answer should return false"); // Test empty inputs.
        assertFalse(gamePlay.checkAns(question, ""), "Valid question but empty answer should return false"); // Test valid question with empty answer.
        assertFalse(gamePlay.checkAns("", validAnswer), "Empty question but valid answer should return false"); // Test empty question with valid answer.
    }

    /**
     * Tests the save and load functionality of the game state in the GamePlay class. This test ensures
     * that the game state is correctly saved and can be accurately retrieved, preserving the integrity
     * of the game data across these operations.
     */
    @Test
    void testSaveAndLoadGame() {
        int user_level = 1; // Test data for user level.
        int user_scores = 100; // Test data for user scores.
        String currlocation = "Paris"; // Test data for current location.
        int user_incorrect = 2; // Test data for incorrect answers count.

        gamePlay.saveGame(user_level, user_scores, currlocation, user_incorrect); // Test saving the game state.
        gamePlay.loadGame(user_level); // Test loading the saved game state.

        assertEquals(user_level, gamePlay.getLevel()); // Verify level is correctly loaded.
        assertEquals(user_scores, gamePlay.getScores()); // Verify scores are correctly loaded.
        assertEquals(currlocation, gamePlay.getCurrentQuestion()); // Verify current location is correctly loaded.
        assertEquals(user_incorrect, gamePlay.getIncorrect()); // Verify incorrect count is correctly loaded.
    }

    /**
     * Verifies the functionality of reading from and saving to a CSV file in the GamePlay class.
     * This test checks that data is correctly read from a CSV, processed, and then accurately saved back,
     * ensuring data integrity and the ability to persist game state across sessions.
     */
    @Test
    void testReadCSVAndStoreAndSaveToCSV() {
        String user = "testUser"; // Test data for user.
        String filePath = "C:\\gui\\" + user + ".csv"; // Test file path for storing game data.

        gamePlay.saveToCSV(user, 0); // Test saving game data to CSV.
        gamePlay.readCSVAndStore(user); // Test reading game data from CSV.

        assertNotNull(gamePlay.getLevel()); // Verify level is correctly read.
        assertNotNull(gamePlay.getScores()); // Verify scores are correctly read.
        assertNotNull(gamePlay.getCurrentQuestion()); // Verify current question is correctly read.
        assertNotNull(gamePlay.getIncorrect()); // Verify incorrect count is correctly read.

        // Cleanup test file to avoid clutter.
        try {
            Files.delete(Path.of(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
