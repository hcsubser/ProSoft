/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.tipcasa;

import dbb.DatabaseBroker;
import domain.TipCasa;
import domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Saki
 */
public class SOVratiListuSviTipCasa extends OpstaSistemskaOperacija {

    private ArrayList<TipCasa> list;

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof TipCasa)) {
            throw new Exception("Prosledjeni objekat nije instanca klase TipCasa!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        list = (ArrayList<TipCasa>) (ArrayList<?>) DatabaseBroker.getInstance().select(odo);
    }

    public ArrayList<TipCasa> getList() {
        return list;
    }
}
