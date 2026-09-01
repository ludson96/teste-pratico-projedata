package com.projedata.dto;

import com.projedata.model.Funcionario;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "Dados de retorno do funcionário")
public record FuncionarioResponseDTO(
    @Schema(description = "ID único do funcionário", example = "1")
    Long id,

    @Schema(description = "Nome do funcionário", example = "Maria")
    String nome,

    @Schema(description = "Data de nascimento", example = "2000-10-18")
    LocalDate dataNascimento,

    @Schema(description = "Salário atual", example = "2210.38")
    BigDecimal salario,

    @Schema(description = "Função ou cargo", example = "Operador")
    String funcao
) {
    public static FuncionarioResponseDTO fromEntity(Funcionario f) {
        return new FuncionarioResponseDTO(
            f.getId(),
            f.getNome(),
            f.getDataNascimento(),
            f.getSalario(),
            f.getFuncao()
        );
    }
}
