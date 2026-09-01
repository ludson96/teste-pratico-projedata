package com.projedata.controller;

import com.projedata.dto.FolhaEstatisticaResponseDTO;
import com.projedata.dto.FuncionarioMaisVelhoResponseDTO;
import com.projedata.dto.FuncionarioRequestDTO;
import com.projedata.dto.FuncionarioResponseDTO;
import com.projedata.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/funcionarios")
@Tag(name = "Funcionários", description = "Endpoints para gerenciamento e relatórios de funcionários")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @Operation(summary = "Listar todos os funcionários", description = "Retorna a lista completa de funcionários, com opção de ordenação alfabética por nome.")
    @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso")
    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDTO>> listar(
            @Parameter(description = "Se verdadeiro, ordena a lista alfabeticamente pelo nome")
            @RequestParam(defaultValue = "false") boolean ordenarPorNome) {
        return ResponseEntity.ok(funcionarioService.listarTodos(ordenarPorNome));
    }

    @Operation(summary = "Buscar funcionário por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Funcionário encontrado"),
        @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(funcionarioService.buscarPorId(id));
    }

    @Operation(summary = "Cadastrar novo funcionário")
    @ApiResponse(responseCode = "201", description = "Funcionário criado com sucesso")
    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> salvar(@Valid @RequestBody FuncionarioRequestDTO dto) {
        FuncionarioResponseDTO criado = funcionarioService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @Operation(summary = "Remover funcionário por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Funcionário removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable Long id) {
        funcionarioService.removerPorId(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Remover funcionário por Nome (Requisito 3.2)")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Funcionário removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
    })
    @DeleteMapping("/nome/{nome}")
    public ResponseEntity<Void> removerPorNome(@PathVariable String nome) {
        funcionarioService.removerPorNome(nome);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Aplicar reajuste salarial a todos os funcionários (Requisito 3.4)", 
               description = "Aplica um reajuste percentual no salário de todos os funcionários cadastrados (ex: 10 para 10%).")
    @ApiResponse(responseCode = "200", description = "Reajuste aplicado com sucesso")
    @PatchMapping("/reajuste")
    public ResponseEntity<List<FuncionarioResponseDTO>> aplicarReajuste(
            @Parameter(description = "Percentual de reajuste (ex: 10.0)", example = "10.0")
            @RequestParam(defaultValue = "10.0") BigDecimal percentual) {
        return ResponseEntity.ok(funcionarioService.aplicarReajusteSalarial(percentual));
    }

    @Operation(summary = "Agrupar funcionários por função (Requisito 3.5 e 3.6)")
    @ApiResponse(responseCode = "200", description = "Agrupamento retornado com sucesso")
    @GetMapping("/agrupados-por-funcao")
    public ResponseEntity<Map<String, List<FuncionarioResponseDTO>>> agruparPorFuncao() {
        return ResponseEntity.ok(funcionarioService.agruparPorFuncao());
    }

    @Operation(summary = "Buscar aniversariantes por mês (Requisito 3.8)", 
               description = "Filtra aniversariantes pelos meses informados. Padrão: meses 10 e 12.")
    @ApiResponse(responseCode = "200", description = "Aniversariantes recuperados com sucesso")
    @GetMapping("/aniversariantes")
    public ResponseEntity<List<FuncionarioResponseDTO>> buscarAniversariantes(
            @Parameter(description = "Lista dos números dos meses desejados (ex: 10,12)", example = "10,12")
            @RequestParam(defaultValue = "10,12") List<Integer> meses) {
        return ResponseEntity.ok(funcionarioService.buscarAniversariantes(meses));
    }

    @Operation(summary = "Obter o funcionário de maior idade (Requisito 3.9)")
    @ApiResponse(responseCode = "200", description = "Funcionário com maior idade retornado")
    @GetMapping("/mais-velho")
    public ResponseEntity<FuncionarioMaisVelhoResponseDTO> obterMaisVelho() {
        return ResponseEntity.ok(funcionarioService.obterFuncionarioMaisVelho());
    }

    @Operation(summary = "Obter estatísticas da folha de pagamento e múltiplos de salários mínimos (Requisitos 3.11 e 3.12)")
    @ApiResponse(responseCode = "200", description = "Estatísticas geradas com sucesso")
    @GetMapping("/estatisticas/folha")
    public ResponseEntity<FolhaEstatisticaResponseDTO> obterEstatisticasFolha(
            @Parameter(description = "Valor base do salário mínimo para referência", example = "1212.00")
            @RequestParam(defaultValue = "1212.00") BigDecimal salarioMinimo) {
        return ResponseEntity.ok(funcionarioService.obterEstatisticasFolha(salarioMinimo));
    }
}
