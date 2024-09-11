import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.lpm.business.Dataset;
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

public class DatasetTest {
  DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  public static Pessoa pessoa;
  public static Pessoa pessoa2;
  public static Dataset dataset;

  @BeforeEach
  public void setUp() throws Exception {
    pessoa = new Pessoa();
    pessoa.setNome("Beatriz");

    pessoa2 = new Pessoa();
    pessoa2.setNome("Vitor");

    dataset = new Dataset();
    dataset.addPessoa(pessoa);
    dataset.addPessoa(pessoa2);
  }

  @Test
  void testAddPessoa() {
    dataset.addPessoa(new Pessoa());
    assertEquals(3, dataset.size(), "Adicionando dentro do limite de MAX_PESSOA = 3 ");

    dataset.addPessoa(new Pessoa());
    assertEquals(3, dataset.size(), "Adicionando fora do limite de MAX_PESSOA");
  }

  @Test
  void testGetPessoaByName() {
    assertEquals(pessoa, dataset.getPessoaByName(pessoa.getNome()), "Testando pessoa existente");
    assertEquals(null, dataset.getPessoaByName("Arlindo"), "Testando pessoa não existente");
  }

  @Test
  void testRemovePessoa() {
    dataset.removePessoa(pessoa2);
    assertEquals(1, dataset.size(), "Testando o tamanho do vetor apos a remoção por parametro");
    assertEquals(
        null,
        dataset.getPessoaByName(pessoa2.getNome()),
        "Testando se a pessoa passada por parametro foi removida");
  }

  @Test
  void testRemovePessoaByName() {
    dataset.removePessoaByName(pessoa2.getNome());
    assertEquals(1, dataset.size(), "Testando o tamanho do vetor apos a remoção por nome");
    assertEquals(
        null,
        dataset.getPessoaByName(pessoa2.getNome()),
        "Testando se a pessoa passada por nome foi removida");
  }

  @Test
  void testReplacePessoa() {
    Pessoa pessoaNova = new Pessoa();
    pessoaNova.setNome("Joaquim");

    dataset.replacePessoa(pessoa, pessoaNova);

    assertEquals(2, dataset.size(), "Teste se o tamanho permaneceu o mesmo");

    assertEquals(
        pessoaNova,
        dataset.getPessoaByName(pessoaNova.getNome()),
        "Testando se a pessoa nova esta no vetor ");
    assertEquals(
        null,
        dataset.getPessoaByName(pessoa.getNome()),
        "Testando se a pessoa original foi substituida");
  }

  @Test
  void testRemoveAll() {
    dataset.removeAll();
    assertEquals(0, dataset.size(), "Verificando se o tamanho foi zerado");
    assertEquals(
        null, dataset.getPessoaByName(pessoa.getNome()), "Verificando se a pessoa foi removida");
  }

  @Test
  void testMaxAltura() {
    pessoa.setAltura(1.80f);
    pessoa2.setAltura(1.70f);
    assertEquals(pessoa.getAltura(), dataset.maxAltura(), "Verificando maxima altura");
  }

  @Test
  void testMinAltura() {
    pessoa.setAltura(1.80f);
    pessoa2.setAltura(1.70f);
    assertEquals(pessoa2.getAltura(), dataset.minAltura(), "Verificando minima altura");
  }

  @Test
  void testAvgAltura() {
    pessoa.setAltura(1.80f);
    pessoa.setAltura(1.70f);
    assertEquals(1.75f, dataset.avgAltura(), (float) 0.5d, "Teste media altura");
  }

  @Test
  void testMaxPeso() {
    pessoa.setPeso(80);
    pessoa2.setPeso(90);
    assertEquals(pessoa2.getPeso(), dataset.maxPeso(), "Verificando peso maximo");
  }

  @Test
  void testMinPeso() {
    pessoa.setPeso(80);
    pessoa2.setPeso(90);
    assertEquals(pessoa.getPeso(), dataset.minPeso(), "Verificando peso minimo");
  }

  @Test
  void testAvgPeso() {
    pessoa.setPeso(80);
    pessoa2.setPeso(90);
    assertEquals(85, dataset.avgPeso(), "Verificando peso medio");
  }

  @Test
  void testModeEscolaridade() {
    Pessoa pessoa3 = new Pessoa();
    dataset.addPessoa(pessoa3);
    pessoa.setEscolaridade(Escolaridade.SUPERIOR);
    pessoa2.setEscolaridade(Escolaridade.SUPERIOR);
    pessoa3.setEscolaridade(Escolaridade.FUNDAMENTAL);
    assertEquals(
        Escolaridade.SUPERIOR, dataset.modeEscolaridade(), "Verificando moda escolaridade");
  }

