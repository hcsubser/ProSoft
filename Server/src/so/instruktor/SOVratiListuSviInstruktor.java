/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.instruktor;

import dbb.DatabaseBroker;
import domain.Instruktor;
import domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Milan
 */
public class SOVratiListuSviInstruktor extends OpstaSistemskaOperacija{
private ArrayList<Instruktor> list;
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
       if(!(odo instanceof Instruktor)) 
            throw new Exception("Prosledjeni objekat nije instanca klase Instruktor!");
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        list= (ArrayList<Instruktor>) (ArrayList<?>)DatabaseBroker.getInstance().select(odo);
    }
     public ArrayList<Instruktor> getList() {
        return list;
    }
}
