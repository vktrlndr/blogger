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
    private ArticleRepository ar;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //Create some dummy data
        ar.save(new Article("Alpha", "Lorem ipsum", "Art"));
        ar.save(new Article("Beta", "Dolor sit amet", "Sports"));
        ar.save(new Article("Gamma", "Dumber data", "Sports"));
        ar.save(new Article("Delta", "More dummy data", "Art"));
        ar.save(new Article("Epsilon", "A star", "Sports"));
        ar.save(new Article("Zeta", "A longer text just to try out the text functionality of the" +
                " database you know. Filling in characters one by one until this exceeds normal varchar limits" +
                " and explodes in a cloud of stack traces.", "Art"));
        ar.save(new Article("Eta", "Allt i världen faller i en av två kategorier: " +
                "sport eller konst", "Art"));
        ar.save(new Article("Theta", "Det är bara att förhålla sig till detta faktum."
                , "Sports"));
        ar.save(new Article("Iota", "Was a band I think", "Art"));
        ar.save(new Article("Kappa", "Golden Kappa", "Art"));

//        Print out the dummy data using logger
        Iterable<Article> accounts = ar.findAll();
        Iterator<Article> iteratorA = accounts.iterator();
        while (iteratorA.hasNext()) {
            logger.info("{}", iteratorA.next().toString());
        }
        System.out.println("end of runner");
    }

}
