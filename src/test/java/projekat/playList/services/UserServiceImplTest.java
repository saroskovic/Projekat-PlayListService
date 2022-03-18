package projekat.playList.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import projekat.playList.entities.User;
import projekat.playList.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        //userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void givenUser_WhenSaveUser_ThenSave() {
        //given
        User user = new User();
        user.setName("Zoran");

        //when
        userService.saveUser(user);

        //then
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void givenUserWithoutName_WhenSaveUser_ThenThrowException() {
        //given
        User user = new User();
        user.setName(null);

        //when
        Exception e = assertThrows(NullPointerException.class,
                () -> {
                    userService.saveUser(user);
                }
        );
        //then
        assertTrue(e.getMessage().contains("Name cannot be null!"));
    }

    @Test
    void fetchingUsers() {
        //given

        //when
        userService.fetchUserList();

        //then
        verify(userRepository, times(1)).findAll();

    }

    @Test
    void givenUserId_WhenGetUserById_ReturnUser() {
        //given
        User user = new User();
        user.setId(1L);
        user.setName("Zoran");

        //when
        when(userRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(user));
        User u = userService.getUserById(1L);

        //then
        verify(userRepository, times(1)).findById(any(Long.class));
        assertAll(
                () -> assertNotNull(u),
                () -> assertEquals(user.getId(), u.getId())
        );

    }

    @Test
    void givenUserEmail_WhenGetUserByEmail_ReturnUser() {
        //given
        User user = new User();
        user.setEmail("zoran@mail.com");

        //when
        when(userRepository.findByEmail(any(String.class)))
                .thenReturn(user);
        User returned = userService.getUserByEmail("zoran@mail.com");

        //then
        verify(userRepository, times(1)).findByEmail(any(String.class));
        assertAll(
                () -> assertNotNull(returned),
                () -> assertEquals(user.getEmail(), returned.getEmail())
        );
    }


    @Test
    public void givenId_WhenUpdateUser_ShouldUpdateUser_IfFound() {
        //given
        User user = new User();
        user.setId(100L);
        user.setName("Pera");

        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setName("Mika");

        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
        //when
        userService.updateUser(newUser, user.getId());
        //then
        verify(userRepository).save(newUser);
        verify(userRepository).findById(user.getId());

    }

    @Test
    public void givenId_WhenDeleteUserById_ShouldDeleteUser(){
        //given
        User user = new User();
        user.setId(1L);
        user.setName("Zoran");
        //when
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        //then
        userService.deleteUserById(user.getId());
        verify(userRepository).delete(user);


    }
}