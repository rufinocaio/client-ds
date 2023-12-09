package caiofurlan.clientdistributedsystems.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {

    private final ObjectMapper objectMapper = new ObjectMapper();
    Socket socket = null;
    OutputStream out = null;
    InputStream in = null;

    public void connect(String ip, String port) throws Exception {
        try {
            socket = new Socket(ip, Integer.parseInt(port));
            out = socket.getOutputStream();
            in = socket.getInputStream();
        } catch (UnknownHostException e) {
            throw new Exception("Servidor não encontrado");
        } catch (IOException e) {
            throw new Exception("Erro ao conectar com o servidor");
        } catch (Exception e) {
            throw new Exception("Erro ao conectar com o servidor");
        }
    }

    public String send(String message) {
        String response = null;
        while (true) {
            try {
                PrintWriter writer = new PrintWriter(out, true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                JsonNode jsonNode = objectMapper.readTree(message);
                String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
                System.out.println("Enviando para o servidor: \n" + prettyJson);
                writer.println(message);
                if((response = reader.readLine()) != null) {
                    jsonNode = objectMapper.readTree(response);
                    prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
                    System.out.println("JSON recebido do servidor: \n" + prettyJson);
                    break;
                }
            } catch (IOException e) {
                System.err.println("Erro ao enviar mensagem para o servidor: " + e.getMessage());
                break;
            }
        }
        return response;
    }

    public void close() throws IOException {
        out.close();
        in.close();
        socket.close();
    }

}
