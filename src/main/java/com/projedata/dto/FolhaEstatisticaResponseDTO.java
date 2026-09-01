package com.projedata.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Resumo estatístico da folha salarial da empresa")
public record FolhaEstatisticaResponseDTO(
    @Schema(description = "Valor total da soma de todos os salários", example = "35000.50")
    BigDecimal totalSalarios,

    @Schema(description = "Valor de referência do salário mínimo utilizado", example = "1212.00")
    BigDecimal salarioMinimoReferencia,

    @Schema(description = "Detalhamento de salários mínimos recebidos por funcionário")
    List<SalarioMinimoFuncionarioDTO> funcionarios
) {
    public record SalarioMinimoFuncionarioDTO(
        @Schema(description = "Nome do funcionário", example = "Maria")
        String nome,

        @Schema(description = "Salário atual em Reais", example = "2009.44")
        BigDecimal salario,

        @Schema(description = "Quantidade de salários mínimos equivalentes", example = "1.66")
        BigDecimal qtdSalariosMinimos
    ) {}
}
