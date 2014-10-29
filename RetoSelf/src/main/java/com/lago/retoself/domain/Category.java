package com.lago.retoself.domain;

import javax.xml.bind.annotation.XmlRootElement;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("category")
@XmlRootElement
public class Category {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	public static final String TABLENAME = "category";
	
	@Id
	private String id;
	private String name;
	private String color;
	
	public Category() {
		this.color = "someColor";
		this.name = "someName";
	}
	
	public Category(String col) {
		this();
		this.color = col;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String col) {
		color = col;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
