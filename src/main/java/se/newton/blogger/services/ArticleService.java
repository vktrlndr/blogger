package se.newton.blogger.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.newton.blogger.models.Article;
import se.newton.blogger.repositories.ArticleRepository;
import se.newton.blogger.utility.GlobalStore;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    GlobalStore storage = GlobalStore.getInstance();

    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    public Article getById(Integer id) {
        if (articleRepository.findById(id).isPresent())
            return articleRepository.findById(id).get();
        throw new NoSuchElementException();
    }

    public Article getByPublished(Long published) {
        if (articleRepository.findByPublished(published) != null)
            return articleRepository.findByPublished(published);
        throw new NoSuchElementException();
    }

    public Article save(Article newArticle) {
        storage.addArticle(newArticle);
        return articleRepository.save(newArticle);
    }

    public Article update(Article article) {
        Article oldArticle = getById(article.getId());
        if (oldArticle != null) {
            oldArticle.setPublished(Instant.now().getEpochSecond());
            oldArticle.setTitle(article.getTitle());
            oldArticle.setSubject(article.getSubject());
            oldArticle.setContent(article.getContent());
            storage.updateArticle(oldArticle);
            return articleRepository.save(oldArticle);
        }
        throw new NoSuchElementException();
    }

    public void delete(Integer id) {
        if (getById(id) != null) {
            storage.deleteArticle(getById(id));
            articleRepository.delete(getById(id));
        }
        else
            throw new NoSuchElementException();
    }

}
