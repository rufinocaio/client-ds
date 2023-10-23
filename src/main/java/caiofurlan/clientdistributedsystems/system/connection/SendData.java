package caiofurlan.clientdistributedsystems.system.connection;

import caiofurlan.clientdistributedsystems.App;
import caiofurlan.clientdistributedsystems.system.utilities.Token;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class SendData {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private Map<String, Object> data;
    private String message;
    private String error;

    public SendData(Map<String, Object> data, String message, String error) {
        this.data = data;
        this.message = message;
        this.error = error;
    }

    public SendData() {
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Map<String, Object> generateLoginData(String email, String password) throws JsonProcessingException {
        this.data = new HashMap<>();
        this.data.put("email", email);
        this.data.put("password", password);
        return generateFinalData("login", this.data);
    }

    public Map<String, Object> generateRegisterUserADMData(String name, String email, String password, String type) throws JsonProcessingException {
        this.data = new HashMap<>();
        this.data.put("token", Token.getJwtToken());
        this.data.put("name", name);
        this.data.put("email", email);
        this.data.put("password", password);
        this.data.put("type", type);
        return generateFinalData("cadastro-usuario", this.data);
    }

    public Map<String, Object> generateRegisterUserData(String name, String email, String password) throws JsonProcessingException {
        this.data = new HashMap<>();
        this.data.put("nome", name);
        this.data.put("email", email);
        this.data.put("password", password);
        return generateFinalData("autocadastro-usuario", this.data);
    }

    public Map<String, Object> generateLogoutData(String token) throws JsonProcessingException {
        this.data = new HashMap<>();
        this.data.put("token", token);
        return generateFinalData("logout", this.data);
    }

    public  Map<String, Object> generateFinalData(String action, Map<String, Object> data) throws JsonProcessingException {
        this.data = new HashMap<>();
        this.data.put("action", action);
        this.data.put("data", data);
        return this.data;
    }

    /* ----------------- */
    public String sendLogin(String email, String password) {
        String response = null;
        try {
            Connection connection = App.getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateLoginData(email, password)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    /* ----------------- */
    public String sendRegisterUserADM(String name, String email, String password, String type) {
        String response = null;
        try {
            Connection connection = App.getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateRegisterUserADMData(name, email, password, type)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public String sendRegisterUser(String name, String email, String password) {
        String response = null;
        try {
            Connection connection = App.getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateRegisterUserData(name, email, password)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public String sendLogout(String token) {
        String response = null;
        try {
            if (IsValidData.logoutIsValid(token)) {
                Connection connection = App.getConnection();
                response = connection.send(objectMapper.writeValueAsString(generateLogoutData(token)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

}
