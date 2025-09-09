package main.java.com.projedata.model;

import java.math.BigDecimal;

public class Employee extends Person {
  private BigDecimal salario;
  private String funcao;

  public Employee(String nome, java.time.LocalDate dataNascimento, BigDecimal salario, String funcao) {
    super(nome, dataNascimento);
    this.salario = salario;
    this.funcao = funcao;
  }

  // Getters e Setters
  public BigDecimal getSalario() { return salario; }
  public void setSalario(BigDecimal salario) { this.salario = salario; }

  public String getFuncao() { return funcao; }
  public void setFuncao(String funcao) { this.funcao = funcao; }
}
