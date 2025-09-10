package main.java.com.projedata;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import main.java.com.projedata.model.Funcionario;

/**
 * Main class for the Projedata application.
 * This class contains the main method, which performs operations for handling
 * a list of employees.
 */
public class ProjedataApplication {
  /**
   * The main method of the application.
   *
   * @param args The command line arguments (not used).
   */
  public static void main(String[] args) {
    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    NumberFormat formatterSalario = NumberFormat.getInstance(Locale.of("pt", "BR"));

    List<Funcionario> funcionarios = new ArrayList<>();

    // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
    funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
    funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
    funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 12), new BigDecimal("9836.14"), "Coordenador"));
    funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
    funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
    funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
    funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
    funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
    funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
    funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

    // 3.2 – Remover o funcionário “João” da lista.
    funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

    // 3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
    //• informação de data deve ser exibido no formato dd/mm/aaaa;
    //• informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula
    System.out.println("Lista de Funcionários:");
    funcionarios.forEach(f -> System.out.println("Nome: " + f.getNome() +
        ", Data Nascimento: " + f.getDataNascimento().format(formatterData) +
        ", Função: " + f.getFuncao() +
        ", Salário: R$ " + formatterSalario.format(f.getSalario())));

    // 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
    funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.1"))));

    // 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
    Map<String, List<Funcionario>> agrupadosPorFuncao = funcionarios.stream()
        .collect(Collectors.groupingBy(Funcionario::getFuncao));

    // 3.6 – Imprimir os funcionários, agrupados por função.
    System.out.println("\nFuncionários por Função");
    agrupadosPorFuncao.forEach((funcao, lista) -> {
      System.out.println(funcao + ":");
      lista.forEach(f -> System.out.println(" - " + f.getNome()));
    });

    // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
    System.out.println("\nAniversariantes dos meses 10 e 12:");
    funcionarios.stream()
        .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
        .forEach(f -> System.out.println(f.getNome() + " - " + f.getDataNascimento().format(formatterData)));

    // 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
    Funcionario maisVelho = funcionarios.stream()
        .min(Comparator.comparing(Funcionario::getDataNascimento))
        .orElse(null);
    if (maisVelho != null) {
      Period periodo = Period.between(maisVelho.getDataNascimento(), LocalDate.now());
      int idade = periodo.getYears();

      System.out.println("\nFuncionário mais velho:\n" + maisVelho.getNome() + " - " + idade + " anos");
    }

    // 3.10 – Imprimir a lista de funcionários por ordem alfabética.
    System.out.println("\nFuncionários por ordem alfabética:");
    funcionarios.stream()
        .sorted(Comparator.comparing(Funcionario::getNome))
        .forEach(f -> System.out.println(f.getNome()));

    // 3.11 – Imprimir o total dos salários dos funcionários.
    BigDecimal totalSalarios = funcionarios.stream()
        .map(Funcionario::getSalario)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    System.out.println("\nTotal de salários:\nR$ " + formatterSalario.format(totalSalarios));

    // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$ 1212.00.
    BigDecimal salarioMinimo = new BigDecimal("1212");
    System.out.println("\nSalários mínimos por funcionário:");
    funcionarios.forEach(f -> {
      BigDecimal qtdSalariosMinimos = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
      System.out.println(f.getNome() + ": " + qtdSalariosMinimos + " salários mínimos");
    });
  }
}
