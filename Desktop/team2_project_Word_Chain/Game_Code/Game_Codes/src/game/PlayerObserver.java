
package game;

/**
 * @author Simran Kullar
 * @version 1.0
 * The PlayerObserver interface is used to define the methods that any observer of player-related events must implement
 * It follows the Observer design pattern, allowing objects to receive updates about changes in player state or events
 */

public interface PlayerObserver {

    /**
     * Called when there is a new event related to the player that observers need to be informed about
     * Implementing classes should define how they handle these events
     * @param event A string description of the event that occurred
     */
    public void updateEvent(String event);
    /**
     * Called to update observers with the latest scores of the player
     * Implementing classes should define how they handle the score update
     * @param scores The latest scores of the player that needs to be updated to the observers
     */
    public void updateUser(int scores);
}
