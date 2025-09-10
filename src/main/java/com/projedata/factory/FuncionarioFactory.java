package main.java.com.projedata.factory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import main.java.com.projedata.model.Funcionario;

/**
 * A factory class for creating {@link Funcionario} objects.
 */
public class FuncionarioFactory {

  /**
   * Creates a list of {@link Funcionario} objects from a list of raw data arrays.
   *
   * @param dados A list of object arrays, where each array contains the data for one employee.
   *              The expected order of data in the inner array is:
   *              <ul>
   *                <li>[0]: Name (String)</li>
   *                <li>[1]: Year of birth (int)</li>
   *                <li>[2]: Month of birth (int)</li>
   *                <li>[3]: Day of birth (int)</li>
   *                <li>[4]: Salary (String)</li>
   *                <li>[5]: Function/Role (String)</li>
   *              </ul>
   * @return A list of {@link Funcionario} objects.
   */
  public static List<Funcionario> criarFuncionarios(List<Object[]> dados) {
    List<Funcionario> funcionarios = new ArrayList<>();

    for (Object[] d : dados) {
      funcionarios.add(new Funcionario(
          (String) d[0],               // nome
          LocalDate.of((int) d[1], (int) d[2], (int) d[3]), // ano, mes, dia
          new BigDecimal((String) d[4]), // salario
          (String) d[5]                 // função
      ));
    }
    return funcionarios;
  }
}
