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
public class Mesto extends OpstiDomenskiObjekat {

    private int id;
    private String grad;
    private int postanskiBroj;
    private String ulica;

    public Mesto() {
    }

    public Mesto(int idM, String grad, int postanskiBroj, String ulica) {
        this.id = idM;
        this.grad = grad;
        this.postanskiBroj = postanskiBroj;
        this.ulica = ulica;
    }

    public Mesto(String grad, int postanskiBroj, String ulica) {
        this.grad = grad;
        this.postanskiBroj = postanskiBroj;
        this.ulica = ulica;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public int getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(int postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    @Override
    public String toString() {
        if (ulica != null) {
            return grad + ", " + ulica;
        } else {
            return grad;
        }
    }

    @Override
    public String nazivTabele() {
        return "mesto";
    }

    @Override
    public String alijas() {
        return "m";
    }

    @Override
    public String join() {
        return "";
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
            lista.add(m);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(grad, postanskiBroj, ulica)";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "id= " + id;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + grad + "', " + postanskiBroj + ", '" + ulica + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "grad = '" + grad + "', postanskiBroj = " + postanskiBroj + ", ulica = '" + ulica + "'";
    }

    @Override
    public String uslov() {
        return "";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
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
        final Mesto other = (Mesto) obj;
        return this.id == other.id;
    }

}
