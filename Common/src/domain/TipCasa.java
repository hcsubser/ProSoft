/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.Objects;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Milan
 */
public class TipCasa extends OpstiDomenskiObjekat {

    private int id;
    private String naziv;
    private String opis;
    private double cena;

    public TipCasa() {
    }

    public TipCasa(int id, String naziv, String opis, double cena) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
    }

    public TipCasa(String naziv, String opis, double cena) {

        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipCasa other = (TipCasa) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String nazivTabele() {
        return "tipCasa";
    }

    @Override
    public String alijas() {
        return "tc";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            TipCasa tc = new TipCasa(
                    rs.getInt("tc.id"),
                    rs.getString("tc.naziv"),
                    rs.getString("tc.opis"),
                    rs.getDouble("tc.cena")
            );
            lista.add(tc);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(naziv, opis, cena)";
    }

    @Override
    public String vrednostiZaInsert() {
        return  "'"+naziv + "', '" + opis + "', " + cena;
    }

    @Override
    public String vrednostiZaUpdate() {
        return "naziv = '" + naziv + "', opis = '" + opis +"', cena = " + cena;
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
