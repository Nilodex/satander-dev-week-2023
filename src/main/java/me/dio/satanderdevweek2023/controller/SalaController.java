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
import me.dio.satanderdevweek2023.domain.model.SalaModel;
import me.dio.satanderdevweek2023.dtos.SalaRecordDto;
import me.dio.satanderdevweek2023.service.SalaService;

@RestController
public class SalaController {
    @Autowired
    private SalaService salaService;


    @PostMapping("predios/{predioId}/salas")
    public ResponseEntity<SalaModel> saveSala(@PathVariable(value="predioId") UUID predioId, @RequestBody @Valid SalaRecordDto salaRecordDto) {
        PredioModel predioModel = new PredioModel();
        predioModel.setId(predioId);
        SalaModel salaModel = new SalaModel();
        BeanUtils.copyProperties(salaRecordDto, salaModel);
        salaModel.setPredio(predioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(salaService.save(salaModel));
    }

    @GetMapping("predios/{predioId}/salas")
    public ResponseEntity<List<SalaModel>> getAllSalasFromPredio(@PathVariable(value="predioId") UUID predioId){
        return ResponseEntity.ok().body(salaService.findByPredioId(predioId));
    }


    @GetMapping("salas")
    public ResponseEntity<List<SalaModel>> getAllSalas(){
        return ResponseEntity.status(HttpStatus.OK).body(salaService.findAll());
    }

    @GetMapping("salas/{salaId}")
    public ResponseEntity<SalaModel> getSala(@PathVariable(value="salaId") UUID salaId){
        return ResponseEntity.status(HttpStatus.OK).body(salaService.findById(salaId));
    }

    @PutMapping("salas/{salaId}")
    public ResponseEntity<SalaModel> updateSala(@PathVariable(value="salaId") UUID salaId, @RequestBody SalaRecordDto salaRecordDto){
        SalaModel sala = salaService.findById(salaId);
        BeanUtils.copyProperties(salaRecordDto, sala);
        return ResponseEntity.status(HttpStatus.OK).body(salaService.save(sala));
    }

    @DeleteMapping("salas/{salaId}")
    public ResponseEntity<String> deleteSalas(@PathVariable(value="salaId") UUID salaId){
        salaService.deleteById(salaId);
        return ResponseEntity.status(HttpStatus.OK).body("Sala removida");
    }
}
