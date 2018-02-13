package io.pax.cryptos.ws;

import io.pax.cryptos.dao.UserDao;
import io.pax.cryptos.domain.jdbc.Fulluser;
import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.Wallet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 06/02/2018.
 */
@Path("users") // cryptos/api/users
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)


    public class UserWS {
    @GET
    public List<User> getUsers() throws SQLException {
        UserDao dao = new UserDao();
        return dao.listUsers();
    }
    @GET
    @Path("{id}") // pathparam
    public User getUser(@PathParam("id") int userId) throws SQLException {
        return new UserDao().findUserWithWallet(userId);
    }
    @POST
    public User createUser (Fulluser users){
        List<Wallet> wallets = new ArrayList<>();

        try {
            int id = new UserDao().createUser(users.getName());
            return  new Fulluser(id , users.getName(), wallets);
        } catch (SQLException e) {
            throw  new ServerErrorException("Data error, sorry", 500);
        }


    }


}
