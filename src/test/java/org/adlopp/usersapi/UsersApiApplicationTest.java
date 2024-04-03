package org.adlopp.usersapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.adlopp.usersapi.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.adlopp.usersapi.models.entities.User;

@SpringBootTest
class UsersApiApplicationTest {
	@MockBean
    private UserService userService;

    @Test
    void testFindAllUsers() {
        List<User> userList = new ArrayList<>();

        User user1 = User.builder()
            .id(1L)
            .username("Luffy")
            .email("Luffy@example.com")
            .password("12345")
            .build();
        userList.add(user1);

        User user2 = User.builder()
                .id(2L)
                .username("Willyrex")
                .email("Willyrex@example.com")
                .password("Luffy")
                .build();
        userList.add(user2);

        User updatedUser = User.builder()
                .id(1L)
                .username("newUsername")
                .password("newPassword")
                .email("newEmail")
                .build();


        when(userService.findAll()).thenReturn(userList);

        List<User> returnedUsers = userService.findAll();

        verify(userService, times(1)).findAll();

        assertEquals(2, returnedUsers.size());
        assertEquals(user1.getUsername(), returnedUsers.get(0).getUsername());
        assertEquals(user2.getUsername(), returnedUsers.get(1).getUsername());
    }

	@Test
    void testFindUserById() {
        User user = User.builder()
            .id(1L)
            .username("Luffy")
            .email("Luffy@example.com")
            .password("Luffy")
            .build();

        when(userService.findById(1L)).thenReturn(Optional.of(user));

        // Llamar al método findById del servicio con el ID del usuario ficticio
        Optional<User> returnedUserOptional = userService.findById(1L);

        // Verificar que el servicio haya sido llamado exactamente una vez con el ID correspondiente
        verify(userService, times(1)).findById(1L);

        // Verificar que el usuario devuelto no sea nulo
        assertTrue(returnedUserOptional.isPresent());

        // Obtener el usuario de la opción devuelta
        User returnedUser = returnedUserOptional.get();

		//System.out.println("Saved User: " + returnedUser.getUsername());

        // Asegurarse de que los atributos del usuario devuelto coincidan con los del usuario ficticio
        assertEquals(user.getId(), returnedUser.getId());
        assertEquals(user.getUsername(), returnedUser.getUsername());
        assertEquals(user.getEmail(), returnedUser.getEmail());
        assertEquals(user.getPassword(), returnedUser.getPassword());
    }

	@Test
    void testSaveUser() {
        User user = User.builder()
            .username("Luffy")
            .email("Luffy@example.com")
            .password("12345")
            .build();

        when(userService.save(user)).thenReturn(user);

        User savedUser = userService.save(user);

        verify(userService, times(1)).save(user);

        assertNotNull(savedUser);
        assertEquals(user, savedUser);

		//System.out.println("Saved User: " + savedUser.getUsername());
    }

	@Test
    void testUpdateUser() {
        // Crear un usuario ficticio
        User user = User.builder()
            .id(1L)
            .username("LuffyGear5th")
            .email("LuffyGear5th@example.com")
            .password("999999")
            .build();

        when(userService.update(user, 1L)).thenReturn(Optional.of(user));

        Optional<User> updatedUserOptional = userService.update(user, 1L);

        verify(userService, times(1)).update(user, 1L);

        assertTrue(updatedUserOptional.isPresent());
        assertEquals(user, updatedUserOptional.get());
    }

	@Test
    void testRemoveUser() {
        userService.remove(1L);

        verify(userService, times(1)).remove(1L);
    }

	
}
