import static org.junit.jupiter.api.Assertions.assertEquals;

import br.lpm.business.Dataset;
import br.lpm.business.DistanceMeasure;
import br.lpm.business.Escolaridade;
import br.lpm.business.EstadoCivil;
import br.lpm.business.Genero;
import br.lpm.business.Hobby;
import br.lpm.business.Moradia;
import br.lpm.business.Pessoa;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

public class DistanceMeasureTest {
  DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private static Dataset dataset;
  private static Pessoa pessoa1;
  private static Pessoa pessoa2;
  private static Pessoa pessoa3;
  private static DistanceMeasure distance;

  @Test
  void testCalcDistance() {
    dataset = new Dataset();
    pessoa1 =
        new Pessoa(
            "Vitor",
            LocalDate.parse("14/09/2005", fmt),
            Genero.MASCULINO,
            1.70f,
            73,
            2000f,
            "Belo Horizonte",
            Hobby.GAME,
            EstadoCivil.SOLTEIRO,
            Escolaridade.SUPERIOR,
            true,
            Moradia.ALUGUEL);
    pessoa2 =
        new Pessoa(
            "Joaquim",
            LocalDate.parse("16/09/2005", fmt),
            Genero.MASCULINO,
            1.70f,
            73,
            2000f,
            "Belo Horizonte",
            Hobby.GAME,
            EstadoCivil.SOLTEIRO,
            Escolaridade.SUPERIOR,
            true,
            Moradia.ALUGUEL);
    pessoa3 =
        new Pessoa(
            "Bia",
            LocalDate.parse("14/09/2002", fmt),
            Genero.FEMININO,
            1.60f,
            60,
            1000f,
            "Belo Horizonte",
            Hobby.ARTE,
            EstadoCivil.CASADO,
            Escolaridade.MEDIO,
            true,
            Moradia.ALUGUEL);
    dataset.addPessoa(pessoa1);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa3);
    distance = new DistanceMeasure(dataset);

    float pessoasIdenticas = distance.calcDistance(pessoa1, pessoa2);
    assertEquals(0, pessoasIdenticas, "Verificando distancia entre Pessoas identicas");

    float pessoasNãoIdenticas = distance.calcDistance(pessoa1, pessoa3);
    assertEquals(
        0.85f, pessoasNãoIdenticas, 0.01d, "Verificando distancia entre pessoas não identicas");
  }
}
