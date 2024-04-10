package org.adlopp.usersapi.services;

import org.adlopp.usersapi.models.entities.User;
import org.adlopp.usersapi.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository repository;

    private final User testUser1 = User.builder()
            .id(1L)
            .username("user1")
            .password("password1")
            .email("mail1")
            .build();
    private final User testUser2 = User.builder()
            .id(2L)
            .username("user2")
            .password("password2")
            .email("mail2")
            .build();

    private final List<User> expectedUsers = List.of(testUser1, testUser2);

    @Test
    public void whenFindAll_thenUsersAreReturned() {
        // Assign
        Mockito.when(repository.findAll()).thenReturn(expectedUsers);

        // Act
        List<User> users = userService.findAll();

        // Assert
        assertEquals(expectedUsers, users);
        Mockito.verify(repository).findAll();

    }

    @Test
    public void givenValidUserId_whenFindById_thenUserIsReturned() {
        // Assign
        Long userId = 1L;
        Mockito.when(repository.findById(userId)).thenReturn(Optional.of(testUser1));

        // Act
        Optional<User> userOptional = userService.findById(userId);

        // Assert
        assertTrue(userOptional.isPresent());
        assertEquals(testUser1, userOptional.get());
        Mockito.verify(repository).findById(userId);
    }


    @Test
    public void givenAUser_whenSave_thenTheUserIsReturned() {
        // Assign
        Mockito.when(repository.save(testUser1)).thenReturn(testUser1);

        // Act
        User user = userService.save(testUser1);

        // Assert
        assertEquals(testUser1, user);
        Mockito.verify(repository).save(testUser1);
    }

    @Test
    public void givenValidUserAndId_whenUpdate_thenUserIsUpdated() {
        // Asignar
        Long userId = 1L;
        User updatedUser = User.builder()
                .id(userId)
                .username("updatedUsername")
                .password("updatedPassword")
                .email("updatedEmail")
                .build();
        Optional<User> existingUserOptional = Optional.of(testUser1);
        Mockito.when(userService.findById(userId)).thenReturn(existingUserOptional);
        Mockito.when(repository.save(Mockito.any(User.class))).thenReturn(updatedUser);

        // Act
        Optional<User> updatedUserOptional = userService.update(updatedUser, userId);
        updatedUser.setUsername("updatedUsername");
        updatedUser.setEmail("updatedEmail");

        // Assert
        assertTrue(updatedUserOptional.isPresent());
        assertEquals(updatedUser, updatedUserOptional.get());
    }


    @Test
    public void givenAValidUserId_whenRemove_thenUserIsDeleted() {
        // Assign
        Long id = 1L;

        // Act
        userService.remove(id);

        // Assert
        Mockito.verify(repository).deleteById(id);
    }

}
