package se.newton.blogger.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.newton.blogger.models.Article;
import se.newton.blogger.repositories.ArticleRepository;
//import se.newton.blogger.repositories.ArticleRepositoryImpl;

import java.util.List;

@RestController
public class ArticleController
{
    @Autowired
    ArticleRepository ar;
    
    public ArticleController() {}

//    @GetMapping("/getbyid")
//    public Article getById(@RequestParam int id)
//    {
//        return ar.getByID(id);
//    }


    @GetMapping("/allarticles")
    public Iterable<Article> getArticles()
    {
        return ar.findAll();
    }

//    @PostMapping("/addarticle")
//    public Article addArticle(@RequestBody Article newArticle)
//    {
//        return ar.add(newArticle);
//    }

    //TODO: set defaults for values that can't change through this method.
//    @PutMapping("/updatecustomer")
//    public Article updateCustomer(
//            @RequestParam Long id, @RequestBody Article newArticle)
//    {
//        return ar.updateArticle(id, newArticle);
//    }

//    @DeleteMapping("/deletearticle")
//    public void deleteArticle(@RequestParam Long id)
//    {
//        return ar.deleteById(id);
//    }
}