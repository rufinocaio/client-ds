package caiofurlan.clientdistributedsystems.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"id"}, allowSetters = true)
public class Segment {
    @JsonProperty("ponto_origem")
    private Point pontoOrigem;
    @JsonProperty("ponto_destino")
    private Point pontoDestino;
    @JsonProperty("direcao")
    private String direcao;
    @JsonProperty("distancia")
    private int distancia;
    @JsonProperty("obs")
    private String obs;
    @JsonProperty("id")
    private int id;

    public Segment() {
    }

    public Segment(Point ponto_origem, Point ponto_destino, String direcao, int distancia, String obs) {
        this.pontoOrigem = ponto_origem;
        this.pontoDestino = ponto_destino;
        this.direcao = direcao;
        this.distancia = distancia;
        this.obs = obs;
    }

    public Point getPontoOrigem() {
        return pontoOrigem;
    }

    public void setPontoOrigem(Point ponto_origem) {
        this.pontoOrigem = ponto_origem;
    }

    public Point getPontoDestino() {
        return pontoDestino;
    }

    public void setPontoDestino(Point ponto_destino) {
        this.pontoDestino = ponto_destino;
    }

    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
