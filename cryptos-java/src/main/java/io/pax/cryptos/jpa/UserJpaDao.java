package io.pax.cryptos.jpa;

import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.jpa.JpaUser;
import io.pax.cryptos.domain.jpa.JpaWallet;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by AELION on 13/02/2018.
 */
public class UserJpaDao {

    JpaConnector connector = new JpaConnector();
    

    public User createUser(String name){

        EntityManager em = connector.createEntityManager();
        em.getTransaction().begin();

        // cree le user
        JpaUser user = new JpaUser();
        user.setName(name);
        em.persist(user);

        // cree le wallet
        JpaWallet defaultWallet = new JpaWallet();
        defaultWallet.setName(name+" 's wallet");
        em.persist(defaultWallet);

        // jointure
        user.getWallets().add(defaultWallet);

        em.getTransaction().commit();
        em.close();
        System.out.println("User id: " +user.getId());

        return user;
    }


    public User find(int id) {

        EntityManager em = connector.createEntityManager();
        em.getTransaction().begin();

        JpaUser user = em.find(JpaUser.class, id);

        em.getTransaction().commit();
        em.close();

        return user;
    }


    public User findByName(String name) {

        EntityManager em = connector.createEntityManager();
        em.getTransaction().begin();

        // JPQL: Java Persistence Query Language
        TypedQuery<JpaUser> query = em.createQuery("SELECT u FROM JpaUser u WHERE u.name = :name", JpaUser.class);
        query.setParameter("name", name);
        List<JpaUser> users = query.getResultList();

        em.getTransaction().commit();
        em.close();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }

    }

    public void deleteByName(String name){

        EntityManager em = connector.createEntityManager();
        em.getTransaction().begin();

        // JPQL: Java Persistence Query Language
        TypedQuery<JpaUser> query = em.createQuery("SELECT u FROM JpaUser u WHERE u.name = :pName", JpaUser.class);

        query.setParameter("pName", name);
        List<JpaUser> users = query.getResultList();

        for(User u : users){
            em.remove(u);

        }

        em.getTransaction().commit();
        em.close();

    }

    public static void main(String[] args) { //psvm

        UserJpaDao dao = new UserJpaDao();
        dao.createUser("Arthur"); //on ecrase notre ancienne BBD car on a create
       // System.out.println(dao.find(1).toString());
       // System.out.println(dao.findByName("jean").toString());
        //System.out.println(dao.findByName("jean"));
        //dao.deleteByName("Arthur");

        dao.connector.close();


    }
}
