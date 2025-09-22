/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Operation;
import communication.CommPackage;
import dbbroker.DBBroker;
import domain.AbstractDomainObject;
import domain.Mesto;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author mmmdeb
 */
public class ServerController {
    static ServerController serverController;
    
    public static ServerController getInstance(){
        if(serverController==null)
            serverController = new ServerController();
        return serverController;
    }
    
    public Object handlePackage(CommPackage pkg){
        Object obj = new Object();
        switch (pkg.getOperation()) {
                case Operation.ADD_POLAZNIK:
                    System.out.println("nije impelentriano controller.ServerController.handlePackage()");
                    break;
                case Operation.VRATI_MESTA:
                    obj = vratiSvaMesta();
                    break;
                case Operation.REMOVE_POLAZNIK:
                    break;
                case Operation.STRING_MESSAGE:
                    break;
                default:
                    System.out.println("controller.ServerController.handlePackage()");
                    return null;
        }
        return obj;
    }
    
    Object vratiSvaMesta(){
        try {
            Mesto mesto = new Mesto(0,null,0);
            Object array;
            array = DBBroker.getInstance().select(mesto);
            return array;
        } catch (SQLException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
