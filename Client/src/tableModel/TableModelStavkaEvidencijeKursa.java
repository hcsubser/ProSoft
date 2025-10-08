/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import controller.Controller;
import domain.TipCasa;
import domain.EvidencijaKursa;
import javax.swing.table.AbstractTableModel;
import domain.StavkaEvidencijeKursa;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Milan
 */
public class TableModelStavkaEvidencijeKursa extends AbstractTableModel {

    private ArrayList<StavkaEvidencijeKursa> listaStavki;
    private int rbrStavke = 0;
    private final String[] kolone = {"RB", "Datum", "Tip Casa", "Cena", "Napomena", "zavrsen"};

    public TableModelStavkaEvidencijeKursa() {
        listaStavki = new ArrayList<>();
    }

    public TableModelStavkaEvidencijeKursa(EvidencijaKursa evidencijakursa) {
        try {
            System.out.println(""+ evidencijakursa.getId() +" "+ evidencijakursa.getStavkeEvidencijeKursa());
            listaStavki = Controller.getInstance().ucitajStavkeEvidencijeKursaIzBaze(evidencijakursa);

        } catch (Exception ex) {
            Logger.getLogger(TableModelStavkaEvidencijeKursa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<StavkaEvidencijeKursa> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(ArrayList<StavkaEvidencijeKursa> listaStavki) {
        this.listaStavki = listaStavki;
    }

    @Override
    public int getRowCount() {
        return listaStavki.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaEvidencijeKursa st = listaStavki.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return st.getDatumPrisustva();
            case 2:
                return st.getTipCasa().getNaziv();
            case 3:
                return st.getTipCasa().getCena();
            case 4:
                return st.getNapomena();
            case 5:
                return st.isZavrsen();

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

    public boolean unetTipCasa(TipCasa tipCasa) {
        for (StavkaEvidencijeKursa stavkaEvidencijeKursa : listaStavki) {
            if (stavkaEvidencijeKursa.getTipCasa().getId() == tipCasa.getId()) {
                return true;
            }
        }
        return false;
    }

    public void dodajStavkuEvidencijeKursa(StavkaEvidencijeKursa sek) {
        rbrStavke = listaStavki.size();
        sek.setRb(++rbrStavke);
        listaStavki.add(sek);
        fireTableDataChanged();
    }

    public void obrisiStavkuEvidencijeKursa(int selected) {
        rbrStavke = 0;
        listaStavki.remove(selected);
        for (StavkaEvidencijeKursa stavkaEvidencijeKursa : listaStavki) {
            stavkaEvidencijeKursa.setRb(++rbrStavke);
        }
        fireTableDataChanged();
    }

    public double getUkupnaCena() {
        double ukupnaCena = 0;
        for (StavkaEvidencijeKursa stavkaEvidencijeKursa : listaStavki) {
            ukupnaCena += stavkaEvidencijeKursa.getTipCasa().getCena();
        }
        return Math.round(ukupnaCena * 100.0) / 100.0;
        //return StavkaEvidencijeKursa.
    }
}