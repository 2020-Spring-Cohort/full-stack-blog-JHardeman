package org.wcci.blog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category")
    private Collection<Post> posts;

    public Category(String name){
        this.name = name;
    }

    public Category(){

    }

    public String getName(){
        return name;
    }

    public Long getId(){
        return id;
    }

    public Collection<Post> getPosts(){
        return posts;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(name, category.name) &&
                Objects.equals(posts, category.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, posts);
    }
}
