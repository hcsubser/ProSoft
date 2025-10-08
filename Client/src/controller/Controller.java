/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.TipCasa;
import java.io.IOException;
import domain.Instruktor;
import domain.Polaznik;
import domain.Mesto;
import domain.EvidencijaKursa;
import domain.StavkaEvidencijeKursa;
import domain.Licenca;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author Milan
 */
public class Controller {

    private static Controller instance; //jedna, jedina instanca controllera u mojoj aplikaciji

    public static Controller getInstance() throws Exception {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    private Controller() throws IOException {

    }

    private Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();

        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getException();
        } else {
            return response.getData();
        }

    }

    public Instruktor prijaviInstruktora(Instruktor instruktor) throws Exception {
        return (Instruktor) sendRequest(Operation.PRIJAVI_INSTRUKTORA, instruktor);

    }

    public void dodajInstruktora(Instruktor c) throws Exception {
        sendRequest(Operation.DODAJ_INSTRUKTORA, c);
    }

    public void promeniInstruktora(Instruktor instruktor) throws Exception {
        sendRequest(Operation.PROMENI_INSTRUKTORA, instruktor);
    }

    public ArrayList<Instruktor> ucitajInstruktoreIzBaze() throws Exception {
        return (ArrayList<Instruktor>) sendRequest(Operation.VRATI_LISTU_SVI_INSTRUKTOR, null);
    }

    public void obrisiInstruktora(Instruktor instruktorDelete) throws Exception {
        sendRequest(Operation.OBRISI_INSTRUKTORA, instruktorDelete);
    }

    public void obrisiLicenca(Licenca licencaDelete) throws Exception {
        sendRequest(Operation.OBRISI_LICENCA, licencaDelete);
    }

    public void dodajEvidencijuKursa(EvidencijaKursa evidencijakursa) throws Exception {
        sendRequest(Operation.KREIRAJ_EVIDENCIJU_KURSA, evidencijakursa);
    }

    public ArrayList<Polaznik> ucitajPolaznikeIzBaze() throws Exception {
        return (ArrayList<Polaznik>) sendRequest(Operation.VRATI_LISTU_SVI_POLAZNICI, null);

    }

    public ArrayList<TipCasa> ucitajTipCasaeIzBaze() throws Exception {
        return (ArrayList<TipCasa>) sendRequest(Operation.VRATI_LISTU_SVI_TIPCASA, null);
    }

    public void odjaviInstruktora(String korisnickoIme) throws Exception {
        sendRequest(Operation.ODJAVA_INSTRUKTORA, korisnickoIme);
    }

    public void ubaciLicenca(Licenca strs) throws Exception {
        sendRequest(Operation.DODAJ_LICENCA, strs);
    }

    public void promeniLicencu(Licenca ss) throws Exception {
        sendRequest(Operation.PROMENI_LICENCA, ss);
    }

    public ArrayList<Licenca> ucitajLicenceIzBaze() throws Exception {
        return (ArrayList<Licenca>) sendRequest(Operation.VRATI_LISTU_SVI_LICENCA, null);
    }

    public void dodajPolaznika(Polaznik k) throws Exception {
        sendRequest(Operation.KREIRAJ_POLAZNIKA, k);
    }

    public void izmeniPolaznika(Polaznik k) throws Exception {
        sendRequest(Operation.PROMENI_POLAZNIKA, k);
    }

    public ArrayList<Mesto> ucitajMestaIzBaze() throws Exception {
        return (ArrayList<Mesto>) sendRequest(Operation.VRATI_LISTU_SVI_MESTO, null);
    }

    public void dodajMesto(Mesto m) throws Exception {
        sendRequest(Operation.KREIRAJ_MESTO, m);
    }

    public void obrisiMesto(Mesto mestoDelete) throws Exception {
        sendRequest(Operation.OBRISI_MESTO, mestoDelete);

    }

    public void promeniMesto(Mesto mestoChange) throws Exception {
        sendRequest(Operation.PROMENI_MESTO, mestoChange);
    }

    public void obrisiPolaznika(Polaznik polaznikDelete) throws Exception {
        sendRequest(Operation.OBRISI_POLAZNIKA, polaznikDelete);
    }

    public void dodajTipCasa(TipCasa tipCasaAdd) throws Exception {
        sendRequest(Operation.KREIRAJ_TIPCASA, tipCasaAdd);
    }

    public void promeniTipCasa(TipCasa tipCasaChange) throws Exception {
        sendRequest(Operation.PROMENI_TIPCASA, tipCasaChange);

    }

    public void obrisiTipCasa(TipCasa tipCasaDelete) throws Exception {
        sendRequest(Operation.OBRISI_TIPCASA, tipCasaDelete);
    }

    public ArrayList<EvidencijaKursa> ucitajEvidencijeKursaIzBaze() throws Exception {
        return (ArrayList<EvidencijaKursa>) sendRequest(Operation.VRATI_LISTU_SVI_EVIDENCIJA_KURSA, null);
    }

    public ArrayList<StavkaEvidencijeKursa> ucitajStavkeEvidencijeKursaIzBaze(EvidencijaKursa evidencijakursa) throws Exception {
        return (ArrayList<StavkaEvidencijeKursa>) sendRequest(Operation.VRATI_LISTU_STAVKI_EVIDENCIJE_KURSA, evidencijakursa);
    }

    public void obrisiEvidencijuKursa(EvidencijaKursa evidencijakursaDelete) throws Exception {
        sendRequest(Operation.OBRISI_EVIDENCIJU_KURSA, evidencijakursaDelete);

    }

    public ArrayList<EvidencijaKursa> pretraziEvidencijeKursa(EvidencijaKursa kriterijum) throws Exception {
        return (ArrayList<EvidencijaKursa>) sendRequest(Operation.PRETRAZI_EVIDENCIJU_KURSA, kriterijum);
    }

    public void promeniEvidencijuKursa(EvidencijaKursa evidencijakursaChange) throws Exception {
        sendRequest(Operation.PROMENI_EVIDENCIJU_KURSA, evidencijakursaChange);

    }

    public ArrayList<Polaznik> pretraziPolaznike(Polaznik filter) throws Exception {
        return (ArrayList<Polaznik>) sendRequest(Operation.PRETRAZI_POLAZNIKA, filter);
    }
}
