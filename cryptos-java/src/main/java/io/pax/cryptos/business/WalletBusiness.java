package io.pax.cryptos.business;

import io.pax.cryptos.domain.Wallet;
import io.pax.cryptos.domain.jpa.JpaWallet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Stateless: it is java ee managed EJB
 * EJB is a super Object that does everything in your back
 */

@Stateless
public class WalletBusiness {


    //
    @PersistenceContext
    EntityManager em;

    public Wallet findWallet(int id){
        // transaction is opened in your back
        return em.find(JpaWallet.class,id);
    }
}
