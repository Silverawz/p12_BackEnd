package com.deroussenicolas.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
