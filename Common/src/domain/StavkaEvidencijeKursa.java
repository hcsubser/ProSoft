/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Milan
 */
public class StavkaEvidencijeKursa extends OpstiDomenskiObjekat {
    private int rb;
    private Date datumPrisustva;
    private String napomena;
    private boolean zavrsen;
    private TipCasa tipCasa;
    private EvidencijaKursa evidencijakursa;
    public StavkaEvidencijeKursa() {
    }
    public StavkaEvidencijeKursa(int rb, Date datumPrisustva, String napomena,boolean zavrsen, TipCasa tipCasa, EvidencijaKursa evidencijakursa) {
        this.rb = rb;
        this.datumPrisustva = datumPrisustva;
        this.napomena = napomena;
        this.zavrsen = zavrsen;
        this.tipCasa = tipCasa;
        this.evidencijakursa = evidencijakursa;
    }
    public StavkaEvidencijeKursa( Date datumPrisustva, String napomena,boolean zavrsen, TipCasa tipCasa, EvidencijaKursa evidencijakursa) {

        this.datumPrisustva = datumPrisustva;
        this.napomena = napomena;
        this.zavrsen = zavrsen;
        this.tipCasa = tipCasa;
        this.evidencijakursa = evidencijakursa;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public Date getDatumPrisustva() {
        return datumPrisustva;
    }

    public void setDatumPrisustva(Date datumPrisustva) {
        this.datumPrisustva = datumPrisustva;
    }

    public boolean isZavrsen() {
        return zavrsen;
    }

    public void setZavrsen(boolean zavrsen) {
        this.zavrsen = zavrsen;
    }

    
    
    public TipCasa getTipCasa() {
        return tipCasa;
    }

    public void setTipCasa(TipCasa tipCasa) {
        this.tipCasa = tipCasa;
    }

    public EvidencijaKursa getEvidencijaKursa() {
        return evidencijakursa;
    }

    public void setEvidencijaKursa(EvidencijaKursa evidencijakursa) {
        this.evidencijakursa = evidencijakursa;
    }

    @Override
    public String toString() {
        return "StavkaEvidencijeKursa{" + "rb=" + rb + ", datumPrisustva=" + datumPrisustva + ", zavrsen=" + zavrsen + ", tipCasa=" + tipCasa + ", evidencijakursa=" + evidencijakursa + '}';
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
        final StavkaEvidencijeKursa other = (StavkaEvidencijeKursa) obj;
        if (this.rb != other.rb) {
            return false;
        }
        if (!Objects.equals(this.tipCasa, other.tipCasa)) {
            return false;
        }
        return Objects.equals(this.evidencijakursa, other.evidencijakursa);
    }

    @Override
    public String nazivTabele() {
        return "stavkaevidencijekursa";
    }

    @Override
    public String alijas() {
        return "so";
    }

    @Override
    public String join() {
        return "JOIN tipCasa tc ON (so.idTipCasa = tc.id) "
                + "JOIN evidencijakursa o ON (so.idEvidencijaKursa = o.id)"
                +"JOIN instruktor i ON(i.id = o.idInstruktor)"
                +"JOIN polaznik p ON (p.id = o.idPolaznik)"
                +"JOIN mesto m ON (m.id = p.idMesto)";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            // TipCasa
            TipCasa tipCasa = new TipCasa(
                    rs.getInt("tc.id"),
                    rs.getString("tc.naziv"),
                    rs.getString("tc.opis"),
                    rs.getDouble("tc.cena")
            );
            Instruktor instruktor = new Instruktor(
                    rs.getInt("i.id"),
                    rs.getString("i.ime"),
                    rs.getString("i.prezime"),
                    rs.getString("i.korisnickoIme"),
                    rs.getString("i.lozinka")
            );
            Mesto mesto = new Mesto(
                    rs.getInt("m.id"),
                    rs.getString("m.grad"),
                    rs.getInt("m.postanskiBroj"),
                    rs.getString("m.ulica")
            );

            Polaznik polaznik = new Polaznik(
                    rs.getInt("p.id"),
                    rs.getString("p.ime"),
                    rs.getString("p.prezime"),
                    rs.getString("p.telefon"),
                    rs.getString("p.email"),
                    mesto
                    
            );

            EvidencijaKursa evidencijakursa = new EvidencijaKursa(
                    rs.getInt("o.id"),
                    rs.getDouble("o.ukupanIznos"),
                    instruktor,
                    polaznik,null
            );

            StavkaEvidencijeKursa so = new StavkaEvidencijeKursa(
                    rs.getInt("rb"),
                    rs.getDate("datumPrisustva"),
                    rs.getString("napomena"),
                    rs.getBoolean("zavrsen"),
                    tipCasa,
                    evidencijakursa
            );

            lista.add(so);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (idEvidencijaKursa, rb, datumPrisustva, napomena, zavrsen, idTipCasa) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + evidencijakursa.getId() + ", " + rb + ", "
                +  "STR_TO_DATE('" + datumPrisustva.getDay() + "-" + datumPrisustva.getMonth() + "-" + (datumPrisustva.getYear()+1900) +"', '%d-%m-%Y')" 
                + ", '" + napomena + "', " + ((zavrsen) ? "1":"0") + ", " + tipCasa.getId() + " ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " idEvidencijaKursa = " + evidencijakursa.getId();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " WHERE o.id = " + evidencijakursa.getId();
    }

}
