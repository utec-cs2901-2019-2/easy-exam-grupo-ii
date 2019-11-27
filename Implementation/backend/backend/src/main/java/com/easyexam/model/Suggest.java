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

    @Column(name="isReported")
    private boolean isReported;

    public Suggest() {}

    public Suggest(SuggestId suggestId, String description, boolean isReported) {
        this.suggestId = suggestId;
        this.description = description;
        this.isReported = false;
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

    public boolean getIsReported(){
        return isReported;
    }

    public void setIsReported(boolean isReported){
        this.isReported = isReported;
    }
}
