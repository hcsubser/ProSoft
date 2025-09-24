/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.stavkaevidencijekursa;

import dbb.DatabaseBroker;
import domain.OpstiDomenskiObjekat;
import domain.StavkaEvidencijeKursa;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Milan
 */
public class SOVratiListuSviStavkaEvidencijeKursa extends OpstaSistemskaOperacija {

    private ArrayList<StavkaEvidencijeKursa> lista;

    public ArrayList<StavkaEvidencijeKursa> getLista() {
        return lista;
    }

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof StavkaEvidencijeKursa)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StavkaEvidencijeKursa!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        lista = (ArrayList<StavkaEvidencijeKursa>) (ArrayList<?>) DatabaseBroker.getInstance().select(odo);

    }

}
