package me.dio.satanderdevweek2023.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PredioRecordDto(@NotBlank @NotNull String nome, @NotBlank @NotNull String cidade, @NotBlank @NotNull String estado) {
    
}