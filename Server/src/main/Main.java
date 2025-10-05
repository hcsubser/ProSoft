/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import java.sql.SQLException;
import thread.ThreadServer;
import view.ServerForm;

/**
 *
 * @author Milan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
       ThreadServer server = new ThreadServer();
       server.start();
       //new ServerForm().setVisible(true);
    }
    
}
