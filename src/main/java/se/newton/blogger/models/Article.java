package se.newton.blogger.models;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private Long published;

    public Article() {
    }

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
        this.published = Instant.now().getEpochSecond();
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() { return content; }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPublished() { return published; }

    public void setPublished(Long published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", published=" + published +
                '}';
    }
}
