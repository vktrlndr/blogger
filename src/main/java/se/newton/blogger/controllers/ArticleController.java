package se.newton.blogger.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.newton.blogger.models.Article;
import se.newton.blogger.repositories.ArticleRepository;
import se.newton.blogger.services.ArticleService;

import java.util.List;
import java.util.Objects;

@RestController
public class ArticleController {
    @Autowired
            ArticleRepository ar;
    ArticleService aserv;

    @GetMapping("/getbyid/{id}")
    public Article getById(@PathVariable Integer id) {
        return aserv.getById(id);
    }

    @GetMapping("/allarticles")
    public Iterable<Article> getArticles() {
        return aserv.getAll();
    }

    @PostMapping("/addarticle")
    public Article addArticle(@ModelAttribute Article newArticle) {
        return aserv.save(new Article(newArticle.getTitle(), newArticle.getContent(), newArticle.getSubject()));
    }

    @PutMapping("/update/{id}")
    public Article updateArticle(
            @PathVariable int id, @RequestBody Article newArticle) {
        if (Objects.nonNull(ar.getById(id))) {
            return ar.save(new Article(id, newArticle.getTitle(), newArticle.getContent(), newArticle.getSubject()));
        } else return new Article();
        // How can this be made to return something more distinct to warn about the article not being there?
        // Should it have a separate check outside of this method?
    }

    @DeleteMapping("/deletearticle/{id}")
    public String deleteArticle(@PathVariable Integer id) {
        Article deleted = getById(id);
//        String response = "Deleted article " + deleted.getId() + ", " + "\"" + deleted.getTitle() + "\"";
        ar.delete(deleted);
        aserv.delete(id);
        return "index";
    }
}