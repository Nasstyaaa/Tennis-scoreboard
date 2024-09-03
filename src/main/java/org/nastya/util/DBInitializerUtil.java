package org.nastya.util;

import org.hibernate.Session;
import org.nastya.model.Match;
import org.nastya.model.Player;

public class DBInitializerUtil {

    public static void init() {
        Session session = DataListenerUtil.getSession();

        Player player1 = new Player("Miomir Kecmanovic");
        Player player2 = new Player("Lorenzo Musetti");
        Player player3 = new Player("Karolina Pliskova");
        Player player4 = new Player("Jasmine Paolini");
        Player player5 = new Player("Мирра Андреева");

        session.beginTransaction();
        session.save(player1);
        session.save(player2);
        session.save(player3);
        session.save(player4);
        session.save(player5);
        session.getTransaction().commit();


        session.beginTransaction();
        session.save(new Match(player1, player2, player2));
        session.save(new Match(player3, player2, player3));
        session.save(new Match(player3, player4, player3));
        session.save(new Match(player1, player4, player4));
        session.save(new Match(player5, player2, player2));
        session.save(new Match(player4, player5, player5));
        session.save(new Match(player2, player4, player2));
        session.save(new Match(player2, player5, player4));
        session.save(new Match(player1, player5, player1));
        session.save(new Match(player3, player1, player3));
        session.save(new Match(player4, player1, player1));
        session.getTransaction().commit();
    }
}
