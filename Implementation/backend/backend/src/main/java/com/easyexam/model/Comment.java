package com.easyexam.model;

import javax.persistence.*;

@Entity
@Table(name="comment")
@SequenceGenerator(sequenceName = "seqComment",name = "seq_com")
public class Comment {

    @EmbeddedId
    private CommentId commentId;

    @Column(name="description")
    private String description;


    public Comment() { }

    public Comment(CommentId commentId, String description) {
        this.commentId = commentId;
        this.description = description;
    }

    public CommentId getCommentId() {
        return commentId;
    }

    public void setCommentId(CommentId commentId) {
        this.commentId = commentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
