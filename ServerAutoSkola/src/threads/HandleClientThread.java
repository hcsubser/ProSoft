/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import communication.Operation;
import communication.CommPackage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mmmdeb
 */
public class HandleClientThread extends Thread{
    private Socket socket;

    public HandleClientThread(Socket socket) {
        this.socket=socket;
    }

    @Override
    public void run() {
        ObjectInputStream in;
        try {
            while(!socket.isClosed()){
                in = new ObjectInputStream(socket.getInputStream());
                CommPackage request = (CommPackage)in.readObject();
                System.out.println(request.toString());
                System.out.println((String)request.getArgument());
                
                CommPackage response = new CommPackage(Operation.STRING_MESSAGE, "test");
                ObjectOutputStream out= new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
                System.out.println("threads.HandleClientThread.run()");
            }

        } catch (IOException ex) {
            Logger.getLogger(HandleClientThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HandleClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
   
    
}
