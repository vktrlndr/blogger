package se.newton.blogger;

 /*
 Class to create DB using Hibernate and populate it with 5 dummy persons.
 Logging with SLF4J
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import se.newton.blogger.models.Article;
import se.newton.blogger.repositories.ArticleRepository;

import java.util.Iterator;

@Component
public class SetupListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(SetupListener.class);

    @Autowired
    private ArticleRepository artrep;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //Create some dummy data
        artrep.save(new Article("Alpha", "Lorem ipsum"));
        artrep.save(new Article("Beta", "Dolor sit amet"));
        artrep.save(new Article("Gamma", "Dumber data"));
        artrep.save(new Article("Delta", "More dummy data"));

//        Print out the dummy data using logger
        Iterable<Article> accounts = artrep.findAll();
        Iterator<Article> iteratorA = accounts.iterator();
        while (iteratorA.hasNext()) {
            logger.info("{}", iteratorA.next().toString());
        }
        System.out.println("end of runner");
    }

}
