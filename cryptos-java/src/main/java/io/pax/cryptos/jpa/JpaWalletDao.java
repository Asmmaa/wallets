package io.pax.cryptos.jpa;

import io.pax.cryptos.domain.jpa.JpaLine;
import io.pax.cryptos.domain.jpa.JpaWallet;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by AELION on 14/02/2018.
 */
public class JpaWalletDao {


    JpaConnector connector = new JpaConnector();

    public JpaWallet getWallet(int walletId){

        EntityManager em = connector.createEntityManager();

        em.getTransaction().begin();
        JpaWallet w = em.find(JpaWallet.class, walletId);


        String jpgl = "SELECT l FROM JpaLine l JOIN l.wallet w WHERE w.id =:id";

        List<JpaLine> lines = em.createQuery(jpgl, JpaLine.class).setParameter("id",walletId).getResultList();

        System.out.println(lines);

        w.setLines(lines);   // w.getLines().addAll(lines);

        em.getTransaction().commit();
        em.close();


        return  w;
    }


    public static void main(String[] args) {

        JpaWalletDao dao = new JpaWalletDao();
        JpaWallet wallet = dao.getWallet(1);

        System.out.println(wallet.toString());

        dao.connector.close();
    }
}


