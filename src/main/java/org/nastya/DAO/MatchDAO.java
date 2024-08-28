package org.nastya.DAO;


import org.hibernate.Session;
import org.nastya.model.Match;
import org.nastya.util.DataSourceUtil;

import java.util.List;

public class MatchDAO {

    public Match save(Match match) {

        try(Session session = DataSourceUtil.getSession()) {
            session.beginTransaction();

            session.save(match);

            session.getTransaction().commit();
            return match;
        }
    }

    public List<Match> findAll(){

        try(Session session = DataSourceUtil.getSession()) {
            session.beginTransaction();

            List<Match> matches = session.createQuery("FROM Match").getResultList();

            session.getTransaction().commit();
            return matches;
        }
    }
}
