package main.java.com.projedata;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import main.java.com.projedata.model.Employee;

public class ProjedataApplication  {
  public static void main(String[] args) {
    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    NumberFormat formatterSalario = NumberFormat.getInstance(new Locale("pt", "BR"));

    List<Employee> employees = new ArrayList<>();

    // 3.1 – Inserir funcionáriosa
    employees.add(new Employee("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
    employees.add(new Employee("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
    employees.add(new Employee("Caio", LocalDate.of(1961, 5, 12), new BigDecimal("9836.14"), "Coordenador"));
    employees.add(new Employee("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
    employees.add(new Employee("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
    employees.add(new Employee("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
    employees.add(new Employee("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
    employees.add(new Employee("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
    employees.add(new Employee("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
    employees.add(new Employee("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
// teste
    // 3.2 – Remover funcionário "João"
    employees.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

    // 3.3 – Imprimir todos os funcionários formatados
    System.out.println("Lista de Funcionários:");
    employees.forEach(f -> {
      System.out.println("Nome: " + f.getNome() +
          ", Data Nascimento: " + f.getDataNascimento().format(formatterData) +
          ", Função: " + f.getFuncao() +
          ", Salário: R$ " + formatterSalario.format(f.getSalario()));
    });

    // 3.4 – Aumento de 10%
    employees.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.1"))));

    // 3.5 – Agrupar por função
    Map<String, List<Employee>> agrupadosPorFuncao = employees.stream()
        .collect(Collectors.groupingBy(Employee::getFuncao));

    // 3.6 – Imprimir agrupados por função
    System.out.println("\nFuncionários por Função:");
    agrupadosPorFuncao.forEach((funcao, lista) -> {
      System.out.println("Função: " + funcao);
      lista.forEach(f -> System.out.println(" - " + f.getNome()));
    });

    // 3.8 – Aniversariantes dos meses 10 e 12
    System.out.println("\nAniversariantes dos meses 10 e 12:");
    employees.stream()
        .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
        .forEach(f -> System.out.println(f.getNome() + " - " + f.getDataNascimento().format(formatterData)));

    // 3.9 – Funcionário com maior idade
    Employee maisVelho = employees.stream()
        .min(Comparator.comparing(Employee::getDataNascimento))
        .orElse(null);
    if (maisVelho != null) {
      int idade = LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear();
      System.out.println("\nFuncionário mais velho: " + maisVelho.getNome() + " - " + idade + " anos");
    }

    // 3.10 – Lista por ordem alfabética
    System.out.println("\nFuncionários por ordem alfabética:");
    employees.stream()
        .sorted(Comparator.comparing(Employee::getNome))
        .forEach(f -> System.out.println(f.getNome()));

    // 3.11 – Total de salários
    BigDecimal totalSalarios = employees.stream()
        .map(Employee::getSalario)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    System.out.println("\nTotal de salários: R$ " + formatterSalario.format(totalSalarios));

    // 3.12 – Quantos salários mínimos cada funcionário ganha
    BigDecimal salarioMinimo = new BigDecimal("1212");
    System.out.println("\nSalários mínimos por funcionário:");
    employees.forEach(f -> {
      BigDecimal qtde = f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
      System.out.println(f.getNome() + ": " + qtde + " salários mínimos");
    });
  }
}
