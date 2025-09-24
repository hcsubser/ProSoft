/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import controller.Controller;
import domain.EvidencijaKursa;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 *
 * @author Milan
 */
public class TableModelEvidencijaKursa extends AbstractTableModel {

    private ArrayList<EvidencijaKursa> lista;
    private final String[] kolone = {"ID", "Datum izdavanja", "Iznos bez PDV-a", "Iznos sa PDV-om", "Ukupan popust", "Instruktor", "Polaznik"};

    public TableModelEvidencijaKursa() {
        try {
            lista = Controller.getInstance().ucitajEvidencijeKursaIzBaze();
        } catch (Exception ex) {
            Logger.getLogger(TableModelEvidencijaKursa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TableModelEvidencijaKursa(ArrayList<EvidencijaKursa> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        EvidencijaKursa o = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return o.getId();
            case 1:
                return o.getDatumIzdavanja();
            case 2:
                return o.getUkupanIznosBezPDv();
            case 3:
                return o.getUkupanIznosSaPDV();
            case 4:
                return o.getUkupanPopust();
            case 5:
                return o.getInstruktor().getIme() + " " + o.getInstruktor().getPrezime();
            case 6:
                return o.getPolaznik().getNaziv();

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

    public EvidencijaKursa getEvidencijaKursa(int row) {
        return lista.get(row);
    }

    public void refresh() {
        try {
            lista = Controller.getInstance().ucitajEvidencijeKursaIzBaze();
            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(TableModelEvidencijaKursa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
