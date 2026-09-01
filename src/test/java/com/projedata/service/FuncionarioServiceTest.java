package com.projedata.service;

import com.projedata.dto.FolhaEstatisticaResponseDTO;
import com.projedata.dto.FuncionarioMaisVelhoResponseDTO;
import com.projedata.dto.FuncionarioRequestDTO;
import com.projedata.dto.FuncionarioResponseDTO;
import com.projedata.model.Funcionario;
import com.projedata.repository.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioService funcionarioService;

    private List<Funcionario> funcionariosMock;

    @BeforeEach
    void setUp() {
        Funcionario f1 = new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2000.00"), "Operador");
        f1.setId(1L);

        Funcionario f2 = new Funcionario("Caio", LocalDate.of(1960, 5, 12), new BigDecimal("10000.00"), "Coordenador");
        f2.setId(2L);

        funcionariosMock = List.of(f1, f2);
    }

    @Test
    @DisplayName("Deve listar todos os funcionários")
    void deveListarTodos() {
        when(funcionarioRepository.findAll()).thenReturn(funcionariosMock);

        List<FuncionarioResponseDTO> resultado = funcionarioService.listarTodos(false);

        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).nome()).isEqualTo("Maria");
        verify(funcionarioRepository).findAll();
    }

    @Test
    @DisplayName("Deve cadastrar novo funcionário")
    void deveSalvarFuncionario() {
        FuncionarioRequestDTO request = new FuncionarioRequestDTO("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2500.00"), "Recepcionista");
        Funcionario funcionarioSalvo = new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2500.00"), "Recepcionista");
        funcionarioSalvo.setId(3L);

        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionarioSalvo);

        FuncionarioResponseDTO response = funcionarioService.salvar(request);

        assertThat(response.id()).isEqualTo(3L);
        assertThat(response.nome()).isEqualTo("Alice");
    }

    @Test
    @DisplayName("Deve aplicar reajuste percentual de 10% com sucesso")
    void deveAplicarReajuste() {
        when(funcionarioRepository.findAll()).thenReturn(funcionariosMock);
        when(funcionarioRepository.saveAll(any())).thenAnswer(invocation -> invocation.getArgument(0));

        List<FuncionarioResponseDTO> reajustados = funcionarioService.aplicarReajusteSalarial(new BigDecimal("10.0"));

        assertThat(reajustados.get(0).salario()).isEqualByComparingTo("2200.00");
        assertThat(reajustados.get(1).salario()).isEqualByComparingTo("11000.00");
    }

    @Test
    @DisplayName("Deve agrupar funcionários por função")
    void deveAgruparPorFuncao() {
        when(funcionarioRepository.findAll()).thenReturn(funcionariosMock);

        Map<String, List<FuncionarioResponseDTO>> agrupados = funcionarioService.agruparPorFuncao();

        assertThat(agrupados).containsKey("Operador");
        assertThat(agrupados).containsKey("Coordenador");
        assertThat(agrupados.get("Operador")).hasSize(1);
    }

    @Test
    @DisplayName("Deve identificar o funcionário mais velho")
    void deveObterFuncionarioMaisVelho() {
        when(funcionarioRepository.findAll()).thenReturn(funcionariosMock);

        FuncionarioMaisVelhoResponseDTO maisVelho = funcionarioService.obterFuncionarioMaisVelho();

        assertThat(maisVelho.nome()).isEqualTo("Caio");
        assertThat(maisVelho.idade()).isGreaterThan(60);
    }

    @Test
    @DisplayName("Deve calcular o total de salários e múltiplos do salário mínimo")
    void deveCalcularEstatisticasFolha() {
        when(funcionarioRepository.findAll()).thenReturn(funcionariosMock);

        FolhaEstatisticaResponseDTO stats = funcionarioService.obterEstatisticasFolha(new BigDecimal("1000.00"));

        assertThat(stats.totalSalarios()).isEqualByComparingTo("12000.00");
        assertThat(stats.funcionarios().get(0).qtdSalariosMinimos()).isEqualByComparingTo("2.00");
        assertThat(stats.funcionarios().get(1).qtdSalariosMinimos()).isEqualByComparingTo("10.00");
    }
}
