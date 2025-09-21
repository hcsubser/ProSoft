/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author mmmdeb
 */
public class Communication {
    
    private static Communication communication;
    private Socket socket;

    public Communication() {
        try {
            socket=new Socket("localhost", 9000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static Communication getInstance(){
        if(communication==null)
            communication = new Communication();
        return communication;
    }
    public Socket getSocket(){
        return socket;
    }
    

}
