package se.newton.blogger.models;

import net.minidev.json.JSONUtil;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.Instant;

@Entity
@Table(name = "articles")
public class Article implements Comparable<Article> {

    //Columns for the database. @Lob to allow for longer texts as contents.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String title;
    @Lob
    @Column(columnDefinition = "text")
    private String content;
    @Column
    private Long published;
    @Column
    private String subject;

    public Article() { this.published = Instant.now().getEpochSecond(); }

    public Article(String title, String content, String subject) {
        this.title = title;
        this.content = content;
        this.subject = subject;
        this.published = Instant.now().getEpochSecond();
    }

    public String getSubject() { return subject; }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Article(Integer id, String title, String content, String subject) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.subject = subject;
        this.published = Instant.now().getEpochSecond();
    }

    public Integer getId() { return id; }

    public void setId(Integer id) {
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

    @Override
    public int compareTo(Article a) {
        return Long.compare(this.published, a.getPublished());
    }
}
