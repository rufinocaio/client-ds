package caiofurlan.clientdistributedsystems.system.connection;

import caiofurlan.clientdistributedsystems.models.Segment;
import caiofurlan.clientdistributedsystems.system.utilities.Validators;

public class IsValid {
    public static boolean loginIsValid(String email, String password) throws Exception {
        if (email == null || email.isEmpty()) {
            throw new Exception("E-mail é obrigatório.");
        }
        if (password == null || password.isEmpty()) {
            throw new Exception("Senha é obrigatória.");
        }
        if (!Validators.isEmailValid(email)) {
            throw new Exception("E-mail inválido.");
        }
        if (Validators.isPasswordValid(password)) {
            throw new Exception("Senha deve ter 6 dígitos");
        }
        return true;
    }

    public static boolean editUserIsValid(String name, String email, String password, String type) throws Exception {
        if (name == null || name.isEmpty()) {
            throw  new Exception("Nome é obrigatório");
        }
        if (email == null || email.isEmpty()) {
            throw  new Exception("E-mail é obrigatório");
        }
        if (password == null || password.isEmpty()) {
            throw  new Exception("Senha é obrigatório");
        }
        if (type == null || type.isEmpty()) {
            throw new Exception("Tipo é obrigatório");
        }
        if (!Validators.isEmailValid(email)) {
            throw new Exception("E-mail inválido.");
        }
        if (Validators.isPasswordValid(password)) {
            throw new Exception("Senha deve ter 6 dígitos");
        }
        return true;
    }

    public static boolean editPointIsValid(String name, String obs) throws Exception {
        if (name == null || name.isEmpty()) {
            throw  new Exception("Nome é obrigatório");
        }
        if (obs == null || obs.isEmpty()) {
            throw  new Exception("Observação é obrigatório");
        }
        return true;
    }

    public static boolean editSegmentIsValid(int segmendID, Segment segment) throws Exception {
        if (segmendID == 0) {
            throw  new Exception("ID é obrigatório");
        }
        if (segment == null) {
            throw  new Exception("Segmento é obrigatório");
        }
        return true;
    }

    public static boolean registerUserIsValid(String name, String email, String password, String type) throws Exception {
        if (name == null || name.isEmpty()) {
            throw  new Exception("Nome é obrigatório");
        }


        if (password == null || password.isEmpty()) {
            throw  new Exception("Senha é obrigatório");
        }


        if (Validators.isPasswordValid(password)) {
            throw new Exception("Senha deve ter 6 dígitos");
        }

        if (type == null || type.isEmpty()) {
            throw new Exception("Tipo é obrigatório");
        }
        return true;
    }

    public static boolean deleteUserIsValid(String password) throws Exception {
        if (password == null || password.isEmpty()) {
            throw new Exception("Senha é obrigatório");
        }
        if (Validators.isPasswordValid(password)) {
            throw new Exception("Senha deve ter 6 dígitos");
        }
        return true;
    }

    public static boolean tokenIsValid(String token) throws Exception {
        if (token == null || token.isEmpty()) {
            throw new Exception("Token é obrigatório.");
        }
        return true;
    }


}