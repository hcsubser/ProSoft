/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Saki
 */
public class Instruktor extends OpstiDomenskiObjekat{

    private int id;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;

    public Instruktor() {
    }

    public Instruktor(int idInstruktor, String ime, String prezime, String korisnickoIme, String lozinka) {
        this.id = idInstruktor;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public Instruktor(String ime, String prezime, String korisnickoIme, String lozinka) {

        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public Instruktor(String korisnickoIme, String lozinka) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }

    @Override
    public String nazivTabele() {
        return "instruktor";
    }

    @Override
    public String alijas() {
        return "c";
    }

    @Override
    public String join() {
        return ""; 
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Instruktor c = new Instruktor(
                    rs.getInt("c.id"),
                    rs.getString("c.ime"),
                    rs.getString("c.prezime"),
                    rs.getString("c.korisnickoIme"),
                    rs.getString("c.lozinka")
            );
            lista.add(c);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(ime, prezime, korisnickoIme, lozinka)";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " id = " + id;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', '" + korisnickoIme + "', '" + lozinka + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "ime = '" + ime + "', prezime = '" + prezime + "', korisnickoIme = '" + korisnickoIme + "', lozinka = '" + lozinka + "'";
    }

    @Override
    public String uslov() {
        return "";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
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
        final Instruktor other = (Instruktor) obj;
        return this.id == other.id;
    }

}
