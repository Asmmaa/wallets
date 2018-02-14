package io.pax.cryptos.ws;

import io.pax.cryptos.business.WalletBusiness;
import io.pax.cryptos.dao.WalletDao;
import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.Wallet;
import io.pax.cryptos.domain.jdbc.FullWallet;
import io.pax.cryptos.domain.jdbc.SimpleUser;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 06/02/2018.
 */
@Path("wallets") // cryptos/api/wallets
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WalletWs {


    @EJB
    WalletBusiness walletBusiness;

    @GET
    public List<Wallet> getWallets() throws SQLException {
        WalletDao dao = new WalletDao();
        return dao.listWallets();
    }

    @GET
    @Path("{id}") //  {} c'est un param
    public Wallet getWallets(@PathParam("id") int wallet_id){

        return  walletBusiness.findWallet(wallet_id); // creer par le service


    }


    @POST
    public  Wallet createWallet (FullWallet wallet /* sent wallet, has no id*/ ){

     //  Optional<User> option = wallet.getUser();
        User user = wallet.getUser();

        if (user == null) { // si on sait que ca peut etre null mieux faire optionnal

            // 400: navigator sent wrong informations
            throw new NotAcceptableException("406: No user id sent");
        }

        if (wallet.getName().length() < 2) {
            throw new NotAcceptableException("406: wallet name must have at least 2 letters");
        }

        try {
            int id = new WalletDao().createWallet(user.getId(), wallet.getName());

            User boundUser = wallet.getUser();
            SimpleUser simpleUser = new SimpleUser(boundUser.getId(), boundUser.getName());
            return  new FullWallet(id , wallet.getName(), simpleUser);
        } catch (SQLException e) {
            //break the Principle of Least Astonishment (POLA)
            /*POLA says that a design should match the user's experience, expectations, and mental models. In this case, the naming of development,
            test and production machines should exploit pre-existing knowledge as a way to minimize the learning curve of what is where on our infrastructure.*/
            throw  new ServerErrorException("Data error, sorry", 500); // souvent 500 peut etre ossi 501 502 503 mais tres rarement
        }


    }


  /*  @POST
    public  void deleteWallet (FullWallet wallet){

        User user = wallet.getUser();

        if (user == null) { // si on sait que ca peut etre null mieux faire optionnal

            // 400: navigator sent wrong informations
            throw new NotAcceptableException("406: No user id sent");
        }

        if (wallet.getName().length() < 2) {
            throw new NotAcceptableException("406: wallet name must have at least 2 letters");
        }

        try {
            User boundUser = wallet.getUser();
            new WalletDao().deleteAll(boundUser.getId());
        } catch (SQLException e) {
            //break the Principle of Least Astonishment (POLA)
            *//*POLA says that a design should match the user's experience, expectations, and mental models. In this case, the naming of development,
            test and production machines should exploit pre-existing knowledge as a way to minimize the learning curve of what is where on our infrastructure.*//*
            throw  new ServerErrorException("Data error, sorry", 500); // souvent 500 peut etre ossi 501 502 503 mais tres rarement
        }}*/



    @POST
    public  void renameWallet (FullWallet wallet, String newName){

        try {
            new WalletDao().updateWallet(wallet.getId(), newName);
        } catch (SQLException e) {
            //break the Principle of Least Astonishment (POLA)
            /*POLA says that a design should match the user's experience, expectations, and mental models. In this case, the naming of development,
            test and production machines should exploit pre-existing knowledge as a way to minimize the learning curve of what is where on our infrastructure.*/
            throw  new ServerErrorException("Data error, sorry", 500); // souvent 500 peut etre ossi 501 502 503 mais tres rarement
        }
    }
    @DELETE
    @Path("{id}") // pathparam
    public void deleteWallet(@PathParam("id") int id) throws SQLException {
        new WalletDao().deleteWallet(id);
    }




}
