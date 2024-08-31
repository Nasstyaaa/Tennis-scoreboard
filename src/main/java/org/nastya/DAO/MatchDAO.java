package org.nastya.DAO;


import jakarta.persistence.Query;
import org.hibernate.Session;
import org.nastya.model.Match;
import org.nastya.util.DataSourceUtil;

import java.util.List;

public class MatchDAO {

    public Match save(Match match) {

        try (Session session = DataSourceUtil.getSession()) {
            session.beginTransaction();

            session.save(match);

            session.getTransaction().commit();
            return match;
        }
    }

    public double countAll() {
        try (Session session = DataSourceUtil.getSession()) {
            session.beginTransaction();

            double matchesCount = session.createQuery("FROM Match").getResultCount();

            session.getTransaction().commit();
            return matchesCount;
        }
    }

    public List<Match> findAllWithPagination(int first, int max) {

        try (Session session = DataSourceUtil.getSession()) {
            session.beginTransaction();

            Query query = session.createQuery("FROM Match");
            List<Match> matches = query.setFirstResult(first).setMaxResults(max).getResultList();

            session.getTransaction().commit();
            return matches;
        }
    }

    public List<Match> findByPlayerNameWithPagination(String namePlayer, int first, int max) {
        try (Session session = DataSourceUtil.getSession()) {
            session.beginTransaction();

            Query query = session.createQuery("""
                    FROM Match WHERE player1.name = :namePlayer OR player2.name = :namePlayer""");

            query.setParameter("namePlayer", namePlayer);
            List<Match> matches = query.setFirstResult(first).setMaxResults(max).getResultList();

            session.getTransaction().commit();
            return matches;
        }
    }

    public double countByPlayerName(String namePlayer) {
        try (Session session = DataSourceUtil.getSession()) {
            session.beginTransaction();

            double matchesCount = session.createQuery("""
                            
                            FROM Match WHERE player1.name = :namePlayer OR player2.name = :namePlayer""")
                    .setParameter("namePlayer", namePlayer)
                    .getResultCount();

            session.getTransaction().commit();
            return matchesCount;
        }
    }
}
