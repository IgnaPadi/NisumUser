package com.nisum.validation;

import java.util.regex.Pattern;

/**
 * Clase común para validar valores con expresiones regulares
 */
public class RegularExpressionValidator {

    private static String emailRegularExpression = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$";
    private static String passwordRegularExpression = "^(?=(?:[^0-9]*[0-9]){2})(?=(?:[^A-Z]*[A-Z]){1})[A-Za-z0-9]{8,12}$";

    public static boolean isEmail(String email){
        return Pattern.matches(emailRegularExpression, email);
    }

    public static boolean isPassword(String password){
        return Pattern.matches(passwordRegularExpression, password);

    }
}
