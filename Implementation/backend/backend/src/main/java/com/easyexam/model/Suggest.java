package com.easyexam.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="suggest")
public class Suggest {

    @EmbeddedId
    private SuggestId suggestId;

    @Column(name="description")
    private String description;

    public Suggest(SuggestId suggestId, String description) {
        this.suggestId = suggestId;
        this.description = description;
    }

    public SuggestId getSuggestId() {
        return suggestId;
    }

    public void setSuggestId(SuggestId suggestId) {
        this.suggestId = suggestId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
