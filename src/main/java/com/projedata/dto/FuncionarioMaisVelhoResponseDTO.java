package com.projedata.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Dados do funcionário com a maior idade")
public record FuncionarioMaisVelhoResponseDTO(
    @Schema(description = "Nome do funcionário", example = "Caio")
    String nome,

    @Schema(description = "Idade em anos completos", example = "65")
    int idade,

    @Schema(description = "Data de nascimento", example = "1961-05-12")
    LocalDate dataNascimento
) {}
