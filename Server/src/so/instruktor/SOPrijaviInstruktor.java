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
 * @author Saki
 */
public class SOPrijaviInstruktor extends OpstaSistemskaOperacija{
    private Instruktor prijavljenInstruktor;

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if(!(odo instanceof Instruktor))
            throw  new Exception("Prosledjeni objekat nije instanca klase Instruktor!");
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        Instruktor c= (Instruktor) odo;
        ArrayList<Instruktor> listaInstruktora= (ArrayList<Instruktor>) (ArrayList<?>)
                DatabaseBroker.getInstance().select(odo);
        for (Instruktor instruktor : listaInstruktora) {
            if(instruktor.getKorisnickoIme().equals(c.getKorisnickoIme()) &&
                    instruktor.getLozinka().equals(c.getLozinka())){
                prijavljenInstruktor=instruktor;
                return;
            } 
        }
        
        throw new Exception("Korisnicko ime i sifra nisu ispravni!");
    }

    public Instruktor getPrijavljenInstruktor() {
        return prijavljenInstruktor;
    }

    
    
}
