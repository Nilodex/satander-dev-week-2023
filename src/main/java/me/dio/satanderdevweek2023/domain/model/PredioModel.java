package me.dio.satanderdevweek2023.domain.model;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name="TB_PREDIOS")
public class PredioModel {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID predio_id;
    @Column(nullable = false, unique = false)
    private String nome;
    @Column(nullable = false, unique = false)
    private String cidade;
    @Column(nullable = false, unique = false)
    private String estado;
    @Column(unique = false, nullable = true)
    private String fotoUrl;


    public UUID getId() {
        return predio_id;
    }
    public void setId(UUID predio_id) {
        this.predio_id = predio_id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getFotoUrl() {
        return fotoUrl;
    }
    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    @OneToMany(mappedBy = "predio")
    Set<PredioUser> predioUsers;

    @OneToMany(mappedBy= "predio")
    Set<SalaModel> salas;

    public Set<PredioUser> getPredioUsers() {
        return predioUsers;
    }
    public void setPredioUsers(Set<PredioUser> predioUsers) {
        this.predioUsers = predioUsers;
    }

    public Set<SalaModel> getSalas() {
        return this.salas;
    }

    public void setSalas(Set<SalaModel> salas){
        this.salas = salas;
    }

}