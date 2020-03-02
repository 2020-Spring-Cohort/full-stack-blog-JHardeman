package org.wcci.blog.models;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Author {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "author")
    private Collection<Post> posts;

    public Author(){

    }

    public Author(String authorName){
        this.name = authorName;
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
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) &&
                Objects.equals(name, author.name) &&
                Objects.equals(posts, author.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, posts);
    }
}
