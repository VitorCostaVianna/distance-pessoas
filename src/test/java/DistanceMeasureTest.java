import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DistanceMeasureTest {
  DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private static Dataset dataset;
  private static Pessoa pessoa1;
  private static Pessoa pessoa2;
  private static Pessoa pessoa3;
  private static DistanceMeasure distance;

  @BeforeEach
  public void setUp() throws Exception {
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
  }

  @Test
  void testCalcDistance() {
    float pessoasIdenticas = distance.calcDistance(pessoa1, pessoa2);
    assertEquals(0, pessoasIdenticas, "Verificando distancia entre Pessoas identicas");

    float pessoasNãoIdenticas = distance.calcDistance(pessoa1, pessoa3);
    assertEquals(
        0.85f, pessoasNãoIdenticas, 0.01d, "Verificando distancia entre pessoas não identicas");
  }

  @Test
  void testCalcDistanceMatrix() {
    Float[][] matrizExpect = new Float[][] {
        {0f, 0f, 0.85f},
        {0f, 0f, 0.85f},
        {0.85f, 0.85f, 0f}
    };
    
    Float[][] result = distance.calcDistanceMatrix();
    float delta = 0.01f;     
    for (int i = 0; i < matrizExpect.length; i++) {
       for (int j =0; j < matrizExpect.length; j++){
        assertTrue(Math.abs(matrizExpect[i][j] - result[i][j]) < delta, "Verificando matriz de distâncias na linha " + i + "coluna " + j);
       }
    }
  }

  @Test
  void testCalcDistanceVector() {
    Float[] vectExpect = new Float[]{0f ,0.85f};
    Float[] result = distance.calcDistanceVector(pessoa1);
    float delta = 0.01f;
    
    for (int i = 0; i < vectExpect.length; i++) {
        assertTrue(Math.abs(vectExpect[i] - result[i]) < delta, "Verificando vetor de distâncias no índice " + i);
    }

  }

  @Test
  void testGetSimilar() {
    Pessoa[] Expectedpessoas = new Pessoa[] {pessoa2 ,pessoa3};
    assertEquals(Arrays.asList(Expectedpessoas), Arrays.asList(distance.getSimilar(pessoa1, 2)), "Verificando vetor de pessoas similares");
  }
}
