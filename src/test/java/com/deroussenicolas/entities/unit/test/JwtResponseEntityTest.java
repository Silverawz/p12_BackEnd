package com.deroussenicolas.entities.unit.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.deroussenicolas.entities.JwtResponse;
import com.deroussenicolas.entities.Role;
@RunWith(MockitoJUnitRunner.class)
public class JwtResponseEntityTest {

	@Test
	public void jwtResponse_constructor() {
		Set<Role> roles = new HashSet<>(); Role role = new Role();
		role.setId_role(1L); role.setDescription("ADMIN");
		roles.add(role);
		JwtResponse jwtResponse = new JwtResponse("tokenJWT--------------------------", 1L, "nicolas", "aaa@aol.fr", roles);
        assertThat(jwtResponse.getAccessToken()).isEqualTo("tokenJWT--------------------------");  
        assertThat(jwtResponse.getId_user()).isEqualTo(1L);  
        assertThat(jwtResponse.getFirstname()).isEqualTo("nicolas");  
        assertThat(jwtResponse.getEmail()).isEqualTo("aaa@aol.fr");  
        assertThat(jwtResponse.getRoles().get(0)).isEqualTo("ROLE_ADMIN");  
	}
	
	@Test
	public void jwtResponse_setters() {
		Set<Role> roles = new HashSet<>(); Role role = new Role();
		role.setId_role(1L); role.setDescription("ADMIN");
		roles.add(role);
		JwtResponse jwtResponse = new JwtResponse();
		jwtResponse.setAccessToken("tokenJWT-------------------");
		jwtResponse.setId_user(1L);
		jwtResponse.setFirstname("jacob");
		jwtResponse.setEmail("vvv@aol.fr");
		jwtResponse.setRole(roles);
	}
}
