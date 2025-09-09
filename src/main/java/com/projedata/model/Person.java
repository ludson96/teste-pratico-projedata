package main.java.com.projedata.model;

import java.time.LocalDate;

public class Person {
  private String nome;
  private LocalDate dataNascimento;

  public Person(String nome, LocalDate dataNascimento) {
    this.nome = nome;
    this.dataNascimento = dataNascimento;
  }

  // Getters e Setters
  public String getNome() { return nome; }
  public void setNome(String nome) { this.nome = nome; }

  public LocalDate getDataNascimento() { return dataNascimento; }
  public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
}
