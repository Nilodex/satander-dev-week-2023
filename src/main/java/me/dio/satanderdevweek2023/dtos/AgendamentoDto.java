package me.dio.satanderdevweek2023.dtos;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AgendamentoDto(@NotBlank UUID predioUserId, @NotNull @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") Date dataHoraInicio, @NotNull @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") Date dataHoraFim) {
    
}