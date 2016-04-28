package com.example.mohamed.malphase1v1.HelperClassesReviews;

/**
 * Created by Mohamed on 4/23/2016.
 */
public class ReviewInfo {
    String author,content,id;

    public ReviewInfo(String author, String content, String id) {
        this.author = author;
        this.content = content;
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
