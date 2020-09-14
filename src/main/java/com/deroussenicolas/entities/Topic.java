package com.deroussenicolas.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "Topic")
public class Topic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id_topic;
	@NotNull
	@Size(min = 5, max = 70)
	private String title;
	@ManyToOne
	private User user;
	@NotNull
	private boolean active;
	@OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
	private List<Post> postList;
	@ManyToMany(cascade= CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name="category_has_topic", joinColumns=@JoinColumn(name="topic_id_topic"), inverseJoinColumns=@JoinColumn(name="category_id_category"))
	private List<Category> categories;
	
	public Topic() {
		super();
	}

	public Long getId_topic() {
		return id_topic;
	}

	public void setId_topic(Long id_topic) {
		this.id_topic = id_topic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
