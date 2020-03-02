package org.wcci.blog.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Tag {

    private String tag;
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany
    private Collection<Post> posts;

    public Tag(){

    }

    public Tag(String tag){
        this.tag = tag;

    }

    public String getTag(){
        return tag;
    }

    public Long getId(){
        return id;
    }

    public Collection<Post> getPosts(){
        return posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag tag1 = (Tag) o;
        return Objects.equals(tag, tag1.tag) &&
                Objects.equals(id, tag1.id) &&
                Objects.equals(name, tag1.name) &&
                Objects.equals(posts, tag1.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag, id, name, posts);
    }
}
