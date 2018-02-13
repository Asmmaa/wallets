package io.pax.cryptos.dao;

import io.pax.cryptos.domain.Line;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 12/02/2018.
 */
public class LineDao {

    public DataSource connect() {
        
        DataSource data = null ;
        
        return data;

    }


    public boolean hasSymbolLine(int walletId, String symbol){
        boolean res = false;



        return res;
    }

    public List<Line> listLines(int walletId) throws SQLException {
        List<Line> listLines = null;

        return listLines;

    }


    public void buy(int walletId, String symbol,double quantity) throws SQLException {

    }


    public void sell(int walletId, String symbol,double quantity) throws SQLException {

    }
}
