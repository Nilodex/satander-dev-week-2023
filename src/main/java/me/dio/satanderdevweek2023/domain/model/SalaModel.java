package me.dio.satanderdevweek2023.domain.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "TB_SALAS")
public class SalaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(unique = false, nullable = true)
    private int qtdCadeiras;
    @Column(unique = false, nullable = true)
    private int qtdMesas;
    @Column(unique = false, nullable = false)
    private boolean temWifi;
    @ManyToOne
    @JoinColumn(name = "predio_id", nullable = false)
    @JsonIgnore
    private PredioModel predio;

    public SalaModel(){}

    public SalaModel(
            String nome,
            int qtdCadeiras,
            int qtdMesas,
            boolean temWifi,
            PredioModel predio) {
        this.nome = nome;
        this.qtdCadeiras = qtdCadeiras;
        this.qtdMesas = qtdMesas;
        this.temWifi = temWifi;
        this.predio = predio;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdCadeiras() {
        return qtdCadeiras;
    }

    public void setQtdCadeiras(int qtdCadeiras) {
        this.qtdCadeiras = qtdCadeiras;
    }

    public int getQtdMesas() {
        return qtdMesas;
    }

    public void setQtdMesas(int qtdMesas) {
        this.qtdMesas = qtdMesas;
    }

    public boolean isTemWifi() {
        return temWifi;
    }

    public void setTemWifi(boolean temWifi) {
        this.temWifi = temWifi;
    }

    public UUID getPredioId() {
        return this.predio.getId();
    }

    public PredioModel getPredio(){
        return this.predio;
    }

    public void setPredio(PredioModel predio){
        this.predio = predio;
    }
}