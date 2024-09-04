package org.nastya.dao;

import org.hibernate.Session;
import org.nastya.model.Player;
import org.nastya.util.DataListenerUtil;

import java.util.Optional;

public class PlayerDAO {

    public Optional<Player> findByPlayerName(String name) {

        try (Session session = DataListenerUtil.getSession()) {
            session.beginTransaction();

            Player player = session.createSelectionQuery("FROM Player WHERE name = :name", Player.class)
                    .setParameter("name", name)
                    .uniqueResult();

            session.getTransaction().commit();
            return Optional.ofNullable(player);
        }
    }

    public Player save(Player player) {

        try (Session session = DataListenerUtil.getSession()) {
            session.beginTransaction();

            session.persist(player);

            session.getTransaction().commit();
            return player;
        }
    }
}
