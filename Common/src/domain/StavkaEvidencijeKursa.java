/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Saki
 */
public class StavkaEvidencijeKursa extends OpstiDomenskiObjekat {
    private int rb;
    private int kolicina;
    private String napomena;
    private double iznosBezPDV;
    private double iznosSaPDV;
    private double cenaBezPDV;
    private double cenaSaPDV;
    private TipCasa tipCasa;
    private EvidencijaKursa evidencijakursa;
    public StavkaEvidencijeKursa() {
    }
    public StavkaEvidencijeKursa(int rb, int kolicina, String napomena, double iznosBezPDV, double iznosSaPDV, double cenaBezPDV, double cenaSaPdDV, TipCasa tipCasa, EvidencijaKursa evidencijakursa) {
        this.rb = rb;
        this.kolicina = kolicina;
        this.napomena = napomena;
        this.iznosBezPDV = iznosBezPDV;
        this.iznosSaPDV = iznosSaPDV;
        this.cenaBezPDV = cenaBezPDV;
        this.cenaSaPDV = cenaSaPdDV;
        this.tipCasa = tipCasa;
        this.evidencijakursa = evidencijakursa;
    }
    public StavkaEvidencijeKursa(int kolicina, String napomena, double iznosBezPDV, double iznosSaPDV, double cenaBezPDV, double cenaSaPdDV, TipCasa tipCasa, EvidencijaKursa evidencijakursa) {

        this.kolicina = kolicina;
        this.napomena = napomena;
        this.iznosBezPDV = iznosBezPDV;
        this.iznosSaPDV = iznosSaPDV;
        this.cenaBezPDV = cenaBezPDV;
        this.cenaSaPDV = cenaSaPdDV;
        this.tipCasa = tipCasa;
        this.evidencijakursa = evidencijakursa;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public double getIznosBezPDV() {
        return iznosBezPDV;
    }

    public void setIznosBezPDV(double iznosBezPDV) {
        this.iznosBezPDV = iznosBezPDV;
    }

    public double getIznosSaPDV() {
        return iznosSaPDV;
    }

    public void setIznosSaPDV(double iznosSaPDV) {
        this.iznosSaPDV = iznosSaPDV;
    }

    public double getCenaBezPDV() {
        return cenaBezPDV;
    }

    public void setCenaBezPDV(double cenaBezPDV) {
        this.cenaBezPDV = cenaBezPDV;
    }

    public double getCenaSaPDV() {
        return cenaSaPDV;
    }

    public void setCenaSaPDV(double cenaSaPdDV) {
        this.cenaSaPDV = cenaSaPdDV;
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
        return "StavkaEvidencijeKursa{" + "rb=" + rb + ", kolicina=" + kolicina + ", napomena=" + napomena + ", iznosBezPDV=" + iznosBezPDV + ", iznosSaPDV=" + iznosSaPDV + ", cenaBezPDV=" + cenaBezPDV + ", cenaSaPdDV=" + cenaSaPDV + ", tipCasa=" + tipCasa + ", evidencijakursa=" + evidencijakursa + '}';
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
        return "JOIN tipCasa a ON (so.idTipCasa = a.id) "
                + "JOIN evidencijakursa o ON (so.idEvidencijaKursa = o.id)"
                +"JOIN poreskastopa ps ON(a.poreskaStopa = ps.id)"
                +"JOIN instruktor c ON(c.id = o.idInstruktor)"
                +"JOIN polaznik k ON (k.id = o.idPolaznik)"
                +"JOIN mesto m ON (m.id = k.idMesto)";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            PoreskaStopa ps = new PoreskaStopa(
                    rs.getInt("ps.id"),
                    rs.getDouble("ps.vrednost")
            );

            // TipCasa
            TipCasa tipCasa = new TipCasa(
                    rs.getInt("a.id"),
                    rs.getString("a.naziv"),
                    rs.getString("a.opis"),
                    ps,
                    rs.getDouble("a.cenaBezPDV"),
                    rs.getDouble("a.cenaSaPDV"),
                    rs.getDouble("a.popust")
            );
            Instruktor instruktor = new Instruktor(
                    rs.getInt("c.id"),
                    rs.getString("c.ime"),
                    rs.getString("c.prezime"),
                    rs.getString("c.korisnickoIme"),
                    rs.getString("c.lozinka")
            );
            Mesto mesto = new Mesto(
                    rs.getInt("m.id"),
                    rs.getString("m.grad"),
                    rs.getInt("m.postanskiBroj"),
                    rs.getString("m.ulica")
            );

            Polaznik polaznik = new Polaznik(
                    rs.getInt("k.id"),
                    rs.getInt("k.pib"),
                    rs.getString("k.telefon"),
                    rs.getString("k.email"),
                    mesto,
                    rs.getString("k.naziv")
            );

            EvidencijaKursa evidencijakursa = new EvidencijaKursa(
                    rs.getInt("o.id"),
                    rs.getDouble("o.ukupanIznosBezPDV"),
                    rs.getDouble("o.ukupanIznosSaPDV"),
                    rs.getDouble("o.ukupanPopust"),
                    rs.getDate("o.datumIzdavanja"),                   
                    instruktor,
                    polaznik,null
            );

            StavkaEvidencijeKursa so = new StavkaEvidencijeKursa(
                    rs.getInt("rb"),
                    rs.getInt("kolicina"),
                    rs.getString("napomena"),
                    rs.getDouble("iznosBezPDV"),
                    rs.getDouble("iznosSaPDV"),
                    rs.getDouble("cenaBezPDV"),
                    rs.getDouble("cenaSaPDV"),
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
        return " (idEvidencijaKursa, rb, kolicina, napomena, iznosBezPDV, iznosSaPDV, cenaBezPDV, cenaSaPDV, idTipCasa) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + evidencijakursa.getId() + ", " + rb + ", " + kolicina + ", '"
                + napomena + "', " + iznosBezPDV + ", " + iznosSaPDV + ", "
                + cenaBezPDV + ", " + cenaSaPDV + ", " + tipCasa.getId() + " ";
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
