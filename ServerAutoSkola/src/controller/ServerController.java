/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Operation;
import communication.CommPackage;
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
    
    Object handlePackage(CommPackage pkg){
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
    
        return new Object();
    }
}
