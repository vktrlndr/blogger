package se.newton.blogger.utility;

import se.newton.blogger.models.Article;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class GlobalStore {
    private static final GlobalStore globalStore = new GlobalStore();
    private List<Article> articles;
    
    private GlobalStore() {
        articles = new ArrayList<>();
    }
    
    public static GlobalStore getInstance() {
        return globalStore;
    }
    
    public List<Article> getArticles() {
        return articles;
    }
    
    public void addArticle(Article article) {
        articles.add(article);
    }
    
    public void deleteArticle(Article article) {
        articles.remove(article);
    }
    
    public void updateArticle(Article article) {
        for (Article a : articles)
            if (a.getId().equals(article.getId())) {
                a.setTitle(article.getTitle());
                a.setContent(article.getContent());
                a.setPublished(Instant.now().getEpochSecond());
                break;
            }
    }

}
