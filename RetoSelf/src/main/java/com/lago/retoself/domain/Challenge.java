package com.lago.retoself.domain;

import org.mongodb.morphia.annotations.Entity;

import javax.xml.bind.annotation.XmlRootElement;

@Entity("reto")
@XmlRootElement
public class Challenge extends DomO{

    public enum ChallengeType {
        SIMPLE_DO,
        DO_X_BY,
        STOP_HABIT,
        START_HABIT,
        DO_X_BY_LAPSE
    }//end enum

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	public static final String TABLENAME = "reto";

    private String categoryId;
    private String description;
    private Boolean completed = false;
	private ChallengeType type;
	
	public Challenge(){
        super.setName("some challenge name");
		setType(ChallengeType.SIMPLE_DO);
	}

    //getters and setters
	public ChallengeType getType() {
		return type;
	}

	public void setType(ChallengeType type) {
		this.type = type;
	}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryid) {
        this.categoryId = categoryid;
    }

    @Override
    public Class getClassForTableName() {
        return Challenge.class;
    }
}
