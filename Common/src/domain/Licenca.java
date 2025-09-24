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
    private String nivo;
    private boolean sertifikat;

    public Licenca() {
    }

    public Licenca(int id, String naziv, String nivo, boolean sertifikat) {
        this.id = id;
        this.naziv= naziv;
        this.nivo= nivo;
        this.sertifikat = sertifikat;
    }
    public Licenca(String naziv, String nivo, boolean sertifikat) {
        this.naziv= naziv;
        this.nivo= nivo;
        this.sertifikat = sertifikat;
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

    public String getNivo() {
        return nivo;
    }

    public void setNivo(String nivo) {
        this.nivo = nivo;
    }

    public boolean isSertifikat() {
        return sertifikat;
    }

    public void setSertifikat(boolean sertifikat) {
        this.sertifikat = sertifikat;
    }

    @Override
    public String toString() {
        return naziv ;
    }

     @Override
    public String nazivTabele() {
        return "";
    }

    @Override
    public String alijas() {
        return "ss";
    }

    @Override
    public String join() {
        return ""; 
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Licenca ss = new Licenca(
                rs.getInt("ss.id"),
                rs.getString("ss.naziv"),
                rs.getString("ss.nivo"),
                rs.getBoolean("ss.sertifikat")
            );
            lista.add(ss);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(naziv, nivo, sertifikat)";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'"+ naziv + "', '" + nivo + "', " + (sertifikat ? 1 : 0);
    }

    @Override
    public String vrednostiZaUpdate() {
        return "naziv = '" + naziv + "', nivo = '" + nivo + "', sertifikat = " + (sertifikat ? 1 : 0);
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
