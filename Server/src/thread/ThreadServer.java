/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saki
 */
public class ThreadServer extends Thread {

    private ServerSocket serverSocket;
    private static final Set<String> aktivniKorisnici = new HashSet<>();

    public ThreadServer() {
        try {
            serverSocket = new ServerSocket(9000);
        } catch (BindException ex) {
            System.err.println("Port se vec koristi. Samo jedan server se moze pokrenuti");
        } catch (IOException ex) {
           // Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static Set<String> getAktivniKorisnici() {
        return aktivniKorisnici;
    }

    @Override
    public void run() {

        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("Klijent se povezao!");
                ThreadClient th = new ThreadClient(socket);
                th.start();
            }
        } catch (IOException ex) {
            //Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Server ugasen: " + ex.getMessage());
        }

    }

    public static synchronized boolean dodajAktivnogKorisnika(String korisnickoIme) {
        if (aktivniKorisnici.contains(korisnickoIme)) {
            return false; 
        }
        aktivniKorisnici.add(korisnickoIme);
        return true;
    }

    public static synchronized void ukloniAktivnogKorisnika(String korisnickoIme) {
        aktivniKorisnici.remove(korisnickoIme);
    }

}
