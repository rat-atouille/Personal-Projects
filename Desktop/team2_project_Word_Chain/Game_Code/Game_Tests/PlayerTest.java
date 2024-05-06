import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Class PlayerTest to test methods created in Player class
 * @version 1.0
 * @author Maneet Chahal
 */
public class PlayerTest {

    /** Method BeforeAll type which is to set up class
     * However it is not needed for this test
     */
    @BeforeAll
    public static void setUpClass() {
        System.out.println("setUpClass()");
    }

    /** Method AfterALl type which is to tear down class
     * However it is not needed for this test
     */
    @AfterAll
    public static void tearDownClass() {
        System.out.println("tearDownClass()");
    }

    /** Method BeforeEach type which is to set up pre test
     * Includes a player object if needed to be used
     */
    @BeforeEach
    void setUp() {
        System.out.println("SetUp()");
        Player player = new Player("john",0,new int[]{100,70,50},3,new Boolean[]{true,false,false});
    }

    /** Method AfterEach type which is to tear down post test
     * However it is not needed for this test
     */
    @AfterEach
    void tearDown() {
        System.out.println("tearDown()");
    }


    /** Test for update method. Will create a player object, then an updated player
     * It will then use the update method and compare each param to see if they match
     */
    @Test
    void testUpdate() {
        Player player = new Player("john",0,new int[]{100,70,50},3,new Boolean[]{true,false,false});
        Player updatedPlayer = new Player("john",0,new int[]{100,100,54},3,new Boolean[]{true,true,false});
        player.update(updatedPlayer);
        assertEquals("john",player.getId());
        assertEquals(0,player.getPin());
        assertArrayEquals(new int[]{100,100,54},player.getHighscore());
        assertEquals(3,player.getProg());
        assertArrayEquals(new Boolean[]{true,true,false}, player.getAchievement());
    }

    /** Test for SetId method, which will create a new object, then set the id
     * to another id, if it matches then it passes the test
     */
    @Test
    void testSetId() {
        Player player = new Player("john",0,new int[]{100,70,50},3,new Boolean[]{true,false,false});
        player.setId("jane");
        assertEquals("jane",player.getId());
    }

    /** Test for getId to see if the correct Id will be returned
     * Player object id is john and if it matches it passes
    */
    @Test
    void testGetId() {
        Player player = new Player("john",0,new int[]{100,70,50},3,new Boolean[]{true,false,false});
        assertEquals("john",player.getId());
    }

    /** Test for getPin to see retrieve pin */
    @Test
    void testGetPin() {
        Player player = new Player("john",0,new int[]{100,70,50},3,new Boolean[]{true,false,false});
        assertEquals(0,player.getPin());
    }

    /** Test for getProgress, returns the value of progress */
    @Test
    void testGetProgress() {
        Player player = new Player("john",0,new int[]{100,70,50},3,new Boolean[]{true,false,false});
        assertEquals(3,player.getProg());
    }

    /** Ignored test for getLoad as it cannot be tested through player */
    @Ignore
    @Test
    void testGetLoad() {
        System.out.println("getLoad()");
        System.out.println("Cannot be tested through Player.");
    }

    /** Ignored test for setGame as it cannot be tested through player */
    @Ignore
    @Test
    void testSetGame() {
        System.out.println("setGame()");
        System.out.println("Cannot be tested through Player.");
    }

    /** Ignored test for getGame as it cannot be tested through Player */
    @Ignore
    @Test
    void testGetGame() {
        System.out.println("getGame()");
        System.out.println("Cannot be tested through Player.");
    }

    /** Test for setting high scores, which will set new scores for the player in each level
     * then be compared with arrays, to see if they match with expected
     */
    @Test
    void testSetHighscore() {
        Player player = new Player("john",0,new int[]{100,70,50},3,new Boolean[]{true,false,false});
        player.setHighscore(0,60);
        player.setHighscore(1,30);
        player.setHighscore(2,60);
        assertArrayEquals(new int[]{60,30,60},player.getHighscore());
    }

    /** Test for getting high score which should return the int array */
    @Test
    void testGetHighscore() {
        Player player = new Player("john",0,new int[]{100,70,50},3,new Boolean[]{true,false,false});
        assertArrayEquals(new int[]{100,70,50},player.getHighscore());
    }

    /** Test for getAchievement which will return the boolean array */
    @Test
    void testGetAchievement() {
        Player player = new Player("john",0,new int[]{100,70,50},3,new Boolean[]{true,false,false});
        assertArrayEquals(new Boolean[]{true,false,false}, player.getAchievement());
    }

    /** Test for setting achievements by setting each level's achievement
     * will set to true when method is used
    */
    @Test
    void testSetAchievement() {
        Player player = new Player("john",0,new int[]{100,70,50},3,new Boolean[]{true,false,false});
        player.setAchievement(0);
        player.setAchievement(1);
        player.setAchievement(2);
        assertArrayEquals(new Boolean[]{true,true,true},player.getAchievement());
    }

    /**Test for getLevelsCompleted which cannot be tested through Player as it is for GUI */
    @Ignore
    @Test
    void testGetLevelsCompleted() {
        System.out.println("getLevelsCompleted()");
        System.out.println("Cannot be tested through Player.");
    }

    /**Test for setLevelsCompleted which cannot be tested through Player as it is for GUI */
    @Ignore
    @Test
    void testSetLevelsCompleted() {
        System.out.println("setLevelsCompleted()");
        System.out.println("Cannot be tested through Player.");
    }

    /**Test for getLevel which cannot be tested through Player as it is for GUI */
    @Ignore
    @Test
    void testGetLevel() {
        System.out.println("getLevel()");
        System.out.println("Cannot be tested through Player.");
    }

    /** Test for isAdmin where the condition is true, will return true if player is admin id */
    @Test
    void testIsAdminTrue() {
        Player admin = new Player("admin@2398!",0,new int[]{100,100,100},3,new Boolean[]{true,true,true});
        assertEquals(true,admin.isAdmin());

    }
    /** Test for isAdmin where the condition is false, will return false if player is not admin */
    @Test
    void testIsAdminFalse(){
        Player player = new Player("john",0,new int[]{100,70,50},3,new Boolean[]{true,false,false});
        assertEquals(false,player.isAdmin());
    }

    /** Test for toString method which will convert the constructor of Player to String */
    @Test
    void testToString() {
        Player player = new Player("john",0,new int[]{100,70,50},3,new Boolean[]{true,false,false});
        assertEquals("john,0,100,70,50,3,true,false,false",player.toString());
    }
}