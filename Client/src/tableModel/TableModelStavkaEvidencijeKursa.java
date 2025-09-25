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
import view.KreirajEvidencijuKursaForma;

/**
 *
 * @author Milan
 */
public class TableModelStavkaEvidencijeKursa extends AbstractTableModel {

    private ArrayList<StavkaEvidencijeKursa> listaStavki;
    private KreirajEvidencijuKursaForma kof;
    private int rbrStavke = 0;
    private final String[] kolone = {"RB", "Datum", "Tip Casa", "Cena", "Napomena", "zavrsen"};

    public TableModelStavkaEvidencijeKursa() {
        listaStavki = new ArrayList<>();
    }

    public TableModelStavkaEvidencijeKursa(EvidencijaKursa evidencijakursa) {
        try {
            System.out.println("MILAN11111");
            System.out.println(""+ evidencijakursa.getId() + evidencijakursa.getStavkeEvidencijeKursa());
            listaStavki = Controller.getInstance().ucitajStavkeEvidencijeKursaIzBaze(evidencijakursa);
                        System.out.println("MILAN2222");

        } catch (Exception ex) {
            Logger.getLogger(TableModelStavkaEvidencijeKursa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public KreirajEvidencijuKursaForma getKof() {
        return kof;
    }

    public void setKof(KreirajEvidencijuKursaForma kof) {
        this.kof = kof;
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

    /*@Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        StavkaEvidencijeKursa st = listaStavki.get(rowIndex);

        if (columnIndex == 1) {

            kof.setUkupnaSa(getUkupnaCenaSaPDV());
            kof.setUkupnaBez(getUkupnaCenaBezPDV());
            kof.setUkupanPopust(getUkupanPopust());
            kof.getTxtUkupanPopust().setText(getUkupanPopust()+"");
            kof.getTxtUkupnoBez().setText(getUkupnaCenaBezPDV()+"");
            kof.getTxtUkupnoSaPDV().setText(getUkupnaCenaSaPDV()+"");
            fireTableRowsUpdated(rowIndex, rowIndex);
        }
    }*/

    public boolean unetTipCasa(TipCasa tipCasa) {
        for (StavkaEvidencijeKursa stavkaEvidencijeKursa : listaStavki) {
            if (stavkaEvidencijeKursa.getTipCasa().getId() == tipCasa.getId()) {
                return true;
            }
        }
        return false;
    }

    public void dodajStavkuEvidencijeKursa(StavkaEvidencijeKursa so) {
        rbrStavke = listaStavki.size();
        so.setRb(++rbrStavke);
        listaStavki.add(so);
        fireTableDataChanged();
    }

    private double izracunajIznos(double datumPrisustva, double cena) {
        return  datumPrisustva * cena;
    }

    public void obrisiStavkuEvidencijeKursa(int selected) {
        rbrStavke = 0;
        listaStavki.remove(selected);
        for (StavkaEvidencijeKursa stavkaEvidencijeKursa : listaStavki) {
            stavkaEvidencijeKursa.setRb(++rbrStavke);
        }
        fireTableDataChanged();
    }

    /*public double getUkupnaCena() {
        double ukupnaCenaSa = 0;
        for (StavkaEvidencijeKursa stavkaEvidencijeKursa : listaStavki) {
            ukupnaCenaSa += stavkaEvidencijeKursa.getIznosSaPDV();
        }
        return Math.round(ukupnaCenaSa * 100.0) / 100.0;
        return StavkaEvidencijeKursa.
    }

    public double getUkupnaCenaBezPDV() {
        double ukupnaCenaBez = 0;
        for (StavkaEvidencijeKursa stavkaEvidencijeKursa : listaStavki) {
            ukupnaCenaBez += stavkaEvidencijeKursa.getIznosBezPDV();
        }
        return Math.round(ukupnaCenaBez * 100.0) / 100.0;
    }

    public double getUkupanPopust() {
        double ukupanPopust = 0;
        for (StavkaEvidencijeKursa stavkaEvidencijeKursa : listaStavki) {
            ukupanPopust += (stavkaEvidencijeKursa.getKolicina() * (stavkaEvidencijeKursa.getTipCasa().getCenaSaPDV() - stavkaEvidencijeKursa.getCenaSaPDV()));
        }
        return Math.round(ukupanPopust * 100.0) / 100.0;
    }*/
}
