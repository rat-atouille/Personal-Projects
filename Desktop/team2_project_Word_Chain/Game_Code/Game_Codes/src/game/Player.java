package game;
/**
 * Player class which creates the player object
 * Allows users to create an account ,start game,
 * and view their scores and achievements.
 * @version 4.0
 * @author Maneet Chahal
 */
public class Player {
	/** The player's ID or username */
	private String id;
	/** The pin used if instructor player */
	private int pin;
	/** represents saved game, for easy,med,hard */
	private GamePlay[] load = new GamePlay[3];
	/** represents high scores for each level, default to 0 */
	private int [] highscore = new int[3];
	/** represents progress, where the user currently stands, default to 0 */
	private int progress = 0;
	/** The achievements as boolean array for each level, true if achieved */
        private Boolean[] achievement = {false, false, false};
        /** levels completed array for what levels have been completed */
        private Boolean[] levelsCompleted = {false, false, false};
	
	/** Player constructor. Creates new player object.
	 * @param id String for the player or user's ID
	 * @param pin int pin which is default 0 since it is not an instructor
     * @param highscore array of integers for high scores
     * @param progress int for the player's progress
     * @param achievement Boolean array for achievements
	 */
	public Player(String id, int pin, int[] highscore, int progress, Boolean[] achievement) {
    	this.id = id;
		this.pin = 0;
        this.highscore = highscore;
        this.progress = progress;
        this.achievement = achievement;
    }

	/** Second Player constructor to create a user, with null data
	 * @param id String for the user ID
	 */
    public Player (String id) {
        this.id = id;
    }
        
	/** Update method to update the exisiting player data with new data */
	public void update(Player newData) {
		this.pin = newData.getPin();
		this.load = newData.getLoad();
		this.highscore = newData.getHighscore();
		this.progress = newData.getProg();
		this.achievement = newData.getAchievement();
	}

	/** Setter for user ID
	 * @param id the User ID
	 */
	public void setId(String id) {
            this.id = id;
	}
	
	/** Getter for user ID
	 * @return returns the ID
	 */
	public String getId() {
            return id;
	}

	/**Getter to retrieve Pin 
	 * @return the pin int
	*/
	public int getPin(){
		return pin;
	}

	/** Getter to get progress of user
	 * @return progress int
	 */
	public int getProg(){
		return progress;
	}

	/** Setter to set the progress to new int 
	 * @param newProg the int to change progress to
	*/
	public void setProg(int newProg){
		progress = newProg;
	}
        
    /**Getter to retrieve the load array
	 * @return load
	 */
	public GamePlay[] getLoad(){
		return load;
	}
	
	/** Setter to save game accordingly
	 * @param game GamePlay object representing the game data
	 * @param pos int for the position of GamePlay array
	 */
	public void setGame(GamePlay game, int pos) {
            load[pos] = game;
	}

	/** Getter Method to retrieve the game data
	 * @param pos int for the position in array
	 * @return the game object in array if saved otherwise null
	 */
	public GamePlay getGame(int pos) {
            if (load[pos]!= null) {
                return load[pos];
			}
            else return null;
	}
	
	/** Method to set the user's highscores for each level
	 * @param userHighscore int array for each level's highest score
	 */
	public void setHighscore(int pos, int user_highscore) {
            highscore[pos] = user_highscore;
	}

	/** Method to retrieve high scores int array
	 * @return int array which contains high scores
	 */
	public int[] getHighscore() {
            return highscore;
	}

	/** Method to retrieve high score at indicated position
	 * @return high score int
	 */
	public int getHighscore(int pos) {
		return highscore[pos];
	}
	
	/** Method to retrieve achievement
	* @return achievement as boolean array
	*/
	public Boolean[] getAchievement() {
		return achievement;
	}
	
	/** Method to set achievements
	 * @param pos int for position in array
	 */
	public void setAchievement(int pos) {
            achievement[pos] = true; // achievement unlocked
	}
        
	/** method to get boolean array for which levels are completed
	 * @return boolean array of levels
	 */
    public Boolean[] getLevelsCompleted() {
        return this.levelsCompleted;
    }

	/** sets the boolean array once the level is completed
	 * @param pos for position in array
	 * @param val for value
	 */
	public void setLevelsCompleted(int pos, Boolean val){
		levelsCompleted[pos] = val;}


	/** getter to retrieve level of each levels completed array
	 * @return 3 for corresponding level
	 * @return 2 for corresponding level
	 * @return 1 for corresponding level
	 * @return 0 otherwise...
	 */
	public int getLevel() {
		if (levelsCompleted[2]) {
			return 3;
		}
		else if (levelsCompleted [1]) {
			return 2;
		}
		else if (levelsCompleted[0]) {
			return 1;
		}
		else {
			return 0;
		}
	}

	
        
	/** Method to check is the user or player is an admin
	 * @return true if the user is an admin, checked by seeing if admin id is a match
	 * false otherwise
	 */
	public Boolean isAdmin(){
		if (id.equals("admin@2398!")) { 
			return true;
		}
		return false;
	}


	/** Method to convert Player into String format seperated by commas
	 * This will later be used in Data class to export into csv file
	 * Format: id,pin,hseasy,hsmed,hshard,progress,achiveeasy,achivemed,achivehard
	 * @return String of player object seperated by commas
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder(); // creating new string builder
		sb.append(id).append(",").append(pin).append(","); // appending the id with the comma, then pin then comma

		for (int i = 0; i < highscore.length; i++) { // appending the highscores array as strings
			sb.append(highscore[i]);
			sb.append(","); // add commma
		}

		sb.append(progress).append(","); // appending progress 

		for (int i = 0; i < achievement.length; i++) { // appending the achievements array
			sb.append(achievement[i]);
			if (i < achievement.length - 1) { // to ensure that last element does not add , 
				sb.append(",");
			}
		}
		return sb.toString(); // return string
	}
}
