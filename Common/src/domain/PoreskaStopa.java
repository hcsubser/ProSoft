/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Saki
 */
public class PoreskaStopa extends OpstiDomenskiObjekat {

    private int id;
    private double vrednost;

    public PoreskaStopa() {
    }

    public PoreskaStopa(int id, double vrednost) {
        this.id = id;
        this.vrednost = vrednost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVrednost() {
        return vrednost;
    }

    public void setVrednost(double vrednost) {
        this.vrednost = vrednost;
    }

    @Override
    public String toString() {
       // return "PoreskaStopa{" + "id=" + id + ", vrednost=" + vrednost + '}';
       return vrednost+"";
    }

    @Override
    public String nazivTabele() {
        return "poreskastopa";
    }

    @Override
    public String alijas() {
        return "ps";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            PoreskaStopa ps = new PoreskaStopa(
                    rs.getInt("ps.id"),
                    rs.getDouble("ps.vrednost") 
            );
            lista.add(ps);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(vrednost)";
    }

    @Override
    public String vrednostiZaInsert() {
        return  ""+vrednost;
    }

    @Override
    public String vrednostiZaUpdate() {
        return "vrednost = " + vrednost;
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "id = " + id;
    }

    @Override
    public String uslov() {
        return "";
    }
}
