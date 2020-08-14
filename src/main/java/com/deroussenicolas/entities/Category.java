package com.deroussenicolas.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "Category")
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id_category;
	@NotNull
	@Size(min = 5, max = 70)
	private String description;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="category_has_topic", joinColumns=@JoinColumn(name="category_id_category"), inverseJoinColumns=@JoinColumn(name="topic_id_topic"))
	private Set<Topic> topicList;
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="category_has_article", joinColumns=@JoinColumn(name="category_id_category"), inverseJoinColumns=@JoinColumn(name="article_id_article"))
	private Set<Article> articlecList;
	
	
	public Category() {
		super();
	}

	public Long getId_category() {
		return id_category;
	}

	public void setId_category(Long id_category) {
		this.id_category = id_category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Topic> getTopicList() {
		return topicList;
	}

	public void setTopicList(Set<Topic> topicList) {
		this.topicList = topicList;
	}
	public Set<Article> getArticlecList() {
		return articlecList;
	}

	public void setArticlecList(Set<Article> articlecList) {
		this.articlecList = articlecList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
