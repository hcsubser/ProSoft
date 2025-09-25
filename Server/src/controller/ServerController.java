/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import validator.PasswordHash;
import domain.TipCasa;
import domain.Instruktor;
import domain.Polaznik;
import domain.Mesto;
import domain.EvidencijaKursa;
import domain.PoreskaStopa;
import domain.StavkaEvidencijeKursa;
import domain.Licenca;
import java.sql.SQLException;
import java.util.ArrayList;
import so.tipcasa.SOKreirajTipCasa;
import so.tipcasa.SOObrisiTipCasa;
import so.tipcasa.SOPromeniTipCasa;
import so.tipcasa.SOVratiListuSviTipCasa;
import so.instruktor.SOKreirajInstruktor;
import so.instruktor.SOObrisiInstruktor;
import so.instruktor.SOPrijaviInstruktor;
import so.instruktor.SOPromeniInstruktor;
import so.instruktor.SOVratiListuSviInstruktor;
import so.polaznik.SOObrisiPolaznik;
import so.polaznik.SOPromeniPolaznik;
import so.polaznik.SOVratiListuSviPolaznik;
import so.polaznik.SOKreirajPolaznik;
import so.polaznik.SOPretraziPolaznik;
import so.mesto.SOKreirajMesto;
import so.mesto.SOObrisiMesto;
import so.mesto.SOPromeniMesto;
import so.mesto.SOVratiListuSviMesto;
import so.evidencijakursa.SOKreirajEvidencijuKursa;
import so.evidencijakursa.SOObrisiEvidencijuKursa;
import so.evidencijakursa.SOPretraziEvidencijuKursa;
import so.evidencijakursa.SOPromeniEvidencijuKursa;
import so.evidencijakursa.SOVratiListuSvihEvidencijaKursa;
import so.poreskastopa.SOVratiListuSviPoreskaStopa;
import so.stavkaevidencijekursa.SOVratiListuSviStavkaEvidencijeKursa;
import so.licenca.SOObrisiLicenca;
import so.licenca.SOPromeniLicenca;
import so.licenca.SOUbaciLicenca;
import so.licenca.SOVratiListuSviLicenca;

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
        String hash = PasswordHash.hashPassword(instruktor.getLozinka());
        instruktor.setLozinka(hash);
        soPrijavi.templateExecute(instruktor);
        return soPrijavi.getPrijavljenInstruktor();

    }

    public void dodajInstruktora(Instruktor instruktorAdd) throws Exception {
        String hash = PasswordHash.hashPassword(instruktorAdd.getLozinka());
        instruktorAdd.setLozinka(hash);
        (new SOKreirajInstruktor()).templateExecute(instruktorAdd);

    }

    public void promeniInstruktora(Instruktor instruktorChange) throws Exception {
        (new SOPromeniInstruktor()).templateExecute(instruktorChange);
    }

    public ArrayList<Instruktor> ucitajInstruktoreIzBaze() throws Exception {
        SOVratiListuSviInstruktor so = new SOVratiListuSviInstruktor();
        so.templateExecute(new Instruktor());
        return so.getList();
    }

    public void obrisiInstruktora(Instruktor instruktorDelete) throws Exception {
        (new SOObrisiInstruktor()).templateExecute(instruktorDelete);
    }

    public ArrayList<Polaznik> ucitajKupceIzBaze() throws Exception {
        SOVratiListuSviPolaznik so = new SOVratiListuSviPolaznik();
        so.templateExecute(new Polaznik());
        return so.getList();
    }

    public void promeniKupca(Polaznik polaznikChange) throws Exception {
        (new SOPromeniPolaznik()).templateExecute(polaznikChange);
    }

    public void obrisiKupca(Polaznik polaznikDelete) throws Exception {
        (new SOObrisiPolaznik()).templateExecute(polaznikDelete);
    }

    public void dodajKupca(Polaznik polaznikAdd) throws Exception {
        (new SOKreirajPolaznik()).templateExecute(polaznikAdd);
    }

    public ArrayList<TipCasa> ucitajTipCasaeIzBaze() throws Exception {
        SOVratiListuSviTipCasa so = new SOVratiListuSviTipCasa();
        so.templateExecute(new TipCasa());
        return so.getList();
    }

    public void promeniTipCasa(TipCasa tipCasaChange) throws Exception {
        (new SOPromeniTipCasa()).templateExecute(tipCasaChange);
    }

    public void obrisiTipCasa(TipCasa tipCasaDelete) throws Exception {
        (new SOObrisiTipCasa()).templateExecute(tipCasaDelete);
    }

    public void dodajTipCasa(TipCasa tipCasaAdd) throws Exception {
        (new SOKreirajTipCasa()).templateExecute(tipCasaAdd);
    }

    public ArrayList<Mesto> ucitajMestaIzBaze() throws Exception {
        SOVratiListuSviMesto so = new SOVratiListuSviMesto();
        so.templateExecute(new Mesto());
        return so.getList();
    }

    public void promeniMesto(Mesto mestoChange) throws Exception {
        (new SOPromeniMesto()).templateExecute(mestoChange);
    }

    public void obrisiMesto(Mesto mestoDelete) throws Exception {
        (new SOObrisiMesto()).templateExecute(mestoDelete);
    }

    public void dodajMesto(Mesto mestoAdd) throws Exception {
        (new SOKreirajMesto()).templateExecute(mestoAdd);
    }

    public void kreirajEvidencijuKursa(EvidencijaKursa otpAdd) throws Exception {
        (new SOKreirajEvidencijuKursa()).templateExecute(otpAdd);
    }

    public ArrayList<EvidencijaKursa> ucitajEvidencijeKursaIzBaze() throws Exception {
        SOVratiListuSvihEvidencijaKursa so = new SOVratiListuSvihEvidencijaKursa();
        so.templateExecute(new EvidencijaKursa());
        return so.getList();
    }

    public void promeniEvidencijuKursa(EvidencijaKursa evidencijakursaChange) throws Exception {
        (new SOPromeniEvidencijuKursa()).templateExecute(evidencijakursaChange);
    }

    public void ubaciLicencu(Licenca licencaAdd) throws Exception {
        (new SOUbaciLicenca()).templateExecute(licencaAdd);
    }

    public void promeniLicencu(Licenca licencaChange) throws Exception {
        (new SOPromeniLicenca()).templateExecute(licencaChange);
    }

    public ArrayList<Licenca> ucitajLicenceIzBaze() throws Exception {
        SOVratiListuSviLicenca so = new SOVratiListuSviLicenca();
        so.templateExecute(new Licenca());
        return so.getList();
    }

    public void obrisiLicenca(Licenca licencaDelete) throws Exception {
        (new SOObrisiLicenca()).templateExecute(licencaDelete);
    }

    public void obrisiEvidencijuKursa(EvidencijaKursa evidencijakursaDelete) throws Exception {
        (new SOObrisiEvidencijuKursa()).templateExecute(evidencijakursaDelete);
    }

    public ArrayList<StavkaEvidencijeKursa> ucitajStavkeEvidencijeKursaIzBaze(EvidencijaKursa evidencijakursa) throws Exception {//mozda promenim na Arraylist posle
                   System.out.println("MILAN56666");

        SOVratiListuSviStavkaEvidencijeKursa so = new SOVratiListuSviStavkaEvidencijeKursa();
        StavkaEvidencijeKursa s = new StavkaEvidencijeKursa();
                    System.out.println("MILAN7777" + s);

        s.setEvidencijaKursa(evidencijakursa);
        so.templateExecute(s);
        return so.getLista();

    }

    public ArrayList<EvidencijaKursa> pretraziEvidencijuKursa(EvidencijaKursa kriterijum) throws Exception {
        SOPretraziEvidencijuKursa so = new SOPretraziEvidencijuKursa();
        so.templateExecute(kriterijum);
        return so.getLista();
    }

    public ArrayList<PoreskaStopa> ucitajPoreskaStopeIzBaze() throws Exception {
        SOVratiListuSviPoreskaStopa so = new SOVratiListuSviPoreskaStopa();
        so.templateExecute(new PoreskaStopa());
        return so.getList();
    }

    public void dodajLicenca(Licenca licenca) throws Exception {
        (new SOUbaciLicenca()).templateExecute(licenca);
    }

    public ArrayList<Polaznik> pretraziKupca(Polaznik polaznik) throws Exception {
        SOPretraziPolaznik so = new SOPretraziPolaznik();
        so.templateExecute(polaznik);
        return so.getLista();
    }

}
