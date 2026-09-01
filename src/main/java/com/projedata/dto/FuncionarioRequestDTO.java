package com.projedata.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "Dados para criação ou atualização de um funcionário")
public record FuncionarioRequestDTO(

    @Schema(description = "Nome do funcionário", example = "Maria")
    @NotBlank(message = "O nome é obrigatório")
    String nome,

    @Schema(description = "Data de nascimento no formato YYYY-MM-DD", example = "2000-10-18")
    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve ser uma data no passado")
    LocalDate dataNascimento,

    @Schema(description = "Salário inicial do funcionário", example = "2009.44")
    @NotNull(message = "O salário é obrigatório")
    @DecimalMin(value = "0.01", message = "O salário deve ser maior que zero")
    BigDecimal salario,

    @Schema(description = "Função ou cargo exercido", example = "Operador")
    @NotBlank(message = "A função é obrigatória")
    String funcao
) {}
