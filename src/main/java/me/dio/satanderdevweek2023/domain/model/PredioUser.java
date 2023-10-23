package me.dio.satanderdevweek2023.domain.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name= "TB_PREDIO_USERS")
public class PredioUser{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID predio_user_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserModel user;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "predio_id")
    @JsonIgnore
    private PredioModel predio;

    public PredioUser(){}

    public PredioUser(UserModel user, PredioModel predio){
        this.user = user;
        this.predio = predio;
    }

    public UUID getId() {
        return predio_user_id;
    }

    public void setId(UUID predio_user_id) {
        this.predio_user_id = predio_user_id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public PredioModel getPredio() {
        return predio;
    }

    public void setPredio(PredioModel predio) {
        this.predio = predio;
    }

    public UUID getUserId(){
        return this.user.getId();
    }

    public UUID getPredioId(){
        return this.predio.getId();
    }
}