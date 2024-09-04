package org.nastya.dao;


import org.hibernate.Session;
import org.nastya.model.Match;
import org.nastya.util.DataListenerUtil;

import java.util.List;

public class MatchDAO {

    public Match save(Match match) {

        try (Session session = DataListenerUtil.getSession()) {
            session.beginTransaction();

            session.persist(match);

            session.getTransaction().commit();
            return match;
        }
    }

    public int countAll() {
        try (Session session = DataListenerUtil.getSession()) {
            session.beginTransaction();

            int matchesCount = (int) session.createSelectionQuery("FROM Match", Match.class).getResultCount();

            session.getTransaction().commit();
            return matchesCount;
        }
    }

    public List<Match> findAllWithPagination(int offset, int max) {

        try (Session session = DataListenerUtil.getSession()) {
            session.beginTransaction();

            List<Match> matches = session.createSelectionQuery("FROM Match", Match.class)
                    .setFirstResult(offset)
                    .setMaxResults(max)
                    .getResultList();

            session.getTransaction().commit();
            return matches;
        }
    }

    public List<Match> findByPlayerNameWithPagination(String namePlayer, int offset, int max) {

        try (Session session = DataListenerUtil.getSession()) {
            session.beginTransaction();

            List<Match> matches = session.createSelectionQuery("""
                            FROM Match WHERE player1.name = :namePlayer OR player2.name = :namePlayer""", Match.class)
                    .setParameter("namePlayer", namePlayer)
                    .setFirstResult(offset).setMaxResults(max).getResultList();

            session.getTransaction().commit();
            return matches;
        }
    }

    public int countByPlayerName(String namePlayer) {

        try (Session session = DataListenerUtil.getSession()) {
            session.beginTransaction();

            int matchesCount = (int) session.createSelectionQuery("""
                            FROM Match WHERE player1.name = :namePlayer OR player2.name = :namePlayer""", Match.class)
                    .setParameter("namePlayer", namePlayer)
                    .getResultCount();

            session.getTransaction().commit();
            return matchesCount;
        }
    }
}
