package com.projedata.repository;

import com.projedata.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findByNomeIgnoreCase(String nome);

    void deleteByNomeIgnoreCase(String nome);

    List<Funcionario> findAllByOrderByNomeAsc();
}
