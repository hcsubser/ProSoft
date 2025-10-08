/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.TipCasa;
import domain.Instruktor;
import domain.Polaznik;
import domain.Mesto;
import domain.EvidencijaKursa;
import domain.StavkaEvidencijeKursa;
import domain.Licenca;
import java.sql.SQLException;
import java.util.ArrayList;
import so.tipcasa.SOVratiListuSviTipCasa;
import so.instruktor.SOPrijaviInstruktor;
import so.instruktor.SOVratiListuSviInstruktor;
import so.polaznik.SOObrisiPolaznik;
import so.polaznik.SOPromeniPolaznik;
import so.polaznik.SOVratiListuSviPolaznik;
import so.polaznik.SOKreirajPolaznik;
import so.polaznik.SOPretraziPolaznik;
import so.mesto.SOKreirajMesto;
import so.mesto.SOVratiListuSviMesto;
import so.evidencijakursa.SOKreirajEvidencijuKursa;
import so.evidencijakursa.SOObrisiEvidencijuKursa;
import so.evidencijakursa.SOPretraziEvidencijuKursa;
import so.evidencijakursa.SOPromeniEvidencijuKursa;
import so.evidencijakursa.SOVratiListuSvihEvidencijaKursa;
import so.stavkaevidencijekursa.SOVratiListuSviStavkaEvidencijeKursa;
import so.licenca.SOUbaciLicenca;

/**
 *
 * @author Milan
 */
public class ServerController {

    private static ServerController instance;

    public static ServerController getInstance() throws Exception {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    private ServerController() throws SQLException {

    }

    public Instruktor prijaviInstruktora(Instruktor instruktor) throws SQLException, Exception {
        SOPrijaviInstruktor soPrijavi = new SOPrijaviInstruktor();
        soPrijavi.templateExecute(instruktor);
        return soPrijavi.getPrijavljenInstruktor();

    }

    public ArrayList<Instruktor> ucitajInstruktoreIzBaze() throws Exception {
        SOVratiListuSviInstruktor so = new SOVratiListuSviInstruktor();
        so.templateExecute(new Instruktor());
        return so.getList();
    }

    public ArrayList<Polaznik> ucitajPolaznikeIzBaze() throws Exception {
        SOVratiListuSviPolaznik so = new SOVratiListuSviPolaznik();
        so.templateExecute(new Polaznik());
        return so.getList();
    }

    public void promeniPolaznika(Polaznik polaznikChange) throws Exception {
        (new SOPromeniPolaznik()).templateExecute(polaznikChange);
    }

    public void obrisiPolaznika(Polaznik polaznikDelete) throws Exception {
        (new SOObrisiPolaznik()).templateExecute(polaznikDelete);
    }

    public void dodajPolaznika(Polaznik polaznikAdd) throws Exception {
        (new SOKreirajPolaznik()).templateExecute(polaznikAdd);
    }

    public ArrayList<TipCasa> ucitajTipCasaIzBaze() throws Exception {
        SOVratiListuSviTipCasa so = new SOVratiListuSviTipCasa();
        so.templateExecute(new TipCasa());
        return so.getList();
    }

    public ArrayList<Mesto> ucitajMestaIzBaze() throws Exception {
        SOVratiListuSviMesto so = new SOVratiListuSviMesto();
        so.templateExecute(new Mesto());
        return so.getList();
    }

    public void dodajMesto(Mesto mestoAdd) throws Exception {
        (new SOKreirajMesto()).templateExecute(mestoAdd);
    }

    public void kreirajEvidencijuKursa(EvidencijaKursa ekAdd) throws Exception {
        (new SOKreirajEvidencijuKursa()).templateExecute(ekAdd);
    }

    public ArrayList<EvidencijaKursa> ucitajEvidencijeKursaIzBaze() throws Exception {
        SOVratiListuSvihEvidencijaKursa sek = new SOVratiListuSvihEvidencijaKursa();
        sek.templateExecute(new EvidencijaKursa());
        return sek.getList();
    }

    public void promeniEvidencijuKursa(EvidencijaKursa evidencijakursaChange) throws Exception {
        (new SOPromeniEvidencijuKursa()).templateExecute(evidencijakursaChange);
    }

    public void obrisiEvidencijuKursa(EvidencijaKursa evidencijakursaDelete) throws Exception {
        (new SOObrisiEvidencijuKursa()).templateExecute(evidencijakursaDelete);
    }

    public ArrayList<StavkaEvidencijeKursa> ucitajStavkeEvidencijeKursaIzBaze(EvidencijaKursa evidencijakursa) throws Exception {

        SOVratiListuSviStavkaEvidencijeKursa sek = new SOVratiListuSviStavkaEvidencijeKursa();
        StavkaEvidencijeKursa s = new StavkaEvidencijeKursa();

        s.setEvidencijaKursa(evidencijakursa);
        sek.templateExecute(s);
        return sek.getLista();

    }

    public ArrayList<EvidencijaKursa> pretraziEvidencijuKursa(EvidencijaKursa kriterijum) throws Exception {
        SOPretraziEvidencijuKursa so = new SOPretraziEvidencijuKursa();
        so.templateExecute(kriterijum);
        return so.getLista();
    }
  
    public void dodajLicenca(Licenca licenca) throws Exception {
        (new SOUbaciLicenca()).templateExecute(licenca);
    }

    public ArrayList<Polaznik> pretraziPolaznika(Polaznik polaznik) throws Exception {
        SOPretraziPolaznik so = new SOPretraziPolaznik();
        so.templateExecute(polaznik);
        return so.getLista();
    }

}
