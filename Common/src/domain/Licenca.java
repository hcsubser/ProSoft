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
 * @author Milan
 */
public class Licenca extends OpstiDomenskiObjekat {
    private int id;
    private String naziv;
    private String kategorija;

    public Licenca() {
    }

    public Licenca(int id, String naziv, String kategorija) {
        this.id = id;
        this.naziv= naziv;
        this.kategorija= kategorija;
    }
    public Licenca(String naziv, String kategorija) {
        this.naziv= naziv;
        this.kategorija= kategorija;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }


    @Override
    public String toString() {
        return naziv ;
    }

     @Override
    public String nazivTabele() {
        return "licenca";
    }

    @Override
    public String alijas() {
        return "l";
    }

    @Override
    public String join() {
        return ""; 
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Licenca l = new Licenca(
                rs.getInt("l.id"),
                rs.getString("l.naziv"),
                rs.getString("l.kategorija")
            );
            lista.add(l);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(naziv, kategorija)";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'"+ naziv + "', '" + kategorija + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "naziv = '" + naziv + "', kategorija = '" + kategorija + "'";
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
