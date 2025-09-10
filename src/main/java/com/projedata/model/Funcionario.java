package main.java.com.projedata.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents an employee, extending the Pessoa class.
 * This class includes information about the employee's salary and job function.
 */
public class Funcionario extends Pessoa {

  /**
   * The employee's salary.
   */
  private BigDecimal salario;

  /**
   * The employee's job function or role.
   */
  private String funcao;

  /**
   * Constructs a new Funcionario object.
   *
   * @param nome           The employee's name.
   * @param dataNascimento The employee's date of birth.
   * @param salario        The employee's salary.
   * @param funcao         The employee's job function.
   */
  public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
    super(nome, dataNascimento);
    this.salario = salario;
    this.funcao = funcao;
  }

  /**
   * Gets the employee's salary.
   *
   * @return The salary as a BigDecimal.
   */
  public BigDecimal getSalario() {
    return salario;
  }

  /**
   * Sets the employee's salary.
   *
   * @param salario The new salary to set.
   */
  public void setSalario(BigDecimal salario) {
    this.salario = salario;
  }

  /**
   * Gets the employee's job function.
   *
   * @return The job function as a String.
   */
  public String getFuncao() {
    return funcao;
  }

  /**
   * Sets the employee's job function.
   *
   * @param funcao The new job function to set.
   */
  public void setFuncao(String funcao) {
    this.funcao = funcao;
  }
}
