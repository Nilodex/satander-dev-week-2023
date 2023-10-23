package me.dio.satanderdevweek2023.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.satanderdevweek2023.domain.model.PredioModel;
import me.dio.satanderdevweek2023.domain.model.PredioUser;
import me.dio.satanderdevweek2023.domain.model.UserModel;

@Repository
public interface PredioUserRepository extends JpaRepository<PredioUser, UUID>{
    Optional<PredioUser> findByUserAndPredio(UserModel user, PredioModel predio);

    List<PredioUser> findByPredio(PredioModel predio);

    Optional<PredioUser> findByIdAndPredio(UUID id, PredioModel predio);
}