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
    private double ukupanIznosBezPDv;
    private double ukupanIznosSaPDV;
    private Date datumIzdavanja;
    private double ukupanPopust;
    private Instruktor instruktor;
    private Polaznik polaznik;
    private ArrayList<StavkaEvidencijeKursa> stavkeEvidencijeKursa;

    public EvidencijaKursa() {
    }

    public EvidencijaKursa(double ukupanIznosBezPDv, double ukupanIznosSaPDV,double ukupanPopust, Date datumIzdavanja, Instruktor instruktor, Polaznik polaznik,ArrayList<StavkaEvidencijeKursa> stavkeEvidencijeKursa ) {
        this.stavkeEvidencijeKursa=stavkeEvidencijeKursa;
        this.ukupanIznosBezPDv = ukupanIznosBezPDv;
        this.ukupanIznosSaPDV = ukupanIznosSaPDV;
        this.datumIzdavanja = datumIzdavanja;
        this.instruktor = instruktor;
        this.polaznik = polaznik;
        this.ukupanPopust=ukupanPopust;
    }

    public EvidencijaKursa(int id, double ukupanIznosBezPDv, double ukupanIznosSaPDV,double ukupanPopust, Date datumIzdavanja, Instruktor instruktor, Polaznik polaznik, ArrayList<StavkaEvidencijeKursa> stavkeEvidencijeKursa) {
        this.id = id;
        this.ukupanIznosBezPDv = ukupanIznosBezPDv;
        this.ukupanIznosSaPDV = ukupanIznosSaPDV;
        this.datumIzdavanja = datumIzdavanja;
        this.instruktor = instruktor;
        this.polaznik = polaznik;
        this.ukupanPopust=ukupanPopust;
        this.stavkeEvidencijeKursa = stavkeEvidencijeKursa;
    }

    public ArrayList<StavkaEvidencijeKursa> getStavkeEvidencijeKursa() {
        return stavkeEvidencijeKursa;
    }

    public void setStavkeEvidencijeKursa(ArrayList<StavkaEvidencijeKursa> stavkeEvidencijeKursa) {
        this.stavkeEvidencijeKursa = stavkeEvidencijeKursa;
    }

    public double getUkupanPopust() {
        return ukupanPopust;
    }

    public void setUkupanPopust(double ukupanPopust) {
        this.ukupanPopust = ukupanPopust;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getUkupanIznosBezPDv() {
        return ukupanIznosBezPDv;
    }

    public void setUkupanIznosBezPDv(double ukupanIznosBezPDv) {
        this.ukupanIznosBezPDv = ukupanIznosBezPDv;
    }

    public double getUkupanIznosSaPDV() {
        return ukupanIznosSaPDV;
    }

    public void setUkupanIznosSaPDV(double ukupanIznosSaPDV) {
        this.ukupanIznosSaPDV = ukupanIznosSaPDV;
    }

   

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
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
        return "EvidencijaKursa{" + "id=" + id + ", ukupanIznosBezPDv=" + ukupanIznosBezPDv + ", ukupanIznosSaPDV=" + ukupanIznosSaPDV + ", datumIzdavanja=" + datumIzdavanja + ", instruktor=" + instruktor + ", polaznik=" + polaznik + '}';
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
                + "JOIN polaznik k ON (p.id = o.idPolaznik)"
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
                    rs.getDouble("o.ukupanIznosBezPDv"),
                    rs.getDouble("o.ukupanIznosSaPDV"),
                    rs.getDouble("o.ukupanPopust"),
                    rs.getDate("o.datumIzdavanja"),
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
        return "(ukupanIznosBezPDv, ukupanIznosSaPDV,ukupanPopust, datumIzdavanja, idInstruktor, idPolaznik)";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "id = " + id;
    }

    @Override
    public String vrednostiZaInsert() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datumStr = (datumIzdavanja != null) ? "'" + sdf.format(datumIzdavanja) + "'" : "NULL";

        return ukupanIznosBezPDv + ", " + ukupanIznosSaPDV +  ", "+ukupanPopust+", "
                + datumStr + ", " + instruktor.getId() + ", " + polaznik.getId();
    }

    @Override
    public String vrednostiZaUpdate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datumStr = (datumIzdavanja != null) ? "'" + sdf.format(datumIzdavanja) + "'" : "NULL";

        return "ukupanIznosBezPDv = " + ukupanIznosBezPDv
                + ", ukupanIznosSaPDV = " + ukupanIznosSaPDV
                +", ukupanPopust = "+ukupanPopust
                + ", datumIzdavanja = " + datumStr
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
    if (datumIzdavanja != null) {
        java.sql.Date sqlDatum = new java.sql.Date(datumIzdavanja.getTime());
        uslovi.add("o.datumIzdavanja = '" + sqlDatum + "'");
    }

    if (uslovi.isEmpty()) {
        return ""; 
    }
    return "WHERE " + String.join(" AND ", uslovi);
    }

}
