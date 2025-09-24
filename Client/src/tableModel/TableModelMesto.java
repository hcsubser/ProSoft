/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import controller.Controller;
import javax.swing.table.AbstractTableModel;
import domain.Mesto;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saki
 */
public class TableModelMesto  extends AbstractTableModel{
    private ArrayList<Mesto> listaMesta;
    private String[] kolone={"Grad","Postanski Broj","Ulica"};

    public TableModelMesto() {
        try {
            listaMesta=Controller.getInstance().ucitajMestaIzBaze();
        } catch (Exception ex) {
            Logger.getLogger(TableModelMesto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public int getRowCount() {
        return listaMesta.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Mesto m=listaMesta.get(rowIndex);
        switch(columnIndex){
            case 0:
                return m.getGrad();
            case 1:
                return m.getPostanskiBroj();
            case 2:
                return m.getUlica();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    public Mesto getMesto(int row) {
        return listaMesta.get(row);
    }

     public void refresh()  {
        try {
            listaMesta=Controller.getInstance().ucitajMestaIzBaze();
            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(TableModelEvidencijaKursa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
