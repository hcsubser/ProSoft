/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import communication.Operation;
import communication.CommPackage;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author mmmdeb
 */
public class Controller {
    
    public Object sendRequest(Operation operation, Object data) throws Exception {
        CommPackage request=new CommPackage(operation, data);
        System.out.println("controller.Controller.sendRequest()");
        ObjectOutputStream out= new ObjectOutputStream(Communication.getInstance().getSocket().getOutputStream());
        out.writeObject(request);
        out.flush();
        System.out.println("controller.Controller.sendRequest()");
        
        ObjectInputStream in= new ObjectInputStream(Communication.getInstance().getSocket().getInputStream());
        CommPackage response= (CommPackage) in.readObject();
        System.out.println("controller.Controller.sendRequest()");
        System.out.println((String)response.getArgument());
        
        /*if(response.getResponseType().equals(ResponseType.ERROR)){
            throw response.getException();
        }else{
            return response.getResult();
        }*/
        return response.getArgument();
    }
    
}
