package main.java.com.projedata.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

// 2 – Classe Funcionário que estenda a classe Pessoa, com os atributos: salário (BigDecimal) e função (String).
public class Funcionario extends Pessoa {
  private BigDecimal salario;
  private String funcao;


  public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
    super(nome, dataNascimento);
    this.salario = salario;
    this.funcao = funcao;
  }

  // Getters e Setters
  public BigDecimal getSalario() { return salario; }

  public void setSalario(BigDecimal salario) { this.salario = salario; }

  public String getFuncao() { return funcao; }

  public void setFuncao(String funcao) { this.funcao = funcao; }

  @Override
  public String toString() {
    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    NumberFormat formatterSalario = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));

    return "Nome: " + getNome() +
        ", Data Nascimento: " + getDataNascimento().format(formatterData) +
        ", Função: " + getFuncao() +
        ", Salário: " + formatterSalario.format(getSalario());
  }
}
