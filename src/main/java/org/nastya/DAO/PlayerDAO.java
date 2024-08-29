package org.nastya.DAO;

import org.hibernate.Session;
import org.nastya.model.Player;
import org.nastya.util.DataSourceUtil;

import java.util.List;
import java.util.Optional;

public class PlayerDAO {

    public Optional<Player> find(String name) {

        try (Session session = DataSourceUtil.getSession()) {
            session.beginTransaction();

            String queryString = "FROM Player WHERE name = :name";
            Player player = (Player) session.createQuery(queryString).setParameter("name", name).uniqueResult();

            session.getTransaction().commit();
            return Optional.ofNullable(player);
        }
    }

    public List<Player> findAll(){
        try (Session session = DataSourceUtil.getSession()) {
            session.beginTransaction();

            List<Player> players = session.createQuery("FROM Player ").getResultList();

            session.getTransaction().commit();
            return players;
        }
    }


    public Player save(String name) {

        try (Session session = DataSourceUtil.getSession()) {
            session.beginTransaction();

            Player player = new Player(name);
            session.save(player);

            session.getTransaction().commit();
            return player;
        }
    }
}
