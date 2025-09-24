/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package session;

import domain.Instruktor;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author 38169
 */
public class Session {

    private static Session instance;
    private Socket socket;
    private Instruktor ulogovani;

    private Session() {
        try {
            socket = new Socket("localhost", 9000);
        } catch (IOException ex) {
            throw new RuntimeException("Server nije dostupan. Pokrenite server i pokusajte ponovo.");
        }
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setUlogovani(Instruktor ulogovani) {
        this.ulogovani = ulogovani;
    }

    public Instruktor getUlogovani() {
        return ulogovani;
    }

   
}
