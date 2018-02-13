package io.pax.cryptos.domain.jpa;

import io.pax.cryptos.domain.Wallet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by AELION on 13/02/2018.
 */

@Entity
public class JpaWallet implements Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;


    @Override
    public int getId() {

        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
