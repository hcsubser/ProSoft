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
 * @author Milan
 */
public class Polaznik extends OpstiDomenskiObjekat{
    private int id;
    private String ime;
    private String prezime;
    private String telefon;
    private String email;
    private Mesto mesto;

    public Polaznik() {
    }

    public Polaznik(int id, String ime, String prezime, String telefon, String email, Mesto mesto) {
        this.id = id;
        this.prezime= prezime;
        this.telefon = telefon;
        this.email = email;
        this.mesto = mesto;
        this.ime=ime;
    }
     public Polaznik(String ime, String prezime, String telefon, String email, Mesto mesto) {
        
        this.prezime = prezime;
        this.telefon = telefon;
        this.email = email;
        this.mesto = mesto;
        this.ime=ime;
    }


    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime (String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

   @Override
    public String nazivTabele() {
        return "polaznik";
    }

    @Override
    public String alijas() {
        return "p";
    }

    @Override
    public String join() {
        return "JOIN mesto m ON p.idMesto = m.id";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Mesto m = new Mesto(
            rs.getInt("m.id"),
            rs.getString("m.grad"),
            rs.getInt("m.postanskiBroj"),
            rs.getString("m.ulica")
        );
            Polaznik k = new Polaznik(
                    rs.getInt("p.id"),
                    rs.getString("p.ime"),
                    rs.getString("p.prezime"),
                    rs.getString("p.telefon"),
                    rs.getString("p.email"),
                    m
            );
            lista.add(k);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(ime, prezime, telefon, email, idMesto)";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', '" + telefon + "', '" + email + "', " + mesto.getId();
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "id = " + id;
    }

    @Override
    public String vrednostiZaUpdate() {
        return "ime = '" + ime + "', prezime = '" + prezime + "', telefon = '" + telefon + "', email = '" + email + "', idMesto = " + mesto.getId();
    }

    @Override
    public String uslov() {
       ArrayList<String> uslovi = new ArrayList<>();

    if (mesto != null && mesto.getId() > 0) {
        uslovi.add("p.idMesto = " + mesto.getId());
    }
    if (this != null && this.getIme()!=null) {
        uslovi.add("p.ime = '" + this.getIme()+"'");
    }
   
    if (uslovi.isEmpty()) {
        return ""; 
    }
    return "WHERE " + String.join(" AND ", uslovi);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
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
        final Polaznik other = (Polaznik) obj;
        return this.id == other.id;
    }

}
