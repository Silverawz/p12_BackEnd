package com.deroussenicolas.entities;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "Article")
public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id_article;
	@NotNull
	@Size(min = 5, max = 70)
	private String title;
	@NotNull
	@Size(min = 5, max = 1000)
	private String message;
	@NotNull
	private Date date;
	@NotNull
	private boolean active;
	@ManyToMany(cascade= CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name="category_has_article", joinColumns=@JoinColumn(name="article_id_article"), inverseJoinColumns=@JoinColumn(name="category_id_category"))
	private List<Category> categories;
	@ManyToOne
	private User user;
	
	
	
	public Article() {
		super();
	}

	public Long getId_article() {
		return id_article;
	}

	public void setId_article(Long id_article) {
		this.id_article = id_article;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Article [id_article=" + id_article + ", title=" + title + ", message=" + message + ", date=" + date
				+ ", active=" + active + ", user=" + user.getEmail() + "]";
	}

}
