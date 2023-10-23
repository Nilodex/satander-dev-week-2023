package me.dio.satanderdevweek2023.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.satanderdevweek2023.domain.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    
    UserModel findUserById(UUID id);

    Optional<UserModel> findByEmail(String email);
    
}
