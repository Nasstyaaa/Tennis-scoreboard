package org.nastya.DAO;


import org.hibernate.Session;
import org.nastya.model.Match;
import org.nastya.model.Player;
import org.nastya.util.DataSourceUtil;

public class MatchDAO {

    public Match save(Player player1, Player player2, Player winner) {

        try(Session session = DataSourceUtil.getSession()) {
            session.beginTransaction();

            Match match = new Match(player1, player2, winner);
            session.save(match);

            session.getTransaction().commit();
            return match;
        }
    }
}
