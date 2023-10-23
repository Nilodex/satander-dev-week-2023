package me.dio.satanderdevweek2023.domain.model;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="TB_AGENDAMENTOS")
public class AgendamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(unique = false, nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dataHoraInicio;
    @Column(unique = false, nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dataHoraFim;
    @ManyToOne
    @JoinColumn(name = "predio_user_id", nullable = false)
    @JsonIgnore
    private PredioUser predioUser;
    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    @JsonIgnore
    private SalaModel sala;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public Date getDataHoraInicio() {
        return dataHoraInicio;
    }
    public void setDataHoraInicio(Date dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }
    public Date getDataHoraFim() {
        return dataHoraFim;
    }
    public void setDataHoraFim(Date dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }
    public PredioUser getPredioUser() {
        return predioUser;
    }
    public void setPredioUser(PredioUser predioUser) {
        this.predioUser = predioUser;
    }
    public SalaModel getSala() {
        return sala;
    }
    public void setSala(SalaModel sala) {
        this.sala = sala;
    }

    public UUID getSalaId(){
        return this.sala.getId();
    }
    
}
