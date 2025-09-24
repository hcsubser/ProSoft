/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.polaznik;

import dbb.DatabaseBroker;
import domain.Polaznik;
import domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Saki
 */
public class SOVratiListuSviPolaznik extends OpstaSistemskaOperacija{
private ArrayList<Polaznik> list;
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
       if(!(odo instanceof Polaznik)) 
            throw new Exception("Prosledjeni objekat nije instanca klase Polaznik!");
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        list= (ArrayList<Polaznik>) (ArrayList<?>)DatabaseBroker.getInstance().select(odo);
    }
     public ArrayList<Polaznik> getList() {
        return list;
    }
}
