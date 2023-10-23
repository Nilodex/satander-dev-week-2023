package me.dio.satanderdevweek2023.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.satanderdevweek2023.domain.model.AgendamentoModel;
import me.dio.satanderdevweek2023.domain.model.PredioUser;
import me.dio.satanderdevweek2023.domain.model.SalaModel;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoModel, UUID>{

    List<AgendamentoModel> findByPredioUser(PredioUser predioUser);

    List<AgendamentoModel> findBySala(SalaModel sala);
    
}