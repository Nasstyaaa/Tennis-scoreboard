package org.nastya.DAO;


import org.hibernate.Session;
import org.nastya.model.Match;
import org.nastya.util.DataSourceUtil;

public class MatchDAO {

    public Match save(Match match) {

        try(Session session = DataSourceUtil.getSession()) {
            session.beginTransaction();

            session.save(match);

            session.getTransaction().commit();
            return match;
        }
    }
}
