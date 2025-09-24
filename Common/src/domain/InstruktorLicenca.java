/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;


import java.util.Date;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
/**
 *
 * @author Saki
 */
public class InstruktorLicenca extends OpstiDomenskiObjekat{
    private Instruktor instruktor;
    private Licenca licenca;
    private Date datumObuke;

    public InstruktorLicenca() {
    }

    public InstruktorLicenca(Instruktor instruktor, Licenca licenca, Date datumObuke) {
        this.instruktor = instruktor;
        this.licenca = licenca;
        this.datumObuke = datumObuke;
    }

    public Instruktor getInstruktor() {
        return instruktor;
    }

    public void setInstruktor(Instruktor instruktor) {
        this.instruktor = instruktor;
    }

    public Licenca getLicenca() {
        return licenca;
    }

    public void setLicenca(Licenca licenca) {
        this.licenca = licenca;
    }

    public Date getDatumObuke() {
        return datumObuke;
    }

    public void setDatumObuke(Date datumObuke) {
        this.datumObuke = datumObuke;
    }

    @Override
    public String toString() {
        return "InstruktorLicenca{" + "instruktor=" + instruktor + ", licenca=" + licenca + ", datumObuke=" + datumObuke + '}';
    }
     @Override
    public String nazivTabele() {
        return "instruktorLicenca";
    }

    @Override
    public String alijas() {
        return "instruktorLicenca";
    }

    @Override
    public String join() {
        return "JOIN instruktor c ON instruktorLicenca.idInstruktor = c.id "
             + "JOIN licenca ss ON instruktorLicenca.idLicenca = ss.id";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Instruktor instruktor = new Instruktor();
            instruktor.setId(rs.getInt("c.id"));

            Licenca ss = new Licenca();
            ss.setId(rs.getInt("ss.id"));
            ss.setNaziv(rs.getString("ss.naziv"));
            ss.setNivo(rs.getString("ss.nivo"));
            ss.setSertifikat(rs.getBoolean("ss.sertifikat"));

            InstruktorLicenca instruktorLicenca = new InstruktorLicenca(instruktor, ss, rs.getDate("DatumObuke"));

            lista.add(instruktorLicenca);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(idInstruktor, idLicenca, datumObuke)";
    }

    @Override
    public String vrednostiZaInsert() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return instruktor.getId() + ", " + licenca.getId() + ", '" + sdf.format(datumObuke) + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "datumObuke = '" + sdf.format(datumObuke) + "'";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "idInstruktor = " + instruktor.getId() + " AND idLicenca = " + licenca.getId();
    }

    @Override
    public String uslov() {
        return " WHERE " + vrednostZaPrimarniKljuc();
    }
}
