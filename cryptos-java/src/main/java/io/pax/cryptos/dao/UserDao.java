package io.pax.cryptos.dao;


import io.pax.cryptos.domain.SimpleUser;
import io.pax.cryptos.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 08/02/2018.
 */
public class UserDao {

    JdbcConnector connector = new JdbcConnector();
   
    public List<User> listUsers() throws SQLException {
        List<User> users = new ArrayList<User>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user");

        while (rs.next()) {
            String name = rs.getString("name");
            int id = rs.getInt("id");
           // List<Wallet> wallets = new ArrayList<>();

            users.add(new SimpleUser(id, name));

        }

        rs.close();
        stmt.close();
        conn.close();

        return users;


    }

    public int createUser(String name) throws SQLException {
        //most important stuff: never ever string concatenation in JDBC
        String query = "INSERT INTO user (name) VALUES (?)";

        //System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, name);
        //stmt.executeUpdate();
       int rows = stmt.executeUpdate(); // stmt.executeUpdate(query); le prepare le fait deja




        if(rows != 1){
            throw new SQLException("Something wrong happened with : "+ query);
        }

        ResultSet keys = stmt.getGeneratedKeys(); // 1 seul clé
        keys.next();
        int id  = keys.getInt(1);

        stmt.close();
        conn.close();
        return id;
    }



    public void deleteUser(int userId) throws SQLException{
        String query = "DELETE FROM user WHERE id=?";
        System.out.println(query);

        new WalletDao().deleteAll(userId);

        Connection conn = this.connector.getConnection();
        ///Statement stmt = conn.createStatement();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, userId);
        stmt.executeUpdate();

        stmt.close();
        conn.close();


    }

    public List<User> findByName(String extract) throws SQLException {

        List<User> users = new ArrayList<User>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM user WHERE name LIKE '"+extract+"%';";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String name = rs.getString("name");
            int id = rs.getInt("id");

            users.add(new SimpleUser(id, name));

        }

        rs.close();
        stmt.close();
        conn.close();

        return users;

    }
    public void deleteByName(String exactName) throws SQLException {


        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO user (name) VALUES ?", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, exactName);
        int rows = stmt.executeUpdate(); // stmt.executeUpdate(query); le prepare le fait deja

        if(rows != 1){
            throw new SQLException("Something wrong happened with : "+ "INSERT INTO user name VALUES ?");
        }

        ResultSet keys = stmt.getGeneratedKeys(); // 1 seul clé
        keys.next();
        int userId  = keys.getInt(1);

        System.out.println(userId);

        new WalletDao().deleteAll(userId);

        stmt.close();
        conn.close();



        stmt = conn.prepareStatement("DELETE FROM user WHERE id=?");
        stmt.setInt(1, userId);
        stmt.executeUpdate();



        stmt.close();
        conn.close();

    }



    public void updateUser(int userId, String newName) throws SQLException {

        String query = "UPDATE user SET name = ? WHERE  id= ?;";

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, newName);
        stmt.setInt(2, userId);
        stmt.executeUpdate();

        stmt.close();
        conn.close();

    }

    /*public void deleteUser(int userId) throws SQLException {
        // delete wallets, then delete User


    }*/


    public static void main(String[] args) throws SQLException { //psvm

        UserDao userDao = new UserDao();
        //userDao.createUser("newname");
        //userDao.deleteUser(3);
        //userDao.updateUser(3,"newname");
        //System.out.println(userDao.findByName("A"));
        userDao.deleteByName("Julien");


    }
}

