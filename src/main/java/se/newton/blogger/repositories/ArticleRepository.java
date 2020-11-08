package se.newton.blogger.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import se.newton.blogger.models.Article;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Article getById(int id);
    Article findByPublished(Long published);
}
