package com.deroussenicolas.entities.unit.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.deroussenicolas.entities.RegisterRequest;
@RunWith(MockitoJUnitRunner.class)
public class RegisterRequestEntityTest {

	@Test
	public void registerRequest_constructor() {
		RegisterRequest registerRequest = new RegisterRequest("nicolas", "saloman", "aaa@aol.fr", "password");
        assertThat(registerRequest.getFirstname()).isEqualTo("nicolas");  
        assertThat(registerRequest.getLastname()).isEqualTo("saloman");  
        assertThat(registerRequest.getEmail()).isEqualTo("aaa@aol.fr");  
        assertThat(registerRequest.getPassword()).isEqualTo("password");  
	}
}
