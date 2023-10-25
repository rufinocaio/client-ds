package caiofurlan.clientdistributedsystems.system;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

public class JsonHandler {
    public String handleRequest(Socket socket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String inputLine;
        StringBuilder jsonBuilder = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            jsonBuilder.append(inputLine);
        }

        String receivedJson = jsonBuilder.toString();
        System.out.println("JSON recebido do servidor: " + receivedJson);
        return receivedJson;
    }

    public static void sendJson(Socket socket, String action, Map<String, String> data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap;
        jsonMap = Map.of("action", action, "data", data);
        String jsonRequest = objectMapper.writeValueAsString(jsonMap);

        PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("JSON enviado para o servidor: " + jsonRequest);
        outToServer.println(jsonRequest);
        socket.shutdownOutput();
    }
}