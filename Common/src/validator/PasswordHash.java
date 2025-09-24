/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validator;

/**
 *
 * @author Saki
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHash {

    public static String hashPassword(String password) {
        try {
            
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            
            byte[] hashBytes = md.digest(password.getBytes());

            
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Greška pri heširanju lozinke: " + e.getMessage(), e);
        }
    }
}


