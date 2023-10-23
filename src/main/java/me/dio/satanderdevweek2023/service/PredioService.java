package me.dio.satanderdevweek2023.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import me.dio.satanderdevweek2023.domain.model.PredioModel;
import me.dio.satanderdevweek2023.domain.repository.PredioRepository;


@Service
public class PredioService {
    @Autowired
    private PredioRepository predioRepository;

    public PredioModel save(PredioModel predio){
        return predioRepository.save(predio);
    }

    public List<PredioModel> findAll(){
        return predioRepository.findAll();
    }

    public PredioModel findById(UUID id){
        Optional<PredioModel>predioObj = predioRepository.findById(id);
        if(predioObj.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prédio não encontrado");
        }
        return predioObj.get();
    }

    public void deleteById(UUID id){ 
        PredioModel predio = this.findById(id);
        predioRepository.delete(predio);
    }
}
