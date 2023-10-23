package me.dio.satanderdevweek2023.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import me.dio.satanderdevweek2023.domain.model.AgendamentoModel;
import me.dio.satanderdevweek2023.domain.model.PredioUser;
import me.dio.satanderdevweek2023.domain.model.SalaModel;
import me.dio.satanderdevweek2023.domain.repository.AgendamentoRepository;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private SalaService salaService;

    @Autowired
    private PredioUserService predioUserService;

    public List<AgendamentoModel> findAll(){
        return agendamentoRepository.findAll();
    }

    public List<AgendamentoModel> findAllBySalaId(UUID salaId){
        SalaModel sala = salaService.findById(salaId);
        return agendamentoRepository.findBySala(sala);
    }

    public AgendamentoModel findById(UUID id){
        Optional<AgendamentoModel> agendamentoObj = agendamentoRepository.findById(id);
        if(agendamentoObj.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado");
        }
        return agendamentoObj.get();
    }

    public AgendamentoModel save(AgendamentoModel agendamento){
        SalaModel sala = salaService.findById(agendamento.getSalaId());
        agendamento.setSala(sala);
        //a data fim não pode ser menor que a data inicio
        long diffMillis = agendamento.getDataHoraFim().getTime() - agendamento.getDataHoraInicio().getTime();
        long diffHours = TimeUnit.MILLISECONDS.toHours(diffMillis);
        if(diffHours < 1){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A diferença entre o fim e o inicio do agendamento deve ser de pelo menos 1 hora.");
        }
        //o usuario deve estar cadastrado no predio
        UUID predioUserId = agendamento.getPredioUser().getId();
        UUID predioId = sala.getPredioId();
        PredioUser predioUser = predioUserService.findByIdAndPredioId(predioUserId, predioId);
        agendamento.setPredioUser(predioUser);
        //TODO não deve existir um agendamento entre o espaço de tempo
        return agendamentoRepository.save(agendamento); 
    }
}