package io.pax.cryptos.domain.jdbc;

import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.Wallet;

import java.util.List;

/**
 * Created by AELION on 08/02/2018.
 */
public class SimpleUser implements User {

    int id;
    String name;
    List<Wallet> wallets;

    public SimpleUser(int id, String name) { //alt + insert
        this.id= id;
        this.name = name;
       // this.wallets = wallets;

    }
    public SimpleUser() {

    }

    @Override
    public int getId() {
        return id;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Wallet> getWallets() {
        return (List<Wallet>) wallets;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWallets() {this.wallets = wallets;}


    public String toString(){
        return this.wallets.toString();
    }

}
