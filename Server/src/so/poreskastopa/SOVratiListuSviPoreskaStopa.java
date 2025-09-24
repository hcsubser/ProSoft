/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.poreskastopa;

import dbb.DatabaseBroker;
import domain.OpstiDomenskiObjekat;
import domain.PoreskaStopa;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Saki
 */
public class SOVratiListuSviPoreskaStopa extends OpstaSistemskaOperacija{
    private ArrayList<PoreskaStopa> list;
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof PoreskaStopa))
            throw new Exception("Prosledjeni objekat nije instanca klase PoreskaStopa!");
    }
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
         list = (ArrayList<PoreskaStopa>) (ArrayList<?>) DatabaseBroker.getInstance().select(odo);
    }
    public ArrayList<PoreskaStopa> getList() {
        return list;
    }
}
