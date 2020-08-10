package com.deroussenicolas.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "Category")
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id_category;
	@NotNull
	@Size(min = 5, max = 70)
	private String description;
	@OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
	private List<Topic> topicList;
	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
	private List<Article> articlecList;
	
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

	public List<Topic> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<Topic> topicList) {
		this.topicList = topicList;
	}

	public List<Article> getArticlecList() {
		return articlecList;
	}

	public void setArticlecList(List<Article> articlecList) {
		this.articlecList = articlecList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
