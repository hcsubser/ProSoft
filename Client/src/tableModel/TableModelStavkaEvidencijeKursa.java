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
    private final String[] kolone = {"RB", "Kolicina", "TipCasa", "Cena Bez PDV",
        "Cena Sa PDV", "Iznos Bez PDV", "Iznos Sa PDV", "Napomena"};

    public TableModelStavkaEvidencijeKursa() {
        listaStavki = new ArrayList<>();
    }

    public TableModelStavkaEvidencijeKursa(EvidencijaKursa evidencijakursa) {
        try {
            listaStavki = Controller.getInstance().ucitajStavkeEvidencijeKursaIzBaze(evidencijakursa);
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
                return st.getKolicina();
            case 2:
                return st.getTipCasa().getNaziv();
            case 3:
                return st.getCenaBezPDV();
            case 4:
                return st.getCenaSaPDV();
            case 5:
                return st.getIznosBezPDV();
            case 6:
                return st.getIznosSaPDV();
            case 7:
                return st.getNapomena();
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

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        StavkaEvidencijeKursa st = listaStavki.get(rowIndex);

        if (columnIndex == 1) {
            st.setKolicina(Integer.parseInt((String) value));
            st.setCenaBezPDV(Math.floor(st.getTipCasa().getCenaBezPDV() * (1 - st.getTipCasa().getPopust() / 100) * 100.0) / 100.0);
            st.setCenaSaPDV(Math.floor( (st.getTipCasa().getCenaBezPDV() * (1 - st.getTipCasa().getPopust() / 100)) * (1 + st.getTipCasa().getPoreskaStopa().getVrednost() / 100) * 100.0 ) / 100.0);
            st.setIznosBezPDV(izracunajIznos(st.getKolicina(), st.getCenaBezPDV()));
            st.setIznosSaPDV(izracunajIznos(st.getKolicina(), st.getCenaSaPDV()));
            kof.setUkupnaSa(getUkupnaCenaSaPDV());
            kof.setUkupnaBez(getUkupnaCenaBezPDV());
            kof.setUkupanPopust(getUkupanPopust());
            kof.getTxtUkupanPopust().setText(getUkupanPopust()+"");
            kof.getTxtUkupnoBez().setText(getUkupnaCenaBezPDV()+"");
            kof.getTxtUkupnoSaPDV().setText(getUkupnaCenaSaPDV()+"");
            fireTableRowsUpdated(rowIndex, rowIndex);
        }
    }

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
        so.setIznosBezPDV(izracunajIznos(so.getKolicina(), so.getCenaBezPDV()));
        so.setIznosSaPDV(izracunajIznos(so.getKolicina(), so.getCenaSaPDV()));
        listaStavki.add(so);
        fireTableDataChanged();
    }

    private double izracunajIznos(double kolicina, double cena) {
        return  kolicina * cena;
    }

    public void obrisiStavkuEvidencijeKursa(int selected) {
        rbrStavke = 0;
        listaStavki.remove(selected);
        for (StavkaEvidencijeKursa stavkaEvidencijeKursa : listaStavki) {
            stavkaEvidencijeKursa.setRb(++rbrStavke);
        }
        fireTableDataChanged();
    }

    public double getUkupnaCenaSaPDV() {
        double ukupnaCenaSa = 0;
        for (StavkaEvidencijeKursa stavkaEvidencijeKursa : listaStavki) {
            ukupnaCenaSa += stavkaEvidencijeKursa.getIznosSaPDV();
        }
        return Math.round(ukupnaCenaSa * 100.0) / 100.0;
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
    }
}
