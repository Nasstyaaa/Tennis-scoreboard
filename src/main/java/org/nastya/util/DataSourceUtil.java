package org.nastya.util;


import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.nastya.model.Match;
import org.nastya.model.Player;
import org.nastya.service.OngoingMatchesService;


@WebListener
public class DataSourceUtil implements ServletContextListener {
    public static SessionFactory sessionFactory;

    @Override
    public void contextInitialized(ServletContextEvent sce){
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Player.class)
                .addAnnotatedClass(Match.class);

        sessionFactory = configuration.buildSessionFactory();
        Session session = getSession();

        Player player1 = new Player("Tom");
        Player player2 = new Player("Bob");
        Player player3 = new Player("Tod");
        session.beginTransaction();
        session.save(player1);
        session.save(player2);
        session.save(player3);
        session.getTransaction().commit();

        session.beginTransaction();
        session.save(new Match(player1, player2, player2));
        session.save(new Match(player3, player2, player3));
        session.getTransaction().commit();

        OngoingMatchesService.getInstance();
    }

    public synchronized static Session getSession(){
        return sessionFactory.openSession();
    }
}
