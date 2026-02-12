package ricksciascia.u5d9.entities;

import jakarta.persistence.*;
import lombok.Generated;

@Entity
@Table(name = "blog_posts")
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String category;
    private String title;
    private String cover;
    private String content;
    private int tempoDiLettura;
    @ManyToOne
    @JoinColumn(name = "author_id",nullable = false)
    private Author author;

    public BlogPost(String category, String title, String content, int tempoDiLettura, Author autore) {
        this.category = category;
        this.title = title;
        this.cover = "https://picsum.photos/200/300";
        this.content = content;
        this.tempoDiLettura = tempoDiLettura;
        this.author = autore;
    }

    public String toString() {
        return "BlogPost{id=" + this.id + ", category='" + this.category + "', title='" + this.title + "', cover='" + this.cover + "', content='" + this.content + "', tempoDiLettura=" + this.tempoDiLettura + "}";
    }

    @Generated
    public BlogPost() {
    }

    @Generated
    public long getId() {
        return this.id;
    }

    @Generated
    public String getCategory() {
        return this.category;
    }

    @Generated
    public String getTitle() {
        return this.title;
    }

    @Generated
    public String getCover() {
        return this.cover;
    }

    @Generated
    public String getContent() {
        return this.content;
    }

    @Generated
    public int getTempoDiLettura() {
        return this.tempoDiLettura;
    }

    @Generated
    public void setCategory(final String category) {
        this.category = category;
    }

    @Generated
    public void setTitle(final String title) {
        this.title = title;
    }

    @Generated
    public void setCover(final String cover) {
        this.cover = cover;
    }

    @Generated
    public void setContent(final String content) {
        this.content = content;
    }

//    public void setAuthor(final Author author) {
//        this.author = author;
//    }

    public Author getAuthor() {
        return this.author;
    }

    @Generated
    public void setTempoDiLettura(final int tempoDiLettura) {
        this.tempoDiLettura = tempoDiLettura;
    }
}