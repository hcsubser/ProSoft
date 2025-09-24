/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import controller.Controller;
import javax.swing.table.AbstractTableModel;
import domain.Polaznik;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 *
 * @author Milan
 */
public class TableModelPolaznik extends AbstractTableModel {

    private ArrayList<Polaznik> listaPolaznika;
    private String[] kolone = {"ID", "Naziv", "PIB", "Telefon", "Email", "Mesto"};

    public TableModelPolaznik() {
        try {
            listaPolaznika = Controller.getInstance().ucitajKupceIzBaze();
        } catch (Exception ex) {
            Logger.getLogger(TableModelPolaznik.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TableModelPolaznik(ArrayList<Polaznik> lista) {
        this.listaPolaznika = lista;
    }

    @Override
    public int getRowCount() {
        return listaPolaznika.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Polaznik k = listaPolaznika.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return k.getId();
            case 1:
                return k.getNaziv();
            case 2:
                return k.getPib();
            case 3:
                return k.getTelefon();
            case 4:
                return k.getEmail();
            case 5:
                return k.getMesto().getGrad();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public Polaznik getPolaznik(int row) {
        return listaPolaznika.get(row);
    }

    
    public void refresh() {
        try {
            listaPolaznika = Controller.getInstance().ucitajKupceIzBaze();
            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(TableModelPolaznik.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
