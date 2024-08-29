package org.nastya.util;


import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.nastya.model.Match;
import org.nastya.model.Player;

@WebListener
public class DataSourceUtil implements ServletContextListener {
    public static SessionFactory sessionFactory;

    @Override
    public void contextInitialized(ServletContextEvent sce){
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Player.class)
                .addAnnotatedClass(Match.class);

        sessionFactory = configuration.buildSessionFactory();
        DBInitializerUtil.save(sessionFactory);
    }

    public synchronized static Session getSession(){
        return sessionFactory.openSession();
    }
}
