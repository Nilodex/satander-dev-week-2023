package me.dio.satanderdevweek2023.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import me.dio.satanderdevweek2023.domain.model.PredioModel;
import me.dio.satanderdevweek2023.domain.model.SalaModel;
import me.dio.satanderdevweek2023.domain.repository.SalaRepository;

@Service
public class SalaService {
    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private PredioService predioService;

    public SalaModel findById(UUID id){
        Optional<SalaModel> salaObj = salaRepository.findById(id);
        if(salaObj.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada");
        }
        return salaObj.get();
    }

    public List<SalaModel> findByPredioId(UUID predioId){
        PredioModel predio = predioService.findById(predioId);
        return salaRepository.findByPredio(predio);
    }

    public SalaModel save(SalaModel sala){
        UUID predioId = sala.getPredioId();
        //predio deve existir para criar a sala
        predioService.findById(predioId);
        return salaRepository.save(sala);
    }

    public List<SalaModel> findAll(){
        return salaRepository.findAll();
    }

    public void deleteById(UUID salaId){
        //busca a sala
        SalaModel sala = this.findById(salaId);
        salaRepository.delete(sala);
    }   
}