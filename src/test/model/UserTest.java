package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests for User class
public class UserTest {
    private User testUser;

    @BeforeEach
    public void setUp(){
        testUser = new User("name", "pass");
    }

    @Test
    public void testConstructor() {
        assertEquals("name", testUser.getUsername());
        assertEquals("pass", testUser.getPassword());

    }

    @Test
    public void setUsername() {
        assertEquals("name", testUser.getUsername());
        testUser.setUsername("user1");
        assertEquals("user1", testUser.getUsername());
    }

    @Test
    public void setPassword() {
        assertEquals("pass", testUser.getPassword());
        testUser.setPassword("security");
        assertEquals("security", testUser.getPassword());
    }


}
