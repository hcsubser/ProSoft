/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.evidencijakursa;

import dbb.DatabaseBroker;
import domain.OpstiDomenskiObjekat;
import domain.EvidencijaKursa;
import domain.StavkaEvidencijeKursa;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Saki
 */
public class SOPromeniEvidencijuKursa extends OpstaSistemskaOperacija {

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof EvidencijaKursa)) {
            throw new Exception("Prosledjeni objekat nije instanca klase EvidencijaKursa !");
        }

        EvidencijaKursa evidencijakursa = (EvidencijaKursa) odo;

        if (evidencijakursa.getStavkeEvidencijeKursa().isEmpty()) {
            throw new Exception("EvidencijaKursa mora imati stavke!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        DatabaseBroker.getInstance().update(odo);
        EvidencijaKursa evidencijakursa = (EvidencijaKursa) odo;
        DatabaseBroker.getInstance().delete(evidencijakursa.getStavkeEvidencijeKursa().get(0));

        for (StavkaEvidencijeKursa stavkaEvidencijeKursa : evidencijakursa.getStavkeEvidencijeKursa()) {
            DatabaseBroker.getInstance().insert(stavkaEvidencijeKursa);
        }
    }

}
