package me.dio.satanderdevweek2023.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import me.dio.satanderdevweek2023.domain.model.PredioModel;
import me.dio.satanderdevweek2023.domain.model.PredioUser;
import me.dio.satanderdevweek2023.domain.model.UserModel;
import me.dio.satanderdevweek2023.dtos.PredioUserRecordDto;
import me.dio.satanderdevweek2023.service.PredioUserService;


@RestController
public class PredioUserController {

    @Autowired
    private PredioUserService predioUserService;

    //obtem uma lista de usuarios de um determinado prédio
    @GetMapping("predios/{predioId}/predioUsers")
    public ResponseEntity<Object> getUsersFromPredio(@PathVariable(value="predioId") UUID predioId){
        return ResponseEntity.status(HttpStatus.OK).body(predioUserService.findByPredioId(predioId));
    }

    //salva um usuário em um prédio
    @PostMapping("predioUsers")
    public ResponseEntity<Object> savePredioUsers(@RequestBody PredioUserRecordDto predioUserRecordDto){
        UserModel user = new UserModel();
        user.setId(predioUserRecordDto.userId());
        PredioModel predio = new PredioModel();
        predio.setId(predioUserRecordDto.predioId());
        PredioUser predioUser = new PredioUser(user, predio);
        return ResponseEntity.status(HttpStatus.CREATED).body(predioUserService.save(predioUser));
    }

    //obtém as listas de todos os usuários de todos os prédios
    @GetMapping("predioUsers")
    public ResponseEntity<List<PredioUser>> getAllPredioUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(predioUserService.findAll());
    }

    //obtém um único usuário de um prédio
    @GetMapping("predioUsers/{id}")
    public ResponseEntity<PredioUser> getPredioUser(@PathVariable(value="id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(predioUserService.findById(id));
    }

    //remove usuario do predio
    @DeleteMapping("/predioUsers/{id}")
    public ResponseEntity<String> deletePredioUser(@PathVariable(value="id") UUID id){
        predioUserService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário removido");
    }
}
