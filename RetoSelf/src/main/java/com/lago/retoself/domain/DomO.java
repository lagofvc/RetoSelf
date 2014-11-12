package com.lago.retoself.domain;

import org.mongodb.morphia.annotations.Id;

/**
 * Created by lago on 11/11/14.
 */
public abstract class DomO {

    public abstract Class getClassForTableName();

    @Id
    private String id;
    private String name;

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
}
