/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mmmdeb
 */
public class Mesto implements AbstractDomainObject{

    int id;
    String naziv;
    int zipcode;

    public Mesto(int id, String naziv, int zipcode) {
        this.id = id;
        this.naziv = naziv;
        this.zipcode = zipcode;
    }


    //getters and setters
    public int getID(){
        return id;
    }
    
    public void setID(int id){
        this.id=id;
    }
    
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
    
    
    
    //overrides:
    @Override
    public String getTableName() {
        return "Mesto";
    }

    @Override
    public String getColumnNamesForInsert() {
       return " ( naziv, postalCode)";
    }

    @Override
    public String getInsertValues() {
        return  "'"+naziv + "', "+ zipcode + " ";
    }

    @Override
    public String getUpdateValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getPrimaryKeyValue() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String alias() {
        return "";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public String condition() {
        return "";
    }

    @Override
    public List<AbstractDomainObject> getAll(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            
            Mesto mesto = new Mesto(rs.getInt("idMesto"), rs.getString("naziv"), rs.getInt("postalCode"));
            lista.add(mesto);
        }

        rs.close();
        return lista;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
    
    
}