  @Test
  void testModeEstadoCivil() {
    Pessoa pessoa3 = new Pessoa();
    dataset.addPessoa(pessoa3);
    pessoa.setEstadoCivil(EstadoCivil.CASADO);
    pessoa2.setEstadoCivil(EstadoCivil.CASADO);
    pessoa3.setEstadoCivil(EstadoCivil.SEPARADO);
    assertEquals(EstadoCivil.CASADO, dataset.modeEstadoCivil(), "Verificando moda Estado Civil");
  }

  @Test
  void testModeMoradia() {
    Pessoa pessoa3 = new Pessoa();
    dataset.addPessoa(pessoa3);
    pessoa.setMoradia(Moradia.COM_FAMILIA);
    pessoa2.setMoradia(Moradia.COM_FAMILIA);
    pessoa3.setMoradia(Moradia.ALUGUEL);
    assertEquals(Moradia.COM_FAMILIA, dataset.modeMoradia(), "Verificando moda Moradia");
  }

  @Test
  void testPercentAdult() {
    pessoa.setDataDeNascimento(LocalDate.parse("20/07/2002", fmt));
    pessoa2.setDataDeNascimento(LocalDate.parse("14/09/2005", fmt));
    assertEquals(100f, dataset.percentAdult(), "Verificando porcentagem de Adultos");

    pessoa.setDataDeNascimento(LocalDate.parse("20/07/2010", fmt));
    pessoa2.setDataDeNascimento(LocalDate.parse("14/09/2015", fmt));
    assertEquals(0, dataset.percentAdult(), "Verificando porcntagem sem adultos no vetor");
  }

  @Test
  void testPercentEscolaridade() {
    Pessoa pessoa3 = new Pessoa();
    dataset.addPessoa(pessoa3);
    pessoa.setEscolaridade(Escolaridade.SUPERIOR);
    pessoa2.setEscolaridade(Escolaridade.SUPERIOR);
    pessoa3.setEscolaridade(Escolaridade.FUNDAMENTAL);
    assertEquals(
        66.6f,
        dataset.percentEscolaridade(Escolaridade.SUPERIOR),
        1.0d,
        "Verificando porcentagem escolaridade");

    assertEquals(
        0f,
        dataset.percentEscolaridade(Escolaridade.POS_GRADUACAO),
        "Verificando porcentagem de escolaridade não presente no vetor");
  }

  @Test
  void testPercentEstadoCivil() {
    Pessoa pessoa3 = new Pessoa();
    dataset.addPessoa(pessoa3);
    pessoa.setEstadoCivil(EstadoCivil.CASADO);
    pessoa2.setEstadoCivil(EstadoCivil.CASADO);
    pessoa3.setEstadoCivil(EstadoCivil.SEPARADO);
    assertEquals(
        66.6f,
        dataset.percentEstadoCivil(EstadoCivil.CASADO),
        1.0d,
        "Verificando porcentagem Estado Civil");

    assertEquals(
        0f,
        dataset.percentEstadoCivil(EstadoCivil.DIVORCIADO),
        "Verificando porcentagem de estado Civil não presente no vetor");
  }

  @Test
  void testPercentFeliz() {
    pessoa.setFeliz(true);
    pessoa2.setFeliz(false);
    assertEquals(50f, dataset.percentFeliz(), 1.0d, "Verificando porcentagem Feliz");

    pessoa.setFeliz(false);
    assertEquals(
        0f, dataset.percentFeliz(), 1.0d, "Verificando porcentagem quando ninguem é Feliz");
  }

  @Test
  void testPercentHobby() {
    Pessoa pessoa3 = new Pessoa();
    dataset.addPessoa(pessoa3);
    pessoa.setHobby(Hobby.ESPORTE);
    pessoa2.setHobby(Hobby.ESPORTE);
    pessoa3.setHobby(Hobby.GAME);
    assertEquals(100, dataset.percentHobby(), 1.0d, "Verificando porcentagem com Hobby");

    pessoa.setHobby(Hobby.NENHUM);
    pessoa2.setHobby(Hobby.NENHUM);
    pessoa3.setHobby(Hobby.NENHUM);
    assertEquals(0f, dataset.percentHobby(), "Verificando porcentagem sem hobby");
  }

