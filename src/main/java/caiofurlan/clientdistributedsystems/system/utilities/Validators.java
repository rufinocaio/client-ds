package caiofurlan.clientdistributedsystems.system.utilities;

import java.util.regex.Pattern;

public class Validators {
    public static boolean isEmailValid(String email) {
        String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    public static boolean isPasswordValid(String password) {
        return password.length() < 6;
    }
}
