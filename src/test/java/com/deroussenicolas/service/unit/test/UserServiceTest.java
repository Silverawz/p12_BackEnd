package com.deroussenicolas.service.unit.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.deroussenicolas.dao.UserRepository;
import com.deroussenicolas.entities.AuthRequest;
import com.deroussenicolas.entities.RegisterRequest;
import com.deroussenicolas.entities.Role;
import com.deroussenicolas.entities.User;
import com.deroussenicolas.exception.InvalidArticleException;
import com.deroussenicolas.exception.InvalidUserException;
import com.deroussenicolas.service.impl.UserServiceImplementation;
import static org.mockito.BDDMockito.given;



@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	private static UserRepository userRepository = mock(UserRepository.class);
	@InjectMocks
	private static UserServiceImplementation userServiceImplementation;
	private static User user1;
	
	@BeforeAll
	public static void initializeBeforeClass() {
		userServiceImplementation = new UserServiceImplementation(userRepository);
		when(userRepository.findOneUserById(1L)).thenReturn(user1);
	}
	
	
	
	
	@Test
	public void getAuthority_withNullUser() { 
		userServiceImplementation.findOneUserById(1L);
		
		
		
		/*
		user1 = new User(); user1.setId_user(1L); user1.setActive(true); user1.setArticleList(null); user1.setEmail("aaa@aol.fr");
		user1.setFirstname("validfirstname"); user1.setLastname("validlastname"); user1.setPassword("validpassword");
		Role role = new Role(); role.setId_role(1L); role.setDescription("ADMIN");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user1.setRoles(roles);
		userServiceImplementation.loadUserByUsername(user1.getEmail());*/
	}
	
	
	
	
	
	
	
	
	
	/**
	 * verificationAuthRequestIsValid
	 * @exception InvalidUserException
	 */
	@Test
	public void verificationAuthRequestIsValid_withAuthRequestNull() {
		InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userServiceImplementation.verificationAuthRequestIsValid(null));
        assertThat(exception.getMessage()).isEqualTo("InvalidUserException authRequest is null"); 
	}
	
	@Test
	public void verificationAuthRequestIsValid_withAuthRequestUsernameIsNull() {
		AuthRequest authRequest = new AuthRequest();
		authRequest.setUsername(null);
		InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userServiceImplementation.verificationAuthRequestIsValid(authRequest));
        assertThat(exception.getMessage()).isEqualTo("InvalidUserException Username in auth request is null"); 
	}
	
	@Test
	public void verificationAuthRequestIsValid_withAuthRequestPasswordIsNull() {
		AuthRequest authRequest = new AuthRequest();
		authRequest.setUsername("aaa@aol.fr");
		authRequest.setPassword(null);
		InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userServiceImplementation.verificationAuthRequestIsValid(authRequest));
        assertThat(exception.getMessage()).isEqualTo("InvalidUserException Password in auth request is null"); 
	}
	
	@Test
	public void verificationAuthRequestIsValid_withAuthRequestUsernameSize() {
		AuthRequest authRequest = new AuthRequest();
		authRequest.setUsername("aaaa"); //less than 5
		authRequest.setPassword("validpassword");
		InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userServiceImplementation.verificationAuthRequestIsValid(authRequest));
        assertThat(exception.getMessage()).isEqualTo("InvalidUserException Username in auth request size is incorrect"); 
        authRequest.setUsername("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); //more than 70
		InvalidUserException exception1 = assertThrows(InvalidUserException.class, () -> userServiceImplementation.verificationAuthRequestIsValid(authRequest));
        assertThat(exception1.getMessage()).isEqualTo("InvalidUserException Username in auth request size is incorrect"); 
	}
	
	@Test
	public void verificationAuthRequestIsValid_withAuthRequestPasswordSize() {
		AuthRequest authRequest = new AuthRequest();
		authRequest.setUsername("validusername"); 
		authRequest.setPassword("aaaa");//less than 5
		InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userServiceImplementation.verificationAuthRequestIsValid(authRequest));
        assertThat(exception.getMessage()).isEqualTo("InvalidUserException Password in auth request size is incorrect"); 
        authRequest.setPassword("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); //more than 255
		InvalidUserException exception1 = assertThrows(InvalidUserException.class, () -> userServiceImplementation.verificationAuthRequestIsValid(authRequest));
        assertThat(exception1.getMessage()).isEqualTo("InvalidUserException Password in auth request size is incorrect"); 
	}
	
	
	/**
	 * verificationRegisterRequestIsValid
	 * @exception InvalidUserException
	 */
	@Test
	public void verificationRegisterRequestIsValid_RegisterRequestNull() {
		InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userServiceImplementation.verificationRegisterRequestIsValid(null));
        assertThat(exception.getMessage()).isEqualTo("InvalidUserException RegisterRequest is null"); 
	}
	
	@Test
	public void verificationRegisterRequestIsValid_RegisterFirstnameNull() {
		RegisterRequest registerRequest = new RegisterRequest();
		registerRequest.setFirstname(null);
		InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userServiceImplementation.verificationRegisterRequestIsValid(registerRequest));
        assertThat(exception.getMessage()).isEqualTo("InvalidUserException Firstname in register request is null"); 
	}
	
	@Test
	public void verificationRegisterRequestIsValid_RegisterFirstnameSize() {
		RegisterRequest registerRequest = new RegisterRequest();
		registerRequest.setFirstname("aa"); //less than 3
		InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userServiceImplementation.verificationRegisterRequestIsValid(registerRequest));
        assertThat(exception.getMessage()).isEqualTo("InvalidUserException Firstname in register request size is incorrect"); 
        registerRequest.setFirstname("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); //more than 70
        InvalidUserException exception1 = assertThrows(InvalidUserException.class, () -> userServiceImplementation.verificationRegisterRequestIsValid(registerRequest));
        assertThat(exception1.getMessage()).isEqualTo("InvalidUserException Firstname in register request size is incorrect"); 
	}
	
	@Test
	public void verificationRegisterRequestIsValid_RegisterLastnameNull() {
		RegisterRequest registerRequest = new RegisterRequest();
		registerRequest.setFirstname("validfirstname");
		registerRequest.setLastname(null);
		InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userServiceImplementation.verificationRegisterRequestIsValid(registerRequest));
        assertThat(exception.getMessage()).isEqualTo("InvalidUserException Lastname in register request is null"); 
	}
	
	@Test
	public void verificationRegisterRequestIsValid_RegisterLastnameSize() {
		RegisterRequest registerRequest = new RegisterRequest();
		registerRequest.setFirstname("validfirstname");
		registerRequest.setLastname("aa"); //less than 3
		InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userServiceImplementation.verificationRegisterRequestIsValid(registerRequest));
        assertThat(exception.getMessage()).isEqualTo("InvalidUserException Lastname in register request size is incorrect"); 
        registerRequest.setLastname("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); //more than 70
        InvalidUserException exception1 = assertThrows(InvalidUserException.class, () -> userServiceImplementation.verificationRegisterRequestIsValid(registerRequest));
        assertThat(exception1.getMessage()).isEqualTo("InvalidUserException Lastname in register request size is incorrect"); 
	}
	
	@Test
	public void verificationRegisterRequestIsValid_RegisterEmailNull() {
		RegisterRequest registerRequest = new RegisterRequest();
		registerRequest.setFirstname("validfirstname");
		registerRequest.setLastname("validlastname");
		registerRequest.setEmail(null);
		InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userServiceImplementation.verificationRegisterRequestIsValid(registerRequest));
        assertThat(exception.getMessage()).isEqualTo("InvalidUserException Email in register request is null"); 
	}
	
	@Test
	public void verificationRegisterRequestIsValid_RegisterEmailSize() {
		RegisterRequest registerRequest = new RegisterRequest();
		registerRequest.setFirstname("validfirstname");
		registerRequest.setLastname("validlastname");
		registerRequest.setEmail("aaaa"); //less than 5
		InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userServiceImplementation.verificationRegisterRequestIsValid(registerRequest));
        assertThat(exception.getMessage()).isEqualTo("InvalidUserException Email in register request size is incorrect"); 
        registerRequest.setEmail("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); //more than 70
        InvalidUserException exception1 = assertThrows(InvalidUserException.class, () -> userServiceImplementation.verificationRegisterRequestIsValid(registerRequest));
        assertThat(exception1.getMessage()).isEqualTo("InvalidUserException Email in register request size is incorrect"); 
	}
	
	@Test
	public void verificationRegisterRequestIsValid_RegisterPasswordNull() {
		RegisterRequest registerRequest = new RegisterRequest();
		registerRequest.setFirstname("validfirstname");
		registerRequest.setLastname("validlastname");
		registerRequest.setEmail("aaa@aol.fr");
		registerRequest.setPassword(null);
		InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userServiceImplementation.verificationRegisterRequestIsValid(registerRequest));
        assertThat(exception.getMessage()).isEqualTo("InvalidUserException Password in register request is null"); 
	}
	
	@Test
	public void verificationRegisterRequestIsValid_RegisterPasswordSize() {
		RegisterRequest registerRequest = new RegisterRequest();
		registerRequest.setFirstname("validfirstname");
		registerRequest.setLastname("validlastname");
		registerRequest.setEmail("aaa@aol.fr");
		registerRequest.setPassword("aaaa"); //less than 5
		InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userServiceImplementation.verificationRegisterRequestIsValid(registerRequest));
        assertThat(exception.getMessage()).isEqualTo("InvalidUserException Password in register request size is incorrect"); 
        registerRequest.setPassword("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); //more than 255
        InvalidUserException exception1 = assertThrows(InvalidUserException.class, () -> userServiceImplementation.verificationRegisterRequestIsValid(registerRequest));
        assertThat(exception1.getMessage()).isEqualTo("InvalidUserException Password in register request size is incorrect"); 
	}
}
