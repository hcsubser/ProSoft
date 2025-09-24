/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import controller.Controller;
import javax.swing.table.AbstractTableModel;
import domain.Instruktor;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 *
 * @author Saki
 */
public class TableModelInstruktor extends AbstractTableModel {
    private ArrayList<Instruktor> listaInstruktora;
    private String[] kolone={"Ime","Prezime"};

    public TableModelInstruktor() {
        try {
            listaInstruktora = Controller.getInstance().ucitajInstruktoreIzBaze();
        } catch (Exception ex) {
            Logger.getLogger(TableModelInstruktor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return listaInstruktora.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Instruktor c=listaInstruktora.get(rowIndex);
        switch(columnIndex){
            case 0:
                return c.getIme();
            case 1:
                return c.getPrezime();
            default:
                return null;
                        
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    public Instruktor getInstruktor(int row) {
        return listaInstruktora.get(row);
    }

    public void refresh() {
        try {
            listaInstruktora = Controller.getInstance().ucitajInstruktoreIzBaze();
            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(TableModelInstruktor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
