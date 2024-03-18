package com.adrian.backend.usersapp.backendusersapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.adrian.backend.usersapp.backendusersapp.services.UserService;
import com.adrian.backend.usersapp.backendusersapp.models.entities.User;

@SpringBootTest
class BackendUsersappApplicationTests {

	//@Autowired 
    //private MockMvc mockMvc; //PARA LAS SOLICITUDES

	@MockBean
    private UserService userService;

    @Test
    void testFindAllUsers() {
        List<User> userList = new ArrayList<>();

        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("Luffy");
        user1.setEmail("Luffy@example.com");
		user1.setPassword("12345");
        userList.add(user1);

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("Willyrex");
        user2.setEmail("Willyrex@example.com");
		user2.setPassword("Luffy");
        userList.add(user2);

        when(userService.findAll()).thenReturn(userList);

        List<User> returnedUsers = userService.findAll();

        verify(userService, times(1)).findAll();

        assertEquals(2, returnedUsers.size());
        assertEquals(user1.getUsername(), returnedUsers.get(0).getUsername());
        assertEquals(user2.getUsername(), returnedUsers.get(1).getUsername());
    }

	@Test
    void testFindUserById() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Luffy");
        user.setEmail("Luffy@example.com");
        user.setPassword("Luffy");

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
        User userToSave = new User();
        userToSave.setUsername("Luffy");
        userToSave.setEmail("Luffy@example.com");
        userToSave.setPassword("12345");

        when(userService.save(userToSave)).thenReturn(userToSave);

        User savedUser = userService.save(userToSave);

        verify(userService, times(1)).save(userToSave);

        assertNotNull(savedUser);
        assertEquals(userToSave, savedUser);

		//System.out.println("Saved User: " + savedUser.getUsername());
    }

	@Test
    void testUpdateUser() {
        // Crear un usuario ficticio
        User userToUpdate = new User();
        userToUpdate.setId(1L);
        userToUpdate.setUsername("LuffyGear5th");
        userToUpdate.setEmail("LuffyGear5th@example.com");
        userToUpdate.setPassword("999999");

        when(userService.update(userToUpdate, 1L)).thenReturn(Optional.of(userToUpdate));

        Optional<User> updatedUserOptional = userService.update(userToUpdate, 1L);

        verify(userService, times(1)).update(userToUpdate, 1L);

        assertTrue(updatedUserOptional.isPresent());
        assertEquals(userToUpdate, updatedUserOptional.get());
    }

	@Test
    void testRemoveUser() {
        userService.remove(1L);

        verify(userService, times(1)).remove(1L);
    }

	
}