  @Test
  void testPercentMoradia() {
    Pessoa pessoa3 = new Pessoa();
    dataset.addPessoa(pessoa3);
    pessoa.setMoradia(Moradia.ALUGUEL);
    pessoa2.setMoradia(Moradia.ALUGUEL);
    pessoa3.setMoradia(Moradia.COM_FAMILIA);
    assertEquals(
        66.6F, dataset.percentMoradia(Moradia.ALUGUEL), 1.0d, "Verificando porcentagem moradia");

    assertEquals(
        0f,
        dataset.percentMoradia(Moradia.CASA_PROPRIA),
        "Verificando porcentagem de MORADIA não presente no vetor");
  }

  @Test
  void testSize() {
    assertEquals(2, dataset.size());
  }

  @Test
  void testNormalizeField() {
    dataset.removeAll();
    Pessoa pessoa1 =
        new Pessoa(
            "Vitor",
            LocalDate.parse("14/09/2005", fmt),
            Genero.MASCULINO,
            1.70f,
            75,
            2000f,
            "Belo Horizonte",
            Hobby.GAME,
            EstadoCivil.SOLTEIRO,
            Escolaridade.SUPERIOR,
            true,
            Moradia.ALUGUEL);
    Pessoa pessoa2 =
        new Pessoa(
            "Joaquim",
            LocalDate.parse("16/09/2005", fmt),
            Genero.MASCULINO,
            1.70f,
            75,
            2000f,
            "Belo Horizonte",
            Hobby.GAME,
            EstadoCivil.SOLTEIRO,
            Escolaridade.SUPERIOR,
            true,
            Moradia.ALUGUEL);
    Pessoa pessoa3 =
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

    Float[] normalizedFieldPeso = dataset.normalizeField("peso");
    Float[] expectedPesos = {1.0f, 1.0f, 0.0f};
    assertArrayEquals(expectedPesos, normalizedFieldPeso, "Verificando normalização do peso");
  }

  @Test
  void testCalcDistanceMatrix() {
    dataset.removeAll();
    Pessoa pessoa1 =
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
    Pessoa pessoa2 =
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
    Pessoa pessoa3 =
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

    Float[][] matrizExpect =
        new Float[][] {
          {0f, 0f, 0.85f},
          {0f, 0f, 0.85f},
          {0.85f, 0.85f, 0f}
        };

    Float[][] result = dataset.calcDistanceMatrix();
    float delta = 0.01f;
    for (int i = 0; i < matrizExpect.length; i++) {
      for (int j = 0; j < matrizExpect.length; j++) {
        assertTrue(
            Math.abs(matrizExpect[i][j] - result[i][j]) < delta,
            "Verificando matriz de distâncias na linha " + i + "coluna " + j);
      }
    }
  }

  @Test
  void testCalcDistanceVector() {
    dataset.removeAll();
    Pessoa pessoa1 =
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
    Pessoa pessoa2 =
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
    Pessoa pessoa3 =
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

    Float[] vectExpect = new Float[] {0f, 0.85f};
    Float[] result = dataset.calcDistanceVector(pessoa1);
    float delta = 0.01f;

    for (int i = 0; i < vectExpect.length; i++) {
      assertTrue(
          Math.abs(vectExpect[i] - result[i]) < delta,
          "Verificando vetor de distâncias no índice " + i);
    }
  }

  @Test
  void testGetSimilar() {
    dataset.removeAll();
    Pessoa pessoa1 =
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
    Pessoa pessoa2 =
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
    Pessoa pessoa3 =
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
    Pessoa pessoa4 =
        new Pessoa(
            "Boulos",
            LocalDate.parse("14/09/2000", fmt),
            Genero.MASCULINO,
            1.90f,
            90,
            2000f,
            "Belo Horizonte",
            Hobby.ARTE,
            EstadoCivil.CASADO,
            Escolaridade.MEDIO,
            false,
            Moradia.COM_FAMILIA);
    dataset.addPessoa(pessoa3);
    dataset.addPessoa(pessoa2);
    dataset.addPessoa(pessoa4);
    dataset.addPessoa(pessoa1);

    Pessoa[] Expectedpessoas = new Pessoa[] {pessoa2, pessoa3, pessoa4};
    assertEquals(
        Arrays.asList(Expectedpessoas),
        Arrays.asList(dataset.getSimilar(pessoa1, 3)),
        "Verificando vetor de pessoas similares");
  }
}
