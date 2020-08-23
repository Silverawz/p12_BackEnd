package com.deroussenicolas.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private Long id_user;
	private String firstname;
	private String email;
	private Set<Role> role;
	
	
	public JwtResponse() {
		super();
	}

	public JwtResponse(String accessToken, Long id_user, String firstname, String email, Set<Role> role) {
		this.token = accessToken;
		this.id_user = id_user;
		this.firstname = firstname;
		this.email = email;
		this.role = role;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		List<String> roleAsStringAnd_Id_Excluded = new ArrayList<>();
		for (Role role : role) {
			roleAsStringAnd_Id_Excluded.add("ROLE_"+role.getDescription());
		}
		return roleAsStringAnd_Id_Excluded;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}
	
	
}
