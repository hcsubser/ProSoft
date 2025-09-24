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
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Milan
 */
public class SOKreirajEvidencijuKursa extends OpstaSistemskaOperacija {

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof EvidencijaKursa)) {
            throw new Exception("Prosledjeni objekat nije instanca klase EvidencijaKursa!");
        }

        EvidencijaKursa evidencijakursa = (EvidencijaKursa) odo;

        //if (evidencijakursa.getStavkeEvidencijeKursa().isEmpty()) {
        //    throw new Exception("EvidencijaKursa mora imati stavke!");
        // }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        try {
            EvidencijaKursa evidencijakursa = (EvidencijaKursa) odo;
            PreparedStatement ps = DatabaseBroker.getInstance().insert(odo);
            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            int pID = keys.getInt(1);
            System.out.println("kljuc evidencijekursa: " + pID);
            evidencijakursa.setId(pID);

            for (StavkaEvidencijeKursa stavkaEvidencijeKursa : evidencijakursa.getStavkeEvidencijeKursa()) {
                stavkaEvidencijeKursa.setEvidencijaKursa(evidencijakursa);
                DatabaseBroker.getInstance().insert(stavkaEvidencijeKursa);
            }
        } catch (Exception e) {
            System.err.println("GRESKA U INSERT EvidencijaKursa: " + e.getMessage());
            throw e;
        }

    }
}
