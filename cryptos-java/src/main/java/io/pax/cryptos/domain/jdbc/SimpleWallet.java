package io.pax.cryptos.domain.jdbc;

import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.Wallet;

/**
 * Created by AELION on 06/02/2018.
 */
public class SimpleWallet implements Wallet {
    int id;
    String name;

    public SimpleWallet(int id,String name) { //alt + insert
        this.id= id;
        this.name = name;

    }

    public SimpleWallet() {

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public User getUser() {
        return null;
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

    public String toString(){
        return this.name;
    }
}
