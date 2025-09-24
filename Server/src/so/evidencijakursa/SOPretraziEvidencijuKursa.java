/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.evidencijakursa;

import dbb.DatabaseBroker;
import domain.OpstiDomenskiObjekat;
import domain.EvidencijaKursa;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Saki
 */
public class SOPretraziEvidencijuKursa extends OpstaSistemskaOperacija{
     private ArrayList<EvidencijaKursa> lista;
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof EvidencijaKursa)) {
            throw new Exception("Prosleđeni objekat nije instanca klase EvidencijaKursa!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        lista = (ArrayList<EvidencijaKursa>) (ArrayList<?>) DatabaseBroker.getInstance().select(odo);
    }

    public ArrayList<EvidencijaKursa> getLista() {
        return lista;
    }
}
