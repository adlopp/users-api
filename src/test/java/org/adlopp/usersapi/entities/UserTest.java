package org.adlopp.usersapi.entities;

import org.adlopp.usersapi.models.entities.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class UserTest {

    @Test
    public void testDefaultConstructor() {
        User user = new User();

        assertNotNull(user);
        assertNull(user.getId());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getEmail());
    }

    @Test
    public void testParameterizedConstructor() {
        Long id = 1L;
        String username = "user1";
        String password = "password1";
        String email = "mail1";

        User user = new User(id, username, password, email);

        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(email, user.getEmail());
    }
}
