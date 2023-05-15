package mirea.morning.eventagencypr;

import mirea.morning.eventagencypr.model.User;
import mirea.morning.eventagencypr.repository.RoleRepository;
import mirea.morning.eventagencypr.repository.UserRepository;
import mirea.morning.eventagencypr.service.Impl.UserServiceImpl;
import mirea.morning.eventagencypr.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;

    private UserService underTest;

    @BeforeEach
    public void setUp() {
        underTest = new UserServiceImpl(userRepository, roleRepository, new BCryptPasswordEncoder());
    }

    @Test
    public void getUsers() {
        // given
        User user = new User();
        user.setUsername("user");
        user.setId(11L);

        // when
        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findById(user.getId() + 1)).thenReturn(Optional.empty());

        // then
        Assertions.assertEquals(user.getUsername(), underTest.findByUsername(user.getUsername()).getUsername());
        Assertions.assertEquals(user.getId(), underTest.findById(user.getId()).getId());
        assertThat(underTest.findById(12L)).isNull();
        //Assertions.assertEquals(user.getUsername(), underTest.findByUsername(user.getUsername()).getUsername());
    }

//    @Test
//        //Todo: переписать
//    void loadUserByUsername() {
//        // given
//        User user = new User();
//        user.setUserName("user");
//        user.setPassword("pswd");
//
//        UserApp userApp = new UserApp(user);
//        //underTest.signUpUser(user);
//
//        Mockito.when(userRepository.findUserByUserName(userApp.getUsername())).thenReturn(user);
//
//        //when
//        User expected = userRepository.findUserByUserName(userApp.getUsername());
//
//        //then
//        Mockito.verify(userRepository).findUserByUserName(userApp.getUsername());
//        assertThat(expected).isEqualTo(user);
//        /*Mockito.doReturn(expected)
//                .when(userRepository)
//                .findUserByUserName(userApp.getUsername());*/
//    }

}
