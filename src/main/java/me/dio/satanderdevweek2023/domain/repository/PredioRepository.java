package me.dio.satanderdevweek2023.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.satanderdevweek2023.domain.model.PredioModel;

@Repository
public interface PredioRepository extends JpaRepository<PredioModel, UUID>{
    
}
