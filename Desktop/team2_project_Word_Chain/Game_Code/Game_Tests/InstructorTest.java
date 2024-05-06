/**
 * Provides unit tests for the {@link Instructor} class to ensure the correctness of its functionalities
 * It covers tests for instructor validation, PIN setting and retrieval, login and logout processes,
 * and handling of non-existing player data
 * <p>
 * The setUp method initializes necessary components before each test, and the tearDown method
 * resets the environment after each test to ensure test isolation.
 * </p>
 * @author Simran Kullar
 * @version 3.0
 */

// import Java I/O and Junit library
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class InstructorTest {
	/**
	 * Create instructor object
	 */
    private Instructor instructor;
    /**
     * Reference to the original standard output stream
     */
    private final PrintStream standardOut = System.out;
    /**
     * ByteArrayOutputStream used to capture output data
     */
    private ByteArrayOutputStream outContent;

    /**
     * Sets up the testing environment before each test. Initializes the instructor object
     * with a fixed PIN, captures the System.out output for validation, and prepares other necessary resources.
     */
    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        instructor = new Instructor(Instructor.getFIXED_PIN());
    }

    /**
     * Cleans up the testing environment after each test. Restores the System.out output to its original state.
     */
    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    /**
     * Tests that the isInstructor method returns true when provided with the correct PIN.
     */
    @Test
    void testIsInstructorTrue() {
        assertTrue(instructor.isInstructor(Instructor.getFIXED_PIN()), "isInstructor should return true for the correct PIN");
    }

    /**
     * Tests that the isInstructor method returns false when provided with an incorrect PIN.
     */
    @Test
    void testIsInstructorFalse() {
        assertFalse(instructor.isInstructor(1234), "isInstructor should return false for an incorrect PIN");
    }

    /**
     * Tests the functionality of setting and then getting an instructor's PIN.
     */
    @Test
    void testSetAndGetPin() {
        instructor.setPin(5678);
        assertEquals("5678", instructor.getPin(), "getPin should return the PIN that was set with setPin");
    }

    /**
     * Tests the login process to ensure it succeeds with the correct PIN.
     */
    @Test
    void testLoginSuccess() {
        instructor.setPin(4351);
        assertTrue(instructor.login(), "Login should succeed with the correct PIN");
    }

    /**
     * Tests the login process to ensure it fails with an incorrect PIN.
     */
    @Test
    void testLoginFailure() {
        instructor.setPin(Instructor.getFIXED_PIN() + 1);
        assertFalse(instructor.login(), "Login should fail with an incorrect PIN");
    }

    /**
     * Tests the logout process to ensure it resets the instructor's PIN to 0.
     */
    @Test
    void testLogoutSuccess() {
        instructor.setPin(4351);
        instructor.login();
        instructor.logout();
        assertEquals("0", instructor.getPin(), "Logging out should reset the pin to 0");
    }

    /**
     * Tests the logout process to ensure it does not incorrectly reset the instructor's PIN to a non-zero value.
     */
    @Test
    void testLogoutFailure() {
        instructor.setPin(4351);
        instructor.login();
        instructor.logout();
        assertNotEquals("1", instructor.getPin(), "Logout should not reset the pin to 1");
    }

    /**
     * Tests the validation process for an instructor with the correct PIN.
     */
    @Test
    void testValidateInstructorValid() {
        instructor.setPin(4351);
        assertTrue(instructor.validateInstructor(), "Instructor should be valid with correct PIN");
    }

    /**
     * Tests the validation process for an instructor with an incorrect PIN.
     */
    @Test
    void testValidateInstructorInvalid() {
        instructor.setPin(4352);
        assertFalse(instructor.validateInstructor(), "Instructor should be invalid with incorrect PIN");
    }

    /**
     * Tests the viewing of player data for a non-existing player identifier to ensure it generates the correct message.
     */
    @Test
    void testViewPlayerDataForNonExistingPlayer() {
        instructor.viewPlayerData("nonExistingPlayerIdentifier");
        String output = outContent.toString().trim();
        assertTrue(output.contains("Player data not found for the specified identifier"), "Output should indicate that player data was not found");
    }
}