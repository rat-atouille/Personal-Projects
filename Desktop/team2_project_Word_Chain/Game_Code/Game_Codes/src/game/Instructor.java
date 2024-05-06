
package game;


import java.util.ArrayList;
/**
 * @version 1.0
 * @author Simran Kullar
 * @author Kareena Sen (added getFIXED_PIN)
 * The Instructor class represents an instructor user in the system
 * It implements the User interface and provides methods to login, validate instructor, view player data, and handle user session
 */
public class Instructor {

    /**
     * Stores instructor's PIN
     */
    private int pin;
    /**
     * Constant for fixed PIN assigned to instructor
     */
    private static final int FIXED_PIN = 4351;
    /**
     * Stores User instances
     */ 
    private static ArrayList<Instructor> instructors = new ArrayList<>();
    
    /**
     * Constructor which initializes the list with some users, from database and the id and pin
     */
    public Instructor(int pin) {
        // Add this instructor instance to list and set pin to specified pin
        this.pin = pin;
        instructors.add(this); 
    } 
    
    /**
     * Attempts to login an instructor with a given username
     * @param username The username of the instructor
     * @return true if login is successful, otherwise false 
     */
    public boolean login() {
        // Iterate through each instructor in the 'instructors' list
    	for (Instructor instructor : instructors) {
            // Check if the current instructor's PIN matches the PIN provided for login
            // and if the provided PIN is also equal to the fixed PIN (indicating a valid instructor)
            if (instructor.pin == pin && pin == FIXED_PIN) {
                // If the conditions are met, the login is successful, so return true
                return true;
            }
        }
        // If no matching instructor is found in the list, return false indicating login failure
        return false;
    }
    
    

    /**
     * Validates if the instructor has a valid PIN and exists in the list of instructors.
     * @return true if the instructor's PIN is valid and the instructor exists, otherwise false.
     */
    public boolean validateInstructor() {
        // Set pin to fixed pin
        return this.pin == FIXED_PIN;
    }



    /**
     * Retrieves and compiles the player data including high score and achievements
     * @return A string representation of the player data
     */
    public String viewPlayerData(String playerIdentifier) {
        // Retrieve the singleton instance of the Data class which holds player information
        Data data = Data.getInstance(); 
        // Fetch the player object based on the provided identifier
        Player player = data.getPlayer(playerIdentifier);
        
        // Check if the player exists
        if (player != null) {
            // Retrieve the player's high scores and achievements arrays
            int[] highScores = player.getHighscore();
            Boolean[] achievements = player.getAchievement();

            // Initialize a StringBuilder to construct the player data string
            StringBuilder playerData = new StringBuilder("Player Data: ");

            // Iterate through the high scores array to append high score information for each level
            for (int i = 0; i < highScores.length; i++) {
                playerData.append("Level ").append(i + 1).append(" High Score: ").append(highScores[i]).append(", ");
            }

            // Iterate through the achievements array to append achievement status for each level
            for (int i = 0; i < achievements.length; i++) {
                playerData.append("Level ").append(i + 1).append(" Achievement: ").append(achievements[i] ? "Unlocked" : "Locked");
                // Add a comma separator between achievements unless it's the last one
                if (i < achievements.length - 1) {
                    playerData.append(", ");
                }
            }
            // Convert the StringBuilder content to a String and return it
            return playerData.toString();
        }
        // If the player does not exist, return a message indicating that the data was not found
        return "Player data not found for the specified identifier.";
    }

    /**
     * Retrieves the instructor's PIN
     * @return The instructor's PIN as a String
     */
    public String getPin() {
        // Convert pin to string 
        return String.valueOf(pin);
    }
    
    public static int getFIXED_PIN() {
        // Get the fixed pin
        return FIXED_PIN;
    }

    /**
     * Checks if the user is an instructor based on ID and PIN
     * @param id  The ID of the user to check
     * @param pin The PIN of the user to check
     * @return true if the user is an instructor, otherwise false
     */
    public boolean isInstructor(int pin) {
        // Check if the current object's PIN matches the PIN provided as a parameter
        // AND also check if the provided PIN matches the predefined FIXED_PIN
    	if (this.pin == pin && pin == FIXED_PIN) {
            // If both conditions are true, return true indicating successful validation
            return true;
        }
        else {
            // If any of the conditions fail, return false indicating unsuccessful validation
            return false;
        }
    }

    /**
     * Sets the instructor's PIN
     * @param pin The new PIN for the instructor
     */
    public void setPin(int pin) {
        // Set pin to specified pin
        this.pin = pin;
    }

    /**
     * Logs out the current user and resets the session
     */
    public void logout() {
        // Reset pin (by setting to 0)
        this.pin = 0;
    }
}