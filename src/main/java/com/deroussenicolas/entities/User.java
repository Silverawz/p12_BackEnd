package com.deroussenicolas.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
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
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id_user;
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
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="user_has_role", joinColumns=@JoinColumn(name="user_id_user"), inverseJoinColumns=@JoinColumn(name="role_id_role"))
	private Set<Role> roles;	
	
	
	@OneToMany(mappedBy = "id_topic")
	private List<Topic> topicList;
	
	@OneToMany(mappedBy = "id_post")
	private List<Post> postList;

	@OneToMany(mappedBy = "id_article", cascade = CascadeType.ALL)
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<Topic> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<Topic> topicList) {
		this.topicList = topicList;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
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
