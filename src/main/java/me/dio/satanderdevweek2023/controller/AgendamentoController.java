package me.dio.satanderdevweek2023.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import me.dio.satanderdevweek2023.domain.model.AgendamentoModel;
import me.dio.satanderdevweek2023.domain.model.PredioUser;
import me.dio.satanderdevweek2023.domain.model.SalaModel;
import me.dio.satanderdevweek2023.dtos.AgendamentoDto;
import me.dio.satanderdevweek2023.service.AgendamentoService;

@RestController
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    
    @GetMapping("salas/{salaId}/agendamentos")
    public ResponseEntity<Object> getAgendamentosFromSala(@PathVariable(value="salaId") UUID salaId){
        return ResponseEntity.status(HttpStatus.OK).body(agendamentoService.findAllBySalaId(salaId));
    }

    @PostMapping("salas/{salaId}/agendamentos")
    public ResponseEntity<Object> saveAgendamento(@PathVariable(value="salaId") UUID salaId, @RequestBody @Valid AgendamentoDto agendamentoDto){
        AgendamentoModel agendamentoModel = new AgendamentoModel();
        PredioUser predioUser = new PredioUser();
        predioUser.setId(agendamentoDto.predioUserId());
        agendamentoModel.setPredioUser(predioUser);
        agendamentoModel.setDataHoraInicio(agendamentoDto.dataHoraInicio());
        agendamentoModel.setDataHoraFim(agendamentoDto.dataHoraFim());
        SalaModel sala = new SalaModel();
        sala.setId(salaId);
        agendamentoModel.setSala(sala);
        return ResponseEntity.status(HttpStatus.OK).body(agendamentoService.save(agendamentoModel));
    }
}
