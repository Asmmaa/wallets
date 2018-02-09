package io.pax.cryptos.dao;


import io.pax.cryptos.domain.SimpleWallet;
import io.pax.cryptos.domain.Wallet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 06/02/2018.
 */
public class WalletDao {

    JdbcConnector connector = new JdbcConnector();


    public List<Wallet> listWallets() throws SQLException {

        List<Wallet> wallets = new ArrayList<Wallet>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM wallet");

        while (rs.next()) {
            String name = rs.getString("name");
            int id = rs.getInt("id");

            wallets.add(new SimpleWallet(id, name));

        }

        rs.close();
        stmt.close();
        conn.close();

        return wallets;

    }


    public int createWallet(int userId, String name) throws SQLException {
        //most important stuff: never ever string concatenation in JDBC
        String query = "INSERT INTO wallet (name, user_id) VALUES (?,?)";

        System.out.println(query);

        Connection conn = this.connector.getConnection();
        ///Statement stmt = conn.createStatement();
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, name);
        stmt.setInt(2, userId);
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

    public void deleteWallet(int walletId) throws SQLException {
        String query = "DELETE FROM wallet WHERE id=?";
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        ///Statement stmt = conn.createStatement();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, walletId);
        stmt.executeUpdate();

        stmt.close();
        conn.close();


    }

    public void deleteByName(String name) throws SQLException {

        String query = "DELETE FROM wallet WHERE name=?";
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        ///Statement stmt = conn.createStatement();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, name);
        stmt.executeUpdate();

        stmt.close();
        conn.close();

    }

  public List<Wallet> findByName(String extract) throws SQLException {

      List<Wallet> wallets = new ArrayList<Wallet>();
      Connection conn =this.connector.getConnection();
      Statement stmt = conn.createStatement();
      String query = "SELECT * FROM wallet WHERE name LIKE '"+extract+"%';";
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
          String name = rs.getString("name");
          int id = rs.getInt("id");

          wallets.add(new SimpleWallet(id, name));

      }

      rs.close();
      stmt.close();
      conn.close();

      return wallets;

  }




     public void updateWallet(int walletId, String newName) throws SQLException {
        String query = "UPDATE wallet SET name = ? WHERE  id= ?;";

         Connection conn = this.connector.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query);
         stmt.setString(1, newName);
         stmt.setInt(2, walletId);
         stmt.executeUpdate();

         stmt.close();
         conn.close();

     }



    public void deleteAll(int userId) throws SQLException {

        String query = "DELETE FROM wallet WHERE user_id=?";

        Connection conn = this.connector.getConnection();
        ///Statement stmt = conn.createStatement();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, userId);
        stmt.executeUpdate();



        stmt.close();
        conn.close();

     }
    public static void main(String[] args) throws SQLException { //psvm
        //System.out.println( new WalletDao().listWallets());
        new WalletDao().createWallet(2, "Cool name");
        WalletDao walletDao = new WalletDao();
        //int id = walletDao.createWallet(2, "name");
        //walletDao.deleteWallet(id);
        //walletDao.deleteByName("name");
        //System.out.println(walletDao.findByName("B"));
        //walletDao.updateWallet(2, "newName");
        //walletDao.deleteAll(10);


    }
}
