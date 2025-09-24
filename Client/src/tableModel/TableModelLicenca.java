/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import controller.Controller;
import domain.Licenca;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Milan
 */
public class TableModelLicenca extends AbstractTableModel {

    private ArrayList<Licenca> listaSs;
    private final String[] kolone = {"Naziv", "Kategorija"};

    public TableModelLicenca() {
        try {
            listaSs= Controller.getInstance().ucitajLicenceIzBaze();
        } catch (Exception ex) {
            Logger.getLogger(TableModelLicenca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return listaSs.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Licenca l = listaSs.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return l.getNaziv();
            case 1:
                return l.getKategorija();
            default:
                return null;

        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1;
    }

    public Licenca getLicenca(int row) {
        return listaSs.get(row);
    }
     public void refresh()  {
        try {
            listaSs=Controller.getInstance().ucitajLicenceIzBaze();
            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(TableModelEvidencijaKursa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
