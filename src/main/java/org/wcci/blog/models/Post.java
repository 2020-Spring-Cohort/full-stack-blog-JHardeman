package org.wcci.blog.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;


@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String body;
    @ManyToOne
    private Author author;
    private String date;
    @ManyToOne
    private Category category;
    @ManyToMany(mappedBy = "posts")
    private Collection<Tag> tags;


    public Post(){

    }

    public Post(String title, String body, Author author, Category category, Tag... tags){
            this.title = title;
            this.body = body;
            this.author = author;
//            this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM dd yyyy hh:mm a"));
            this.category = category;
            this.tags = new ArrayList<>(Arrays.asList(tags));
    }

    public String getTitle(){
        return title;
    }

    public String getBody(){
        return body;
    }

    public Author getAuthor(){
        return author;
    }

    public String getDate(){
        return date;
    }

    public Category getCategory(){
        return category;
    }

    public Collection<Tag> getTags(){
        return tags;
    }

    public Long getId(){
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(title, post.title) &&
                Objects.equals(body, post.body) &&
                Objects.equals(author, post.author) &&
                Objects.equals(date, post.date) &&
                Objects.equals(category, post.category) &&
                Objects.equals(tags, post.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, body, author, date, category, tags);
    }


}
