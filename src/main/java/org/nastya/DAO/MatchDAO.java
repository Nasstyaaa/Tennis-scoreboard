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

    public List<Match> findByPlayerName(String namePlayer){
        try(Session session = DataSourceUtil.getSession()) {
            session.beginTransaction();

            String queryString = "FROM Match WHERE player1.name = :namePlayer OR player2.name = :namePlayer";
            List<Match> matches = session.createQuery(queryString).setParameter("namePlayer", namePlayer).getResultList();

            session.getTransaction().commit();
            return matches;
        }
    }
}
