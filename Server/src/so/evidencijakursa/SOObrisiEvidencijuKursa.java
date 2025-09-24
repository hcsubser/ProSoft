/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.evidencijakursa;

import dbb.DatabaseBroker;
import domain.OpstiDomenskiObjekat;
import domain.EvidencijaKursa;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Saki
 */
public class SOObrisiEvidencijuKursa extends OpstaSistemskaOperacija{
     @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if(!(odo instanceof EvidencijaKursa))
            throw new Exception("Prosledjeni objekat nije instanca klase EvidencijaKursa!");

    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
          DatabaseBroker.getInstance().delete(odo);
    }
    
}
