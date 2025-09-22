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
import so.mesto.AddMesto;
import so.mesto.GetAllMesto;
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
    
    public Object handlePackage(CommPackage pkg) throws Exception{
        Object obj = new Object();
        switch (pkg.getOperation()) {
                case Operation.ADD_POLAZNIK:
                    System.out.println("nije impelentriano controller.ServerController.handlePackage()");
                    break;
                case Operation.VRATI_MESTA:
                    obj = vratiSvaMesta();
                    break;
                case Operation.DODAJ_MESTO:
                    dodajMesto((AbstractDomainObject)pkg.getArgument());
                    break;
                case Operation.REMOVE_POLAZNIK:
                    break;
                case Operation.STRING_MESSAGE:
                    break;
                default:
                    System.out.println("controller.ServerController.handlePackage()");
                    return null;
        }
        return new CommPackage(Operation.SUCCESS,obj);
    }
    
    Object vratiSvaMesta() throws Exception{
            GetAllMesto mesta = new GetAllMesto();
            mesta.templateExecute(new Mesto(0,null,0));
           // mesta.
            return mesta.vratiSvaMesta();
    }
    
    void dodajMesto(AbstractDomainObject ado) {
        try {
            AddMesto addm = new AddMesto();
            addm.templateExecute(ado);
            
        } catch (Exception ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
