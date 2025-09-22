
import java.io.IOException;
import threads.ServerThread;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mmmdeb
 */
public class TestMain {
    
    public static void main(String[] args) throws IOException {
        System.out.println("TestMain.main()");
        ServerThread st = new ServerThread();
        st.start();
        System.out.println("pokrenut serverthread");
    }
}
