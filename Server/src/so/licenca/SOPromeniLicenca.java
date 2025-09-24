/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.licenca;

import dbb.DatabaseBroker;
import domain.OpstiDomenskiObjekat;
import domain.Licenca;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Milan
 */
public class SOPromeniLicenca extends OpstaSistemskaOperacija{

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
       if(!(odo instanceof Licenca))
            throw new Exception("Prosledjeni objekat nije instanca klase Licenca!");
     
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        DatabaseBroker.getInstance().update(odo);
    }
    
}

