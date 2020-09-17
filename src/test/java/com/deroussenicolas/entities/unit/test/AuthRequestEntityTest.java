package com.deroussenicolas.entities.unit.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.deroussenicolas.entities.AuthRequest;
@RunWith(MockitoJUnitRunner.class)
public class AuthRequestEntityTest {

	@Test
	public void authRequest_constructor() {
		AuthRequest authRequest = new AuthRequest("aaa@aol.fr", "passwordvalid");
        assertThat(authRequest.getUsername()).isEqualTo("aaa@aol.fr");  
        assertThat(authRequest.getPassword()).isEqualTo("passwordvalid");  
	}
}
