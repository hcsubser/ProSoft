/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Milan
 */
public class EvidencijaKursa extends OpstiDomenskiObjekat {

    private int id;
    private double ukupanIznos;
    private Instruktor instruktor;
    private Polaznik polaznik;
    private ArrayList<StavkaEvidencijeKursa> stavkeEvidencijeKursa;

    public EvidencijaKursa() {
    }

    public EvidencijaKursa(double ukupanIznos, Instruktor instruktor, Polaznik polaznik,ArrayList<StavkaEvidencijeKursa> stavkeEvidencijeKursa ) {
        this.stavkeEvidencijeKursa=stavkeEvidencijeKursa;
        this.ukupanIznos = ukupanIznos;
        this.instruktor = instruktor;
        this.polaznik = polaznik;
    }

    public EvidencijaKursa(int id, double ukupanIznos, Instruktor instruktor, Polaznik polaznik, ArrayList<StavkaEvidencijeKursa> stavkeEvidencijeKursa) {
        this.id = id;
        this.ukupanIznos = ukupanIznos;
        this.instruktor = instruktor;
        this.polaznik = polaznik;
        this.stavkeEvidencijeKursa = stavkeEvidencijeKursa;
    }

    public ArrayList<StavkaEvidencijeKursa> getStavkeEvidencijeKursa() {
        return stavkeEvidencijeKursa;
    }

    public void setStavkeEvidencijeKursa(ArrayList<StavkaEvidencijeKursa> stavkeEvidencijeKursa) {
        this.stavkeEvidencijeKursa = stavkeEvidencijeKursa;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Instruktor getInstruktor() {
        return instruktor;
    }

    public void setInstruktor(Instruktor instruktor) {
        this.instruktor = instruktor;
    }

    public Polaznik getPolaznik() {
        return polaznik;
    }

    public void setPolaznik(Polaznik polaznik) {
        this.polaznik = polaznik;
    }

    @Override
    public String toString() {
        return "EvidencijaKursa{" + "id=" + id + ", ukupanIznos=" + ukupanIznos + ", instruktor=" + instruktor + ", polaznik=" + polaznik + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final EvidencijaKursa other = (EvidencijaKursa) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.instruktor, other.instruktor)) {
            return false;
        }
        return Objects.equals(this.polaznik, other.polaznik);
    }

    @Override
    public String nazivTabele() {
        return "evidencijakursa";
    }

    @Override
    public String alijas() {
        return "o";
    }

    @Override
    public String join() {
        return " JOIN instruktor i ON (i.id = o.idInstruktor) "
                + "JOIN polaznik p ON (p.id = o.idPolaznik)"
                + "JOIN mesto m ON (p.idMesto = m.id)";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Mesto m = new Mesto(
                    rs.getInt("m.id"),
                    rs.getString("m.grad"),
                    rs.getInt("m.postanskiBroj"),
                    rs.getString("m.ulica"));
            Polaznik polaznik = new Polaznik(
                    rs.getInt("p.id"),
                    rs.getString("p.ime"),
                    rs.getString("p.prezime"),
                    rs.getString("p.telefon"),
                    rs.getString("p.email"),
                    m
                    
            );

            Instruktor instruktor = new Instruktor(
                    rs.getInt("i.id"),
                    rs.getString("i.ime"),
                    rs.getString("i.prezime"),
                    rs.getString("i.korisnickoIme"),
                    rs.getString("i.lozinka")
            );

            EvidencijaKursa o = new EvidencijaKursa(
                    rs.getInt("o.id"),
                    rs.getDouble("o.ukupanIznos"),
                    instruktor,
                    polaznik,
                    null
            );

            lista.add(o);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(ukupanIznos, idInstruktor, idPolaznik)";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "id = " + id;
    }

    @Override
    public String vrednostiZaInsert() {
        
        return ukupanIznos +  ", " + instruktor.getId() + ", " + polaznik.getId();
    }

    @Override
    public String vrednostiZaUpdate() {


        return "ukupanIznos = " + ukupanIznos
                + ", idInstruktor = " + instruktor.getId()
                + ", idPolaznik = " + polaznik.getId();
    }

    @Override
    public String uslov() {
        ArrayList<String> uslovi = new ArrayList<>();

    if (instruktor != null && instruktor.getId() > 0) {
        uslovi.add("o.idInstruktor = " + instruktor.getId());
    }
    if (polaznik != null && polaznik.getId() > 0) {
        uslovi.add("o.idPolaznik = " + polaznik.getId());
    }
    if (uslovi.isEmpty()) {
        return ""; 
    }
    return "WHERE " + String.join(" AND ", uslovi);
    }

}
