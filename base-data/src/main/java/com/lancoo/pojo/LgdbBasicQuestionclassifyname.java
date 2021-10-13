package com.lancoo.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Lgdb_Basic_questionclassifyName")
public class LgdbBasicQuestionclassifyname {
    @Id
    @Column(name = "NewProperty")
    private String newproperty;

    @Column(name = "Name")
    private String name;

    /**
     * @return NewProperty
     */
    public String getNewproperty() {
        return newproperty;
    }

    /**
     * @param newproperty
     */
    public void setNewproperty(String newproperty) {
        this.newproperty = newproperty;
    }

    /**
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}