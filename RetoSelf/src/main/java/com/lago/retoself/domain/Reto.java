package com.lago.retoself.domain;

import javax.xml.bind.annotation.XmlRootElement;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("reto")
@XmlRootElement
public class Reto {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	public static final String TABLENAME = "reto";
	
	@Id
	private String id;
	private RetoType type;
	public enum RetoType {
		DO_X_BY, 
		STOP_HABIT, 
		START_HABIT, 
		DO_X_BY_LAPSE 
	}//end enum
	
	public Reto(){
		type = Reto.RetoType.DO_X_BY;
	}
	
	public RetoType getType() {
		return type;
	}

	public void setType(RetoType type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
