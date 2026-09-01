package com.projedata.service;

import com.projedata.dto.FolhaEstatisticaResponseDTO;
import com.projedata.dto.FuncionarioMaisVelhoResponseDTO;
import com.projedata.dto.FuncionarioRequestDTO;
import com.projedata.dto.FuncionarioResponseDTO;
import com.projedata.exception.ResourceNotFoundException;
import com.projedata.model.Funcionario;
import com.projedata.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional(readOnly = true)
    public List<FuncionarioResponseDTO> listarTodos(boolean ordenarPorNome) {
        List<Funcionario> funcionarios = ordenarPorNome 
            ? funcionarioRepository.findAllByOrderByNomeAsc()
            : funcionarioRepository.findAll();

        return funcionarios.stream()
            .map(FuncionarioResponseDTO::fromEntity)
            .toList();
    }

    @Transactional(readOnly = true)
    public FuncionarioResponseDTO buscarPorId(Long id) {
        return funcionarioRepository.findById(id)
            .map(FuncionarioResponseDTO::fromEntity)
            .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com o ID: " + id));
    }

    @Transactional
    public FuncionarioResponseDTO salvar(FuncionarioRequestDTO dto) {
        Funcionario funcionario = new Funcionario(
            dto.nome(),
            dto.dataNascimento(),
            dto.salario(),
            dto.funcao()
        );
        Funcionario salvo = funcionarioRepository.save(funcionario);
        return FuncionarioResponseDTO.fromEntity(salvo);
    }

    @Transactional
    public void removerPorId(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Funcionário não encontrado com o ID: " + id);
        }
        funcionarioRepository.deleteById(id);
    }

    @Transactional
    public void removerPorNome(String nome) {
        Funcionario funcionario = funcionarioRepository.findByNomeIgnoreCase(nome)
            .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com o nome: " + nome));
        funcionarioRepository.delete(funcionario);
    }

    @Transactional
    public List<FuncionarioResponseDTO> aplicarReajusteSalarial(BigDecimal percentual) {
        if (percentual == null || percentual.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O percentual de reajuste deve ser positivo.");
        }

        BigDecimal multiplicador = BigDecimal.ONE.add(percentual.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP));

        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        for (Funcionario f : funcionarios) {
            BigDecimal novoSalario = f.getSalario().multiply(multiplicador).setScale(2, RoundingMode.HALF_UP);
            f.setSalario(novoSalario);
        }

        List<Funcionario> atualizados = funcionarioRepository.saveAll(funcionarios);
        return atualizados.stream().map(FuncionarioResponseDTO::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public Map<String, List<FuncionarioResponseDTO>> agruparPorFuncao() {
        return funcionarioRepository.findAll().stream()
            .map(FuncionarioResponseDTO::fromEntity)
            .collect(Collectors.groupingBy(FuncionarioResponseDTO::funcao));
    }

    @Transactional(readOnly = true)
    public List<FuncionarioResponseDTO> buscarAniversariantes(List<Integer> meses) {
        return funcionarioRepository.findAll().stream()
            .filter(f -> meses.contains(f.getDataNascimento().getMonthValue()))
            .map(FuncionarioResponseDTO::fromEntity)
            .toList();
    }

    @Transactional(readOnly = true)
    public FuncionarioMaisVelhoResponseDTO obterFuncionarioMaisVelho() {
        Funcionario maisVelho = funcionarioRepository.findAll().stream()
            .min(Comparator.comparing(Funcionario::getDataNascimento))
            .orElseThrow(() -> new ResourceNotFoundException("Nenhum funcionário cadastrado na base."));

        int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
        return new FuncionarioMaisVelhoResponseDTO(maisVelho.getNome(), idade, maisVelho.getDataNascimento());
    }

    @Transactional(readOnly = true)
    public FolhaEstatisticaResponseDTO obterEstatisticasFolha(BigDecimal salarioMinimoRef) {
        BigDecimal salarioMinimo = (salarioMinimoRef != null && salarioMinimoRef.compareTo(BigDecimal.ZERO) > 0)
            ? salarioMinimoRef 
            : new BigDecimal("1212.00");

        List<Funcionario> funcionarios = funcionarioRepository.findAll();

        BigDecimal totalSalarios = funcionarios.stream()
            .map(Funcionario::getSalario)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<FolhaEstatisticaResponseDTO.SalarioMinimoFuncionarioDTO> relatorio = funcionarios.stream()
            .map(f -> {
                BigDecimal qtd = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
                return new FolhaEstatisticaResponseDTO.SalarioMinimoFuncionarioDTO(f.getNome(), f.getSalario(), qtd);
            })
            .toList();

        return new FolhaEstatisticaResponseDTO(totalSalarios, salarioMinimo, relatorio);
    }
}
