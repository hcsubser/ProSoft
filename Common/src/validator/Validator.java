/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validator;

import domain.Instruktor;

/**
 *
 * @author Milan
 */
public class Validator {

    private static final String NAME_REGEX= "^[a-zA-Z ]+$";
    private static final String USERNAME_REGEX="^[a-zA-Z0-9]+$";
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final String NUMBER_REGEX = "^\\d+(\\.\\d+)?$";
    private static final String DATE_REGEX = "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
    public static boolean isValidDate(String date) {
        return date.matches(DATE_REGEX);
    }
    public static boolean isValidUsername(String username){
        return username!=null && username.matches(USERNAME_REGEX);
    }
    public static boolean isValidName(String name) {
        return name != null && name.matches(NAME_REGEX);
    }
    public static boolean isValidPassword(String password){
        return password != null && password.length() >= MIN_PASSWORD_LENGTH;
    }
    public static boolean isValidNumber(String number) {
        return number != null && number.matches(NUMBER_REGEX);
    }
    public static void validateInstruktorAdd(Instruktor instruktor) throws IllegalArgumentException {
        if (!isValidName(instruktor.getIme())) {
            throw new IllegalArgumentException("Ime nije validno. Dozvoljena su samo slova.");
        }
        if (!isValidName(instruktor.getPrezime())) {
            throw new IllegalArgumentException("Prezime nije validno. Dozvoljena su samo slova.");
        }
        if (!isValidPassword(instruktor.getLozinka())) {
            throw new IllegalArgumentException("Lozinka mora imati najmanje " + MIN_PASSWORD_LENGTH + " karaktera.");
        }
        if (!isValidUsername(String.valueOf(instruktor.getKorisnickoIme()))) {
            throw new IllegalArgumentException("Los format korisnickog imena.");
        }
    }
    public static void validateInstruktorChange(Instruktor instruktor) throws IllegalArgumentException {
        if (!isValidName(instruktor.getIme())) {
            throw new IllegalArgumentException("Ime nije validno. Dozvoljena su samo slova.");
        }
        if (!isValidName(instruktor.getPrezime())) {
            throw new IllegalArgumentException("Prezime nije validno. Dozvoljena su samo slova.");
        }
        if (!isValidUsername(String.valueOf(instruktor.getKorisnickoIme()))) {
            throw new IllegalArgumentException("Los format korisnickog imena.");
        }
    }
    public static void validateInstruktorSign(Instruktor instruktor) throws IllegalArgumentException {
        
        if (!isValidPassword(instruktor.getLozinka())) {
            throw new IllegalArgumentException("Lozinka mora imati najmanje " + MIN_PASSWORD_LENGTH + " karaktera.");
        }
        if (!isValidUsername(String.valueOf(instruktor.getKorisnickoIme()))) {
            throw new IllegalArgumentException("Los format korisnickog imena.");
        }
    }
}
