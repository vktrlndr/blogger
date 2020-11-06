package se.newton.blogger.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.newton.blogger.models.Article;
import se.newton.blogger.repositories.ArticleRepository;

import java.util.List;
import java.util.Objects;

@RestController
public class ArticleController {
    @Autowired
    ArticleRepository ar;

    @GetMapping("/getbyid/{id}")
    public Article getById(@PathVariable int id) {

        return ar.getById(id);
    }

    @GetMapping("/allarticles")
    public Iterable<Article> getArticles() {
        return ar.findAll();
    }

    @PostMapping("/addarticle")
    public Article addArticle(@RequestBody Article newArticle) {
        return ar.save(new Article(newArticle.getTitle(), newArticle.getContent()));
    }

    @PutMapping("/update/{id}")
    public Article updateArticle(
            @PathVariable int id, @RequestBody Article newArticle) {
        if (Objects.nonNull(ar.getById(id))) {
            return ar.save(new Article(id, newArticle.getTitle(), newArticle.getContent()));
        } else return new Article();
        // How can this be made to return something more distinct to warn about the article not being there?
        // Should it have a separate check outside of this method?
    }

    @DeleteMapping("/deletearticle/{id}")
    public String deleteArticle(@PathVariable int id) {
        Article deleted = getById(id);
        String response = "Deleted article " + deleted.getId() + ", " + "\"" + deleted.getTitle() + "\"";
        ar.deleteById(id);
        return response;
    }
}