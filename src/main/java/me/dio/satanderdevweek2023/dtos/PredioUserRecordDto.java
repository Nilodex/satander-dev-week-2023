package me.dio.satanderdevweek2023.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PredioUserRecordDto(@NotNull @NotBlank UUID predioId, @NotBlank UUID userId) {
    
}