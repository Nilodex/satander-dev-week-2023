package me.dio.satanderdevweek2023.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SalaRecordDto(@NotBlank @NotNull String nome, int qtdCadeiras, int qtdMesas, @NotNull boolean temWifi) {
   
}
