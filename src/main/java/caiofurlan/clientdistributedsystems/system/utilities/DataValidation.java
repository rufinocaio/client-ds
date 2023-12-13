package caiofurlan.clientdistributedsystems.system.utilities;

import caiofurlan.clientdistributedsystems.models.Segment;

import java.util.regex.Pattern;

public class DataValidation {

    // Common
    public static boolean loginValidation(String email, String password) throws Exception {
        if (email == null || email.isEmpty()) {
            throw new Exception("E-mail é obrigatório.");
        }
        if (password == null || password.isEmpty()) {
            throw new Exception("Senha é obrigatória.");
        }
        if (!isEmailValid(email)) {
            throw new Exception("E-mail inválido.");
        }
        if (isPasswordValid(password)) {
            throw new Exception("Senha deve ter 6 dígitos");
        }
        return true;
    }


    // User CRUD
    public static boolean userInfoValidation(String name, String email, String password, String type) throws Exception {
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
        if (!isEmailValid(email)) {
            throw new Exception("E-mail inválido.");
        }
        if (isPasswordValid(password)) {
            throw new Exception("Senha deve ter 6 dígitos");
        }
        return true;
    }


    // Point CRUD
    public static boolean pointInfoValidation(String name) throws Exception {
        if (name == null || name.isEmpty()) {
            throw  new Exception("Nome é obrigatório");
        }
        return true;
    }

    // Segment CRUD
    public static boolean segmentInfoValidation(Segment segment) throws Exception {
        if (segment.getPontoOrigem() == null) {
            throw  new Exception("Ponto inicial é obrigatório");
        }
        if (segment.getPontoDestino() == null) {
            throw  new Exception("Ponto final é obrigatório");
        }
        if (segment.getDirecao() == null || segment.getDirecao().isEmpty()) {
            throw  new Exception("Sentido é obrigatório");
        }
        if (segment.getDistancia() <= 0) {
            throw  new Exception("Distância inválida");
        }
        return true;
    }

    public static boolean isEmailValid(String email) {
        String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    // Route
    public static boolean routeInfoValidation(String startPoint, String endPoint) throws Exception {
        if (startPoint == null || startPoint.isEmpty()) {
            throw  new Exception("Ponto inicial é obrigatório");
        }
        if (endPoint == null || endPoint.isEmpty()) {
            throw  new Exception("Ponto final é obrigatório");
        }
        return true;
    }

    public static boolean isPasswordValid(String password) {
        return password.length() < 6;
    }

}