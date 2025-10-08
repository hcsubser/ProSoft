/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import controller.ServerController;
import domain.Instruktor;
import domain.Polaznik;
import domain.Mesto;
import domain.EvidencijaKursa;
import domain.Licenca;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author Milan
 */
public class ThreadClient extends Thread {

    private Socket socket;
    private Instruktor ulogovaniKorisnik;

    public ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                Response response = handleRequest(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (SocketException e) {
            System.out.println("Klijent se odvezao: " + e.getMessage());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Greska prilikom obrade klijentskog zahteva: " + e.getMessage());
        } finally {
            try {
                if (ulogovaniKorisnik != null) {
                    ThreadServer.ukloniAktivnogKorisnika(ulogovaniKorisnik.getKorisnickoIme());
                }
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
                case Operation.PRIJAVI_INSTRUKTORA:
                    Instruktor zahtevani = (Instruktor) request.getData();
                    Instruktor prijavljeni = ServerController.getInstance().prijaviInstruktora(zahtevani);
                    if (!ThreadServer.dodajAktivnogKorisnika(zahtevani.getKorisnickoIme())) {//ako postoji vec aktivan korisnik
                        response.setResponseStatus(ResponseStatus.Error);
                        response.setException(new Exception("Korisnik je vec ulogovan na drugom klijentu!"));
                        break;
                    }
                    ulogovaniKorisnik = prijavljeni;
                    response.setData(prijavljeni);
                    break;
                case Operation.KREIRAJ_EVIDENCIJU_KURSA:
                    ServerController.getInstance().kreirajEvidencijuKursa((EvidencijaKursa) request.getData());
                    break;
                case Operation.PROMENI_POLAZNIKA:
                    ServerController.getInstance().promeniPolaznika((Polaznik) request.getData());
                    break;
                case Operation.OBRISI_POLAZNIKA:
                    ServerController.getInstance().obrisiPolaznika((Polaznik) request.getData());
                    break;
                case Operation.VRATI_LISTU_SVI_POLAZNICI:
                    response.setData(ServerController.getInstance().ucitajPolaznikeIzBaze());
                    break;
                case Operation.VRATI_LISTU_SVI_TIPCASA:
                    response.setData(ServerController.getInstance().ucitajTipCasaIzBaze());
                    break;
                case Operation.VRATI_LISTU_SVI_INSTRUKTOR:
                    response.setData(ServerController.getInstance().ucitajInstruktoreIzBaze());
                    break;
                case Operation.KREIRAJ_POLAZNIKA:
                    ServerController.getInstance().dodajPolaznika((Polaznik) request.getData());
                    break;
                case Operation.KREIRAJ_MESTO:
                    ServerController.getInstance().dodajMesto((Mesto) request.getData());
                    break;
                case Operation.VRATI_LISTU_SVI_MESTO:
                    response.setData(ServerController.getInstance().ucitajMestaIzBaze());
                    break;
                case Operation.PROMENI_EVIDENCIJU_KURSA:
                    ServerController.getInstance().promeniEvidencijuKursa((EvidencijaKursa) request.getData());
                    break;
                case Operation.OBRISI_EVIDENCIJU_KURSA:
                    ServerController.getInstance().obrisiEvidencijuKursa((EvidencijaKursa) request.getData());
                    break;
                case Operation.VRATI_LISTU_SVI_EVIDENCIJA_KURSA:
                    response.setData(ServerController.getInstance().ucitajEvidencijeKursaIzBaze());
                    break;
                case Operation.PRETRAZI_EVIDENCIJU_KURSA:
                    response.setData(ServerController.getInstance().pretraziEvidencijuKursa((EvidencijaKursa) request.getData()));
                    break;
                case Operation.VRATI_LISTU_STAVKI_EVIDENCIJE_KURSA:
                    response.setData(ServerController.getInstance().ucitajStavkeEvidencijeKursaIzBaze((EvidencijaKursa) request.getData()));
                    break;
                case Operation.DODAJ_LICENCA:
                    ServerController.getInstance().dodajLicenca((Licenca) request.getData());
                    break;
                case Operation.PRETRAZI_POLAZNIKA:
                    response.setData(ServerController.getInstance().pretraziPolaznika((Polaznik) request.getData()));
                    break;
                case Operation.ODJAVA_INSTRUKTORA:
                    String korisnickoIme = (String) request.getData();
                    ThreadServer.ukloniAktivnogKorisnika(korisnickoIme);
                    ulogovaniKorisnik = null;
                    break;
                default:
                    return null;
            }
        } catch (Exception e) {
            response.setException(e);
            response.setResponseStatus(ResponseStatus.Error);
        }
        return response;
    }
}
