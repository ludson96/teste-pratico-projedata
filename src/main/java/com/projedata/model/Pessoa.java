package main.java.com.projedata.model;

import java.time.LocalDate;

/**
 * Represents a person with a name and date of birth.
 */
public class Pessoa {

  /**
   * The person's name.
   */

  private String nome;
  /**
   * The person's date of birth.
   */

  private LocalDate dataNascimento;

  /**
   * Constructs a new Pessoa object.
   *
   * @param nome           The person's name.
   * @param dataNascimento The person's date of birth.
   */
  public Pessoa(String nome, LocalDate dataNascimento) {
    this.nome = nome;
    this.dataNascimento = dataNascimento;
  }

  /**
   * Gets the person's name.
   *
   * @return The name as a String.
   */
  public String getNome() {
    return nome;
  }

  /**
   * Sets the person's name.
   *
   * @param nome The new name to set.
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Gets the person's date of birth.
   *
   * @return The date of birth as a LocalDate.
   */
  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  /**
   * Sets the person's date of birth.
   *
   * @param dataNascimento The new date of birth to set.
   */
  public void setDataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
  }
}
