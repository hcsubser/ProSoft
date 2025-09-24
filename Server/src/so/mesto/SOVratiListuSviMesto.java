/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.mesto;

import dbb.DatabaseBroker;
import domain.Mesto;
import domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Saki
 */
public class SOVratiListuSviMesto extends OpstaSistemskaOperacija{
private ArrayList<Mesto> list;
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Mesto))
            throw new Exception("Prosledjeni objekat nije instanca klase Mesto!");
    }
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
         list = (ArrayList<Mesto>) (ArrayList<?>) DatabaseBroker.getInstance().select(odo);
    }
    public ArrayList<Mesto> getList() {
        return list;
    }
}
