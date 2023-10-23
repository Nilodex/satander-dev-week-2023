package me.dio.satanderdevweek2023.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import me.dio.satanderdevweek2023.domain.model.PredioModel;
import me.dio.satanderdevweek2023.dtos.PredioRecordDto;
import me.dio.satanderdevweek2023.service.PredioService;

@RestController
public class PredioController {

    @Autowired
    private PredioService predioService;

    @PostMapping("/predios")
    public ResponseEntity<PredioModel> savePredio(@RequestBody @Valid PredioRecordDto predioRecordDto) {
        PredioModel newPredio = new PredioModel();
        //passa todos os dados que tava no json e coloca no novo predio
        BeanUtils.copyProperties(predioRecordDto, newPredio);
        return ResponseEntity.status(HttpStatus.CREATED).body(predioService.save(newPredio));
    }

    @GetMapping("/predios")
    public ResponseEntity<List<PredioModel>> getAllPredios(){
        return ResponseEntity.status(HttpStatus.OK).body(predioService.findAll());
    }

    @GetMapping("/predios/{id}")
    public ResponseEntity<PredioModel> getOnePredio(@PathVariable(value="id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(predioService.findById(id));
    }

    @PutMapping("/predios/{id}")
    public ResponseEntity<PredioModel> updatePredio(@PathVariable(value="id") UUID id, @RequestBody @Valid PredioRecordDto predioRecordDto){
        PredioModel predio = predioService.findById(id);
        BeanUtils.copyProperties(predioRecordDto, predio);
        return ResponseEntity.status(HttpStatus.OK).body(predioService.save(predio));
    }

    @DeleteMapping("/predios/{id}")
    public ResponseEntity<String> deletePredio(@PathVariable(value="id") UUID id){
        predioService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Prédio removido");

    }

}
