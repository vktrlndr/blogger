package se.newton.blogger.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.newton.blogger.models.Article;
import se.newton.blogger.repositories.ArticleRepository;
import se.newton.blogger.services.ArticleService;
import se.newton.blogger.utility.GlobalStore;

import java.util.List;


@Controller
public class PageController {

    @Autowired
    ArticleRepository ar;
    @Autowired
    ArticleService aserv;
//    @Autowired
//    SortingService sortingService;

    GlobalStore storage = GlobalStore.getInstance();

    @RequestMapping(value = "/")
    public String index(Model model) {

        List<Article> articles = aserv.getAll();
        model.addAttribute("allarticles", articles);
        return "index";
    }

//    @RequestMapping("/all")
//    public String all(Model model) {
//        List<Article> articles = aserv.getAll();
//        model.addAttribute("allarticles", articles);
//        return "articles";
//    }

    @PostMapping("/articles/create")
    public String createArticle(@ModelAttribute Article article) {
        if (!article.getTitle().isBlank() && !article.getContent().isBlank()) {
            aserv.save(article);
        }
        return "index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("article", new Article());
        return "new";
    }

    @PostMapping("/articles/update/{id}")
    public String updateArticle(@PathVariable("id") Integer id, Article article, Model model) {
        aserv.update(article);
        return "index";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("article", aserv.getById(id));
        return "update";
    }

    @GetMapping("/articles/delete/{id}")
    public String deleteArticle(@PathVariable("id") Integer id) {
        aserv.delete(id);
        return "index";
    }
}