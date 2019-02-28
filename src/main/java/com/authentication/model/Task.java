package com.authentication.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;


@Entity
@Table(name = "notes")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //    @Column(name = "author")
//    private String author;
    @Column(name = "title")
    private String title;
    @Column(name = "body")
    private String body;

    @Column(name = "author")
    private String author;

    @JsonCreator
    public Task(
            @JsonProperty("author") String author,
            @JsonProperty("title") String title,
            @JsonProperty("body") String body
    ) {
        this.author = author;
        this.title = title;
        this.body = body;
    }

    public Task() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
