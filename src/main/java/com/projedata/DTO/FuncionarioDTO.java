package main.java.com.projedata.DTO;

/**
 * A Data Transfer Object (DTO) for employee data.
 * This record is used to encapsulate the raw data for creating a Funcionario object.
 *
 * @param nome    The employee's name.
 * @param ano     The year of the employee's birth.
 * @param mes     The month of the employee's birth.
 * @param dia     The day of the employee's birth.
 * @param salario The employee's salary as a String.
 * @param funcao  The employee's job function or role.
 */
public record FuncionarioDTO(
    String nome,
    int ano,
    int mes,
    int dia,
    String salario,
    String funcao
) {}
