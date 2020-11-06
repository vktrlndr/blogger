package se.newton.blogger.repositories;

import org.springframework.data.repository.CrudRepository;
import se.newton.blogger.models.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {
//    Article getByID(int id);

}
