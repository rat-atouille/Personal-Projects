package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner; 

/**
 * Represents the logic and state of the gameplay in the system, managing game levels, scores, and game data.
 * Implements the LevelBuilder interface to provide methods for game level management.
 * @author Victor Tan
 * @version 7.0
 */

public class GamePlay implements LevelBuilder {
    // Game state variables
    private int level; // Current game level
    private int user_score; // Current user score
    private int incorrect; // Number of incorrect answers
    private String currentCapital; // Currently focused capital city in the game
    private List<String> locations; // List of location names used in the game

    private Random random; // Random number generator for various game functionalities
    private String curr_question; // Current question being presented to the user


    private List<String> facts; // List of facts corresponding to each location

    private String[] easy; // Array to store game state for easy level
    private String[] normal; // Array to store game state for normal level
    private String[] hard; // Array to store game state for hard level

   
    public  GamePlay(){
        
    }

    public void clearLocations() {
        locations.clear();
    }
    
    private static String FILE_PATH = "facts.csv"; // File path for the CSV file containing game facts

    /**
     * Initializes a new GamePlay instance, loading game facts from a CSV file and setting up the initial game state.
     */
    {
        locations = new ArrayList<>();
        facts = new ArrayList<>();
        loadFactsFromCSV(FILE_PATH);
    }
    /**
     * Constructs a GamePlay object with a specified initial level, setting up the game environment accordingly.
     * @param level The starting level of the game.
     */
    public GamePlay(int level) {
        this.level = level;
        this.user_score = 0;
        this.incorrect = getLives(level);
        random = new Random(); // Initialize random before calling firstChain
        curr_question = firstChain(); // Now random is initialized and can be used in firstChain
    }
    /**
     * Constructs a GamePlay object with specific game state parameters, used for loading a saved game.
     * @param user_level The level of the user.
     * @param user_scores The score of the user.
     * @param chain The current question chain.
     * @param incorrect The number of incorrect answers.
     */
    public GamePlay(int user_level, int user_scores, String chain, int incorrect) {
        this.level = user_level;
        this.user_score = user_scores;
        this.incorrect = incorrect;
        this.curr_question = chain;
        random = new Random();
    }
    /**
     * Loads game facts from a specified CSV file and stores them in the locations and facts lists.
     * @param filePath The path to the CSV file containing the game facts.
     */
    public void loadFactsFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    locations.add(parts[0].trim());
                    facts.add(parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading facts from CSV: " + e.getMessage());
        }
    }

    /**
     * Generates the first question of the game by selecting a random location.
     * @return The first capital city to start the game's question chain.
     */
    public String firstChain() {
        if (locations.isEmpty()) return "No data";
        int index = random.nextInt(locations.size());
        currentCapital = locations.get(index);
        curr_question = currentCapital;
        return currentCapital;
    }


    // Getter methods
    public List<String> getLocations() {
        return locations;
    }

    public List<String> getFacts() {
        return facts;
    }

    public int getLevel() {
        return level;
    }
    public int getScores() {return user_score;}
    public String getCurrentQuestion() {
        return curr_question;
    }
    public int getIncorrect() {
        return incorrect;
    }

    /**
     * Sets the difficulty level for the game.
     * @param level The new level to set for the game.
     */
    public void setLevelDifficulty(int level) {
        this.level = level;
    }
    /**
     * Retrieves a fact associated with a specified location.
     * @param location The location for which to retrieve the associated fact.
     * @return The fact related to the given location, or a message indicating that the fact is not found.
     */
    public String getFact(String location) {
        int index = locations.indexOf(location);
        return index >= 0 ? facts.get(index) : "Fact not found";
    }
    /**
     * Determines the points awarded for answering questions based on the game's difficulty level.
     * @param level The difficulty level of the game.
     * @return The points awarded for the specified level.
     */
    public int getPoints(int level){
        switch (level) {
            case 0:
                return 6;
            case 1:
                return 4;
            case 2:
                return 2;
            default:
                return 0;
        }
    }
    /**
     * Determines the number of lives a player starts with based on the game's difficulty level.
     * @param level The difficulty level of the game.
     * @return The initial number of lives for the specified level.
     */
    public int getLives(int level) {
        switch (level) {
            case 0:
                return 7;
            case 1:
                return 5;
            case 2:
                return 3;
            default:
                return 0;
        }
    }
    /**
     * Checks if the user's answer is correct by comparing it against the game's current state.
     * @param question The current question.
     * @param input The user's answer.
     * @return {@code true} if the answer is correct; {@code false} otherwise.
     */
    public boolean checkAns(String question, String input) {
        if (input == null || question == null || input.trim().isEmpty() || question.trim().isEmpty()) {
            System.out.println("Input or question is null or empty");
            return false;
        }

        input = input.trim();
        boolean isInPossibleAnswers = locations.contains(input);
        char lastCharOfQuestion = Character.toLowerCase(question.charAt(question.length() - 1));
        char firstCharOfInput = Character.toLowerCase(input.trim().charAt(0));
        boolean isCharMatch = lastCharOfQuestion == firstCharOfInput;

        return  isInPossibleAnswers && isCharMatch && (!input.equals(question) && !question.equals(input));
    }
    /**
     * Saves the current state of the game for the user.
     * @param user_level The current level of the user.
     * @param user_scores The current score of the user.
     * @param currlocation The current location in the game.
     * @param user_incorrect The number of incorrect answers.
     */
    public void saveGame(int user_level, int user_scores, String currlocation, int user_incorrect) {
        level = user_level;
        user_score = user_scores;
        curr_question = currlocation;
        incorrect = user_incorrect;
    }

    @Override
    public String toString() {
        return level + "," + user_score + "," + "," + incorrect + "," + curr_question;
    }


    /**
     * Loads a previously saved game state based on the difficulty level.
     * @param level The difficulty level of the game to load.
     */
    public void loadGame(int level){
        if (level == 0 && easy != null) {
            this.level = Integer.parseInt(easy[0]);
            user_score = Integer.parseInt(easy[1]);
            curr_question = easy[2];
            incorrect = Integer.parseInt(easy[3]);
        }
        if (level == 1 && normal != null) {
            this.level = Integer.parseInt(normal[0]);
            user_score = Integer.parseInt(normal[1]);
            curr_question = normal[2];
            incorrect = Integer.parseInt(normal[3]);
        }
        if (level == 2 && hard != null) {
            this.level = Integer.parseInt(hard[0]);
            user_score = Integer.parseInt(hard[1]);
            curr_question = hard[2];
            incorrect = Integer.parseInt(hard[3]);
        }
    }

    /**
     * Reads the user data from a CSV file and stores it in arrays.
     * @param user The user identifier used to locate the CSV file.
     */
    public void readCSVAndStore(String user) {
        String filePath = user + ".csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] parts = line.split(",");
                int level = Integer.parseInt(parts[0].trim());
                int userScore = Integer.parseInt(parts[1].trim());
                String currQuestion = parts[2].trim();
                int incorrect = Integer.parseInt(parts[3].trim());

                switch (level) {
                    case 0:
                        easy = new String[]{String.valueOf(level), String.valueOf(userScore), currQuestion, String.valueOf(incorrect)};
                        break;
                    case 1:
                        normal = new String[]{String.valueOf(level), String.valueOf(userScore), currQuestion, String.valueOf(incorrect)};
                        break;
                    case 2:
                        hard = new String[]{String.valueOf(level), String.valueOf(userScore), currQuestion, String.valueOf(incorrect)};
                        break;
                    default:
                        // Handle invalid level
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }
    /**
     * Saves the current game state to a CSV file.
     * @param user The username to associate with the saved game data.
     * @param givenLevel The current level of the game to be saved.
     */
    public void saveToCSV(String user, int givenLevel) {
        String fileName = user + ".csv";
        try (FileWriter file = new FileWriter(fileName)) {
            file.write((givenLevel) + "," + user_score + "," + curr_question + "," + incorrect + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the data to CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


