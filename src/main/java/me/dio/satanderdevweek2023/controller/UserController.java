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
import me.dio.satanderdevweek2023.domain.model.UserModel;
import me.dio.satanderdevweek2023.dtos.UserRecordDto;
import me.dio.satanderdevweek2023.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        var userModel = new UserModel(userRecordDto.nome(), userRecordDto.email());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.checkUserExistsAndSave(userModel));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserModel> getOneUser(@PathVariable(value="id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable(value="id") UUID id, @RequestBody @Valid UserRecordDto userRecordDto){
        UserModel user = userService.findById(id);
        BeanUtils.copyProperties(userRecordDto, user);
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value="id") UUID id){
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado");
    }    
}
