/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import communication.Operation;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author mmmdeb
 */
public class Controller {
    
    private Object sendRequest(Operation operation, Object data) throws Exception {
        Request request=new Request(operation, data);
        
        ObjectOutputStream out= new ObjectOutputStream(Communication.getInstance().getSocket().getOutputStream());
        out.writeObject(request);
        
        ObjectInputStream in= new ObjectInputStream(Communication.getInstance().getSocket().getInputStream());
        Response response= (Response) in.readObject();
        
        if(response.getResponseType().equals(ResponseType.ERROR)){
            throw response.getException();
        }else{
            return response.getResult();
        }
        
    }
    
}
