package io.pax.cryptos.jpa;

import io.pax.cryptos.domain.jpa.JpaLine;
import io.pax.cryptos.domain.jpa.JpaUser;
import io.pax.cryptos.domain.jpa.JpaWallet;

import javax.persistence.EntityManager;

/**
 * Created by AELION on 14/02/2018.
 */
public class JpaLineDao {


    JpaConnector connector = new JpaConnector();


    public static void main(String[] args) {

        UserJpaDao userDao = new UserJpaDao();
        JpaUser kenny = userDao.createUser("kenny");
        JpaWallet wallet = kenny.getWallets().get(0);

        JpaLineDao dao = new JpaLineDao();
        EntityManager em = dao.connector.createEntityManager();

        em.getTransaction().begin();
        kenny = em.merge(kenny);
        wallet = em.merge(wallet);


        JpaLine lineBTC = new JpaLine();
        lineBTC.setQuantity(12.34);
        lineBTC.setSymbol("BTC");
        lineBTC.setWallet(wallet);
        em.persist(lineBTC);

        JpaLine lineXMR = new JpaLine();
        lineXMR.setQuantity(1045);
        lineXMR.setSymbol("XMR");
        lineXMR.setWallet(wallet);
        em.persist(lineXMR);


        em.getTransaction().commit();
        em.close();
        dao.connector.close();

        System.out.println(lineXMR.getWallet().toString());






    }
}
