package com.deroussenicolas.entities;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

public class RegisterRequest {

	@NotNull
	@Size(min = 3, max = 70)
	private String firstname;
	@NotNull
	@Size(min = 3, max = 70)
	private String lastname;
	@NotNull
	@Email
	@Size(min = 5, max = 70)
	private String email;
	@NotNull
	@Size(min = 5, max = 255)
	private String password;
	
	public RegisterRequest() {
		super();
	}

	public RegisterRequest(@Size(min = 3, max = 70) String firstname, @Size(min = 3, max = 70) String lastname,
			@Email @Size(min = 5, max = 70) String email, @Size(min = 5, max = 255) String password) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
