package com.lago.retoself.domain;

import javax.xml.bind.annotation.XmlRootElement;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("reto")
@XmlRootElement
public class Reto {

    public enum RetoType {
        SIMPLE_DO,
        DO_X_BY,
        STOP_HABIT,
        START_HABIT,
        DO_X_BY_LAPSE
    }//end enum

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	public static final String TABLENAME = "reto";
	
	@Id
	private String id;
    private String name;
    private String description;
    private Boolean hasBeenMet;
	private RetoType type;
	
	public Reto(){
		setType(RetoType.SIMPLE_DO);
        name = "some name";
        hasBeenMet = false;
	}

    //getters and setters
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getHasBeenMet() {
        return hasBeenMet;
    }

    public void setHasBeenMet(Boolean hasBeenMet) {
        this.hasBeenMet = hasBeenMet;
    }
}
