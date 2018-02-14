package io.pax.cryptos.jpa;

import io.pax.cryptos.domain.jpa.JpaUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by AELION on 13/02/2018.
 */
public class JpaConnector {

    static EntityManagerFactory factory;


    void connect() {

        if (this.factory == null) {
            this.factory = Persistence.createEntityManagerFactory("cryptos");

        }
    }

    public EntityManager createEntityManager() {

        // if already connected do nothing
        this.connect();

        return factory.createEntityManager();
    }

    void close(){
        this.factory.close();
    }


    public static void main(String[] args) { //psvm
    JpaConnector connector = new JpaConnector();
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
        connector.close();

}


}
