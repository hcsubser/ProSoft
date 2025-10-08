/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbb;


import domain.OpstiDomenskiObjekat;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Milan
 */
public class DatabaseBroker {

    private static DatabaseBroker instance;
    private Connection connection;

    private DatabaseBroker() throws SQLException, Exception {
        try {
            //  connect();
            try {
                Properties properties = new Properties();
                properties.load(new FileInputStream("dbconfiguration.properties"));
                String url = properties.getProperty("url");
                String username = properties.getProperty("username");
                String password = properties.getProperty("password");
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Konekcija sa bazom podataka uspesno uspostavljena!");
                connection.setAutoCommit(false);
            } catch (Exception ex) {
                //System.out.println("Greska! Konekcija sa bazom nije uspesno uspostavljena!\n" + ex.getMessage());
                throw new Exception("Greska! Konekcija sa bazom nije uspesno uspostavljena!");
        // ex.printStackTrace();
            }
        } catch (SQLException ex) {
        }
    }

    public static DatabaseBroker getInstance() throws SQLException, Exception {
        if (instance == null) {
            instance = new DatabaseBroker();

        }
        return instance;
    }

    public void connect() throws SQLException {
        try {
            String url = "jdbc:mysql://localhost:3306/proba1_baza";
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Konekcija sa bazom podataka uspesno uspostavljena!");
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println("Greska! Konekcija sa bazom nije uspesno uspostavljena!\n" + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    public void disconnect() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Konekcija sa bazom uspesno raskinuta!");
            }
        } catch (SQLException ex) {
            System.out.println("Greska! Konekcija sa bazom nije uspesno raskinuta!\n" + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    public void commit() throws SQLException {
        try {
            connection.commit();
            System.out.println("Transakcija uspesno potvrdjena!");
        } catch (SQLException ex) {
            System.out.println("Transakcija nije potvrdjena!\n" + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    public void rollback() throws SQLException {
        try {
            connection.rollback();
            System.out.println("Transakcija uspesno ponistena!");
        } catch (SQLException ex) {
            System.out.println("Transakcija nije ponistena!\n" + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public ArrayList<OpstiDomenskiObjekat> select(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "SELECT * FROM " + odo.nazivTabele() + " " + odo.alijas()
                + " " + odo.join() + " " + odo.uslov();
        System.out.println(upit);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        //ArrayList<OpstiDomenskiObjekat> lodo =  odo.vratiListu(rs);
        //return lodo;
        return odo.vratiListu(rs);
    }

    public PreparedStatement insert(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "INSERT INTO " + odo.nazivTabele() + " "
                + odo.koloneZaInsert() + " VALUES(" + odo.vrednostiZaInsert() + ")";
        System.out.println(upit);
        PreparedStatement ps = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        return ps;
    }

    public void update(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "UPDATE " + odo.nazivTabele() + " SET "
                + odo.vrednostiZaUpdate() + " WHERE " + odo.vrednostZaPrimarniKljuc();
        System.out.println(upit);
        Statement s = connection.createStatement();
        s.executeUpdate(upit);
    }

    public void delete(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "DELETE FROM " + odo.nazivTabele() + " WHERE " + odo.vrednostZaPrimarniKljuc();
        System.out.println(upit);
        Statement s = connection.createStatement();
        s.executeUpdate(upit);
    }
}
