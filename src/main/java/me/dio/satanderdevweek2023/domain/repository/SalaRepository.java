package me.dio.satanderdevweek2023.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.satanderdevweek2023.domain.model.PredioModel;
import me.dio.satanderdevweek2023.domain.model.SalaModel;

@Repository
public interface SalaRepository extends JpaRepository<SalaModel, UUID> {

    List<SalaModel> findByPredio(PredioModel predio);
    
}
