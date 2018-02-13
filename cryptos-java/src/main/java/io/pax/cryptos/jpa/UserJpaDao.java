package io.pax.cryptos.jpa;

import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.jpa.JpaUser;

import javax.persistence.EntityManager;

/**
 * Created by AELION on 13/02/2018.
 */
public class UserJpaDao {

    JpaConnector connector = new JpaConnector();
    

    public User createUser(String name){
        JpaUser user = new JpaUser();
        user.setName(name);
        EntityManager em = connector.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        System.out.println("User id: " +user.getId());

        return user;
    }

    public static void main(String[] args) { //psvm

        /*JpaConnector connector = new JpaConnector();
         EntityManager em = connector.createEntityManager();

       JpaUser jean = new JpaUser();
        jean.setName("jean");

        JpaUser jackie = new JpaUser();
        jackie.setName("jackie");

        JpaUser jules = new JpaUser();
        jules.setName("jules");

        JpaUser jasper = new JpaUser();
        jasper.setName("jasper");

        //open the box
        em.getTransaction().begin();

        em.persist(jean);  // insert
        em.persist(jackie);
        em.persist(jules);
        em.persist(jasper);

        em.getTransaction().commit();

        em.close();
        connector.close();*/

        UserJpaDao dao = new UserJpaDao();
        dao.createUser("Arthur"); //on ecrase notre ancienne BBD car on a create

        dao.connector.close();


    }
}
