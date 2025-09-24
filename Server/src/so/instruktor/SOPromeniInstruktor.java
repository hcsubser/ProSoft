/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.instruktor;

import dbb.DatabaseBroker;
import domain.Instruktor;
import domain.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Saki
 */
public class SOPromeniInstruktor extends OpstaSistemskaOperacija{

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
       if(!(odo instanceof Instruktor))
            throw new Exception("Prosledjeni objekat nije instanca klase Instruktor!");
     
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        DatabaseBroker.getInstance().update(odo);
    }
    
}
