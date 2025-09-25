/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import controller.Controller;
import domain.TipCasa;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Milan
 */
public class TableModelTipCasa extends AbstractTableModel {

    private ArrayList<TipCasa> listaTipCasaa;
    private String[] kolone = {"Naziv", "Opis", "Cena"};

    public TableModelTipCasa() {
        try {
            listaTipCasaa = Controller.getInstance().ucitajTipCasaeIzBaze();
        } catch (Exception ex) {
            Logger.getLogger(TableModelTipCasa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return listaTipCasaa.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TipCasa a = listaTipCasaa.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return a.getNaziv();
            case 1:
                return a.getOpis();
            case 2:
                return a.getCena();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public TipCasa getTipCasa(int row) {
        return listaTipCasaa.get(row);
    }

    public void refresh() {
        try {
            listaTipCasaa = Controller.getInstance().ucitajTipCasaeIzBaze();
            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(TableModelTipCasa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
