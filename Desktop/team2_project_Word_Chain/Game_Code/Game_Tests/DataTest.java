import org.junit.jupiter.api.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/** Class DataTest to test methods in Data
 * @version 1.0
 * @author Maneet Chahal
 */
public class DataTest {

    /** BeforeAll type however not used*/
    @BeforeAll
    public static void setUpClass() {
        System.out.println("setUpClass()");
    }

    /** AfterAll type however not used*/
    @AfterAll
    public static void tearDownClass() {
        System.out.println("tearDownClass()");
    }

    /** BeforeEach type, created fake data just to test*/
    @BeforeEach
    void setUp() {
        String testFileName = "testData.csv";
        try (FileWriter fileWriter = new FileWriter(testFileName)) {
            fileWriter.write("player1,0,100,50,25,3,true,false,false\n");
            fileWriter.write("player2,0,80,0,0,1,false,false,false\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** AfterEach type however not used*/
    @AfterEach
    void tearDown() {

    }

    /** Test for getInstance will see if the two datas will match*/
    @Test
    void testGetInstance() {
        Data instance1 = Data.getInstance();
        Data instance2 = Data.getInstance();
        assertEquals(instance2,instance1);
    }

    /** Test for addPlayer will check if the player was added to datalist */
    @Test
    void testAddPlayer() {
        Data data = Data.getInstance();
        Player player = new Player("john");
        data.addPlayer(player);
        assertTrue(Data.dataList.contains(player));
    }

    /** Test for ArrayListAddPlayer will check is player was added to arrayList */
    @Test
    void testArrayListAddPlayer() {
        Data data = Data.getInstance();
        Player player = new Player("jane");
        data.arrayListAddPlayer(player);

        assertTrue(data.storePlayerData.contains(player));
    }

    /** Test to check for duplicateID, seeing if there is a duplicate in the data */
    @Test
    void testCheckDuplicateId() {
        Data data = Data.getInstance();
        Player player1 = new Player("john");
        data.addPlayer(player1);

        assertTrue(data.checkDuplicateId("john"));
        assertFalse(data.checkDuplicateId("sally"));
    }

    /** Test to check for duplicate id in the array list */
    @Test
    void testArrayListcheckDuplicateID() {
        Data data = Data.getInstance();
        Player player1 = new Player("john");
        data.arrayListAddPlayer(player1);

        assertTrue(data.arrayListcheckDuplicateID("john"));
        assertFalse(data.arrayListcheckDuplicateID("sally"));
    }

    /** Test to check if the player can be retrieved from data list */
    @Test
    void testGetPlayer() {
        Data data = Data.getInstance();
        Player player1 = new Player("john");
        data.addPlayer(player1);
        Player expected = data.getPlayer("john");

        assertSame(player1, expected);
    }

    /** arrayList get player test to see if it can be retrieved */
    @Test
    void testArrayListGetPlayer() {
        Data data = Data.getInstance();
        Player player1 = new Player("john");
        data.arrayListAddPlayer(player1);
        Player expected = data.arrayListGetPlayer("john");

        assertSame(player1, expected);
    }

    /** Test for updateData to see if the data will be in the file and they match */
    @Test
    void testUpdateData() {
        Data data = Data.getInstance();
        Player player1 = new Player("john");
        Player player2 = new Player("jane");
        data.addPlayer(player1);
        data.addPlayer(player2);

        data.updateData();
        File file = new File("CapitalGameData.csv");
        assertTrue(file.exists());
        String fileName = "CapitalGameData.csv";
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        assertEquals("john,0,0,0,0,0,null,null,null",lines.get(0));
        assertEquals("jane,0,0,0,0,0,null,null,null",lines.get(1));


    }

    /** Test to retrieveData to check if the data recieved and players created are the same */
    @Test
    void testRetrieveData() {
        Data data = Data.getInstance();
        data.retrieveData();

        Player player1 = data.getPlayer("john");
        assertEquals("john", player1.getId());
        assertEquals(0, player1.getPin());
        assertArrayEquals(new int[]{0, 0, 0}, player1.getHighscore());
        assertEquals(0, player1.getProg());
        assertArrayEquals(new Boolean[]{false, false, false}, player1.getAchievement());

        Player player2 = data.getPlayer("jane");
        assertEquals("jane", player2.getId());
        assertEquals(0, player2.getPin());
        assertArrayEquals(new int[]{0, 0, 0}, player2.getHighscore());
        assertEquals(0, player2.getProg());
        assertArrayEquals(new Boolean[]{false, false, false}, player2.getAchievement());
    }
}
