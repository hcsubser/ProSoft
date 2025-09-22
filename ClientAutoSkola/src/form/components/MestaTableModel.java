/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form.components;

import communication.Operation;
import controller.Controller;
import domain.Mesto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mmmdeb
 */
public class MestaTableModel extends AbstractTableModel{

    private ArrayList<Mesto> lista;
    private String[] columnNames=new String[]{"Редни број","Назив","Поштански број"};
    private String parametar="";

    public MestaTableModel() {
        try {
            lista=(ArrayList<Mesto>)Controller.getInstance().sendRequest(Operation.VRATI_MESTA, null);
        } catch (Exception ex) {
            Logger.getLogger(MestaTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setListaMesta(ArrayList<Mesto> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }
    
    
    
    @Override
    public int getRowCount() {
        if(lista==null){
            return 0;
        }else{
            return lista.size();
        }
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Mesto obj=lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                return obj.getID();
            case 1:
                return obj.getNaziv();
            case 2:
                return obj.getZipcode();

            default:
                return "n/a";
        }
    }
    
    public Mesto getMesto(int rowIndex) {
        return lista.get(rowIndex);
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista=(ArrayList<Mesto>)Controller.getInstance().sendRequest(Operation.VRATI_MESTA, null);
            if(!parametar.equals("")){
                ArrayList<Mesto> novaLista=new ArrayList<>();
                for (Mesto iter : lista) {
                    if(iter.getID()==Integer.parseInt(parametar) 
                       || iter.getNaziv().toLowerCase().contains(parametar.toLowerCase())
                       || iter.getZipcode()==Integer.parseInt(parametar))
                    {
                        novaLista.add(iter);
                    }
                }
                lista=novaLista;
            }
            fireTableDataChanged();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*public void smanjiKolicinu(int selectedRow) {
        int staraKolicina=lista.get(selectedRow).getKolicina();
        if(staraKolicina==0){
            return;
        }
        int novaKolicina=staraKolicina-1;
        listaKnjiga.get(selectedRow).setKolicina(novaKolicina);
        fireTableDataChanged();
    }

    public void povecajKolicinu(Knjiga knjiga) {
        for (Knjiga k : listaKnjiga) {
            if(k.equals(knjiga)){
                int novaKolicina=knjiga.getKolicina()+1;
                knjiga.setKolicina(novaKolicina);
                fireTableDataChanged();
            }
        }
    }*/
    
}
