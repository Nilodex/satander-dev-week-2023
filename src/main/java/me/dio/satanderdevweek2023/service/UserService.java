package me.dio.satanderdevweek2023.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import me.dio.satanderdevweek2023.domain.model.UserModel;
import me.dio.satanderdevweek2023.domain.repository.UserRepository;



@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    private Boolean userExistsByEmail(String email){
        Optional<UserModel> userObj = userRepository.findByEmail(email);
        if(userObj.isPresent()){
            return true;
        }
        return false;
    }

    public UserModel save(UserModel userModel){
        return userRepository.save(userModel);
    }

    public UserModel checkUserExistsAndSave(UserModel userModel){
        if(this.userExistsByEmail(userModel.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail já está em uso, utilize outro.");
        }
        return this.save(userModel);
    }

    public List<UserModel> findAll(){
        return userRepository.findAll();
    }

    public UserModel findById(UUID id){
        Optional<UserModel> userObj = userRepository.findById(id);
        if(userObj.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        return userObj.get();
    }

    public void deleteById(UUID id){
        UserModel user = this.findById(id);
        userRepository.delete(user);
    }
}

