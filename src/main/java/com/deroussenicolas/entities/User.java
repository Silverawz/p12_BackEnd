package com.deroussenicolas.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

/**
 * implements Serializable
 * 
 * @author deroussen nicolas
 *
 */
@Entity
@Table(name="User")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id_user;
	@NotNull
	@Size(min = 3, max = 70)
	private String firstname;
	@NotNull
	@Size(min = 3, max = 70)
	private String lastname;
	@NotNull
	@Size(min = 5, max = 70)
	private String email;
	@NotNull
	@Size(min = 5, max = 255)
	private String password;
	@OneToOne
	private UserRole UserRole;
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Post> postList;
	@OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
	private List<Topic> topicList;
	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
	private List<Article> articleList;
	
	public User() {
		
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

	public UserRole getUserRole() {
		return UserRole;
	}

	public void setUserRole(UserRole userRole) {
		UserRole = userRole;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public List<Topic> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<Topic> topicList) {
		this.topicList = topicList;
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
