package game;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

/** Data class which creates a linked list of the Player object storing game and user data
 * Includes methods to create and retrieve CSV file containing data stored locally
 * @version 1.0
 * @author Maneet Chahal
 */
public class Data {
    /** The Player object, as user variable */
	public Player user;
    /** dataList the linked list which contains Player objects */
	public static LinkedList<Player> dataList = new LinkedList<>(); 
    /** Another data list, in an arrayList form to store Player Data */
    public ArrayList<Player> storePlayerData = new ArrayList<>(); 
    /** static Data instance for singleton instance */
    private static Data instance = null;
        

    /** Empty Data constructor */
    public Data() {}

    /** Static method to get the singleton instance 
     * @return instance which is the singleton instance
    */
    public static Data getInstance() {
        if (instance == null) { // if the instance is null then make new data
            instance = new Data();
        }
        return instance;
    }
    
	/** Method to add player to the linked list, does an admin check as well as exisiting data check
     * @param user Player object to be added in list
     * @return exit out of the method if user is admin
     * @return exit out after the data is updated if the user already exists
     */
	public void addPlayer(Player user) {
        String id = user.getId();
        for (Player player : dataList) { // for each player object in the list ... 
            if (player.isAdmin()) { // check for admin
                return; // return if it is admin, to skip adding an admin user
            }
            if (player.getId().equals(id)) { // Check if the player already exists
                player.update(user);  // Update existing player's data
                return; // Exit the method after updating
            }
        }
        // If the player doesn't exist in the list, add them
        dataList.add(user);
    }

    /** Method to remove duplicates if any where added in the data list */
    public void removeDup() {
        HashSet<String> uniqueIds = new HashSet<>(); // make hashset for different ids
        LinkedList<Player> newList = new LinkedList<>(); // create a new list
    
        for (Player player : dataList) { // for each player in list ...
            String id = player.getId(); // get the id
            if (!uniqueIds.contains(id)) { // check if the hashset has the id
                uniqueIds.add(id); // if it doesnt add to hashset
                newList.add(player); // then add new to newlist
            }
        }
        dataList.clear(); // remove old data
        dataList.addAll(newList); // add new
    }
        
    /** Add Player to the array list
     * @param user the Player object to be added
     */
    public void arrayListAddPlayer(Player user) {
        if (!checkDuplicateId(user.getId())) { // compare id and then add user
            storePlayerData.add(user);
        }
    }
        
    /** Check if there is a duplicate ID in the linked list
     * @param id The ID to check for duplication
     * @return true if the ID is already in the list, false otherwise
     */
    public boolean checkDuplicateId(String id) {
        for (Player player : dataList) { // check list of players
            if (player.getId().equals(id)) {  // compare id
                return true;
            }
        }
        return false;
    }
    
    /** Method to check for duplicate id in the array list
     * @param id The user id in String
     * @return true if player exists
     * @return false otherwise
    */
    public boolean arrayListcheckDuplicateID(String id) {
        for (Player player : storePlayerData) { // check list
            if (player.getId().equals(id)) { // compare id
                return true;
            }
        }
        return false;
    }

    /** Method to retrieve player from linked list based on provided id
     * The method will go through the list comparing the id until match is found
     * @param id String id or username of Player to retrieve from list
     * @return player the Player object when found or else null if not found
     */
	public Player getPlayer(String id){
		for (int i=0; i < dataList.size(); i++){ // elements in list
			Player player = dataList.get(i); // get the player
			if (player.getId().equals(id)){ // check and compare id
				return player; // return if found
			}
		}
		return null; // null if not found
	}
        
    /** Get player to retrieve the player from the array list
     * @param id the user String id to retireve from the list
     * @return player if found
     * @return null otherwise
     */
    public Player arrayListGetPlayer(String id) {
        for (Player player : storePlayerData) { // check player in arraylist
            if (player.getId().equals(id)) { // compare id
                return player; // return if found
            }
        }
        return null;
    }

	/** Method to create CSV file to be stored locally
     * Uses the toString method from Player and adds to file
     */
    public void updateData() {
        String fileName = "CapitalGameData.csv"; // declare file name
    
        try (FileWriter file = new FileWriter(fileName)) { // create new file
            for (Player player : dataList) { // iterate through list
                if (!player.isAdmin()) { // check if is an admin
                    file.append(player.toString()).append("\n"); // append the to string of player to file
                }
            }
            file.close();  // close file
        } catch (IOException e) { // catch exception 
            e.printStackTrace();
        }
    }

    /** Method to retrieve the Data from file, and add to linked list
     * Will go through the CSV file, splitting lines and commas into array
     * Then store the data in each variable which will then create the player
     * object to then be added onto the data list. 
     */
	public void retrieveData() {
        String fileName = "CapitalGameData.csv"; // declare the file name

        try (BufferedReader file = new BufferedReader(new FileReader(fileName))) { // create new reader
            String line; // line variable
    
            while ((line = file.readLine()) != null) { // read through until empty file
                String[] data = line.split(","); // split data at the comma and make array
                String id = data[0]; // store id from index 0
                int pin = Integer.parseInt(data[1]); // store pin from index 1
                int progress = Integer.parseInt(data[5]); // store progress from index 5

                // to store the highscores from indices 2-4
                int[] highscore = new int[3]; // create new array
                for (int i = 0; i < 3; i++) { // for loop to add data in array
                    highscore[i] = Integer.parseInt(data[2+i]); // add the data
                }

                // to store the boolean achievements from indices 6-8
                Boolean[] achievement = new Boolean[3]; // new array
                for (int i = 0; i < 3; i++) { // for loop to add data
                    achievement[i] = Boolean.parseBoolean(data[6 + i]); // add the data as booleans
                }

                Player player = new Player(id, pin, highscore, progress, achievement); // create player object with the data read
                dataList.add(player); // add the player to the list
            }
        } catch (IOException e) { // catch exception
            e.printStackTrace();
        }
    }

}
