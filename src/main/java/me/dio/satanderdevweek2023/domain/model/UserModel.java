package me.dio.satanderdevweek2023.domain.model;

import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name="TB_USERS")
public class UserModel {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(unique = true, nullable = false)
    private String email;

    public UserModel(){}

    public UserModel(UUID id, String nome, String email){
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public UserModel(String nome, String email){
        this.nome = nome;
        this.email = email;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    Set<PredioUser> predioUsers;


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
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Set<PredioUser> getPredioUsers() {
        return predioUsers;
    }

    public void setPredioUsers(Set<PredioUser> predioUsers) {
        this.predioUsers = predioUsers;
    }
    
}