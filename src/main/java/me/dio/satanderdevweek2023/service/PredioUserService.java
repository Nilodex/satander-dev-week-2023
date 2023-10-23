package me.dio.satanderdevweek2023.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import me.dio.satanderdevweek2023.domain.model.PredioModel;
import me.dio.satanderdevweek2023.domain.model.PredioUser;
import me.dio.satanderdevweek2023.domain.model.UserModel;
import me.dio.satanderdevweek2023.domain.repository.PredioUserRepository;


@Service
public class PredioUserService {
    @Autowired
    PredioUserRepository predioUserRepository;

    @Autowired
    PredioService predioService;

    @Autowired
    UserService userService;

    public PredioUser save(PredioUser predioUser){
        UserModel user = userService.findById(predioUser.getUser().getId());
        PredioModel predio = predioService.findById(predioUser.getPredioId());
        Optional<PredioUser> predioUserObj = predioUserRepository.findByUserAndPredio(user, predio);
        if(predioUserObj.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já está inserido nesse prédio.");
        }
        return predioUserRepository.save(predioUser);
    }

    public List<PredioUser> findByPredioId(UUID id){
        PredioModel predio = predioService.findById(id);
        return predioUserRepository.findByPredio(predio);
    }

    public PredioUser findByIdAndPredioId(UUID predioUserId, UUID predioId){
        PredioModel predio = predioService.findById(predioId);
        Optional<PredioUser> predioUserObj = predioUserRepository.findByIdAndPredio(predioUserId, predio);
        if(predioUserObj.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário do prédio não encontrado");
        }
        return predioUserObj.get();
    }

    public PredioUser findByUserIdAndPredioId(UUID userId, UUID predioId){
        PredioModel predio = predioService.findById(predioId);
        UserModel user = userService.findById(userId);
        Optional<PredioUser> predioUserObj = predioUserRepository.findByUserAndPredio(user, predio);
        if(predioUserObj.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário do prédio não encontrado");
        } 
        return predioUserObj.get();
    }

    public List<PredioUser> findAll(){
        return predioUserRepository.findAll();
    }

    public PredioUser findById(UUID id){
        Optional<PredioUser> predioUserObj = predioUserRepository.findById(id);
        if(predioUserObj.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        return predioUserObj.get();
    }

    public void deleteById(UUID id){
        PredioUser predioUser = this.findById(id);
        predioUserRepository.delete(predioUser); 
    }
}
