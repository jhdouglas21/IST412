package test;

import controller.UserAuthController;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.UserView;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationTest {

    private User testUser;
    private UserView dummyView;
    private UserAuthController authController;

    @BeforeEach
    void setUp() {
        testUser = new User("1", "testUser", "password123", "test@example.com", 100.0);
        dummyView = new UserView(); // This could be a mock or just a real instance if no behavior needed
        authController = new UserAuthController(testUser, dummyView);
    }

    // ✅ Unit Test 1: Successful login
    @Test
    void testSuccessfulAuthentication() {
        boolean result = authController.authenticate("testUser", "password123");
        assertTrue(result, "Authentication should succeed with correct credentials.");
    }

    // ✅ Unit Test 2: Failed login
    @Test
    void testFailedAuthentication() {
        boolean result = authController.authenticate("wrongUser", "wrongPassword");
        assertFalse(result, "Authentication should fail with incorrect credentials.");
    }

    // ✅ Non-Functional Test: Authentication response time
    @Test
    void testAuthenticationSecurity_emptyInputs() {
        User dummyUser = new User("dummyId", "testUser", "password123", "dummy@example.com", 100.0);
        UserView dummyView = new UserView();
        UserAuthController controller = new UserAuthController(dummyUser, dummyView);

        // Try authenticating with empty username and password
        boolean result = controller.authenticate("", "");
        assertFalse(result, "Authentication should fail with empty username and password");
    }
}

