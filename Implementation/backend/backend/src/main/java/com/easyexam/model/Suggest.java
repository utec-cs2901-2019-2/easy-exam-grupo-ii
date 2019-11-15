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

    @Column(name="is_reported")
    private Boolean isReported;

    public Suggest() { }
    public Suggest(SuggestId suggestId, String description, Boolean isReported) {
        this.suggestId = suggestId;
        this.description = description;
        this.isReported = isReported;
    }

    public Boolean getReported() {
        return isReported;
    }

    public void setReported(Boolean reported) {
        isReported = reported;
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
