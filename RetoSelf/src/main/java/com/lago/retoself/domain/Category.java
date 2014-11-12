package com.lago.retoself.domain;

import org.mongodb.morphia.annotations.Entity;

import javax.xml.bind.annotation.XmlRootElement;

@Entity("category")
@XmlRootElement
public class Category extends DomO {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	public static final String TABLENAME = "category";

	private String color;
	
	public Category() {
        super.setName("someName");
		this.color = "someColor";
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

    @Override
    public Class getClassForTableName() {
        return Category.class;
    }
}
