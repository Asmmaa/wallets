package io.pax.cryptos.domain;

import java.util.List;

/**
 * Created by AELION on 09/02/2018.
 */
public class Fulluser extends  SimpleUser {

    List<Wallet> wallets;

    public Fulluser(int id, String name, List<Wallet> wallets) {
        super(id, name);
        this.wallets = wallets;
    }
    public Fulluser() {
        super();
    }

    @Override
    public String toString(){
        return this.name +" : "+ this.wallets;
    }

    @Override
    public List<Wallet> getWallets(){
        return  this.wallets;

    }
}
