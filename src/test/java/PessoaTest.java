import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.lpm.business.Pessoa;

public class PessoaTest {
  DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  public static Pessoa pessoa;

  @BeforeEach
  public void setUp() throws Exception {
    pessoa = new Pessoa();
  }

  @Test
  void testSetNome() {
    pessoa.setNome("Vitor");
    assertEquals("Vitor", pessoa.getNome(), "Atribuição de nome válido");

    pessoa.setNome("Vitor144");
    assertEquals("Vitor", pessoa.getNome(), "Atribuição de nome inválido");
  }

  @Test
  void testSetDataDeNascimento() {
    LocalDate dataDeAniversario = LocalDate.parse("14/09/2005", fmt);
    pessoa.setDataDeNascimento(dataDeAniversario);
    assertEquals(
        "14/09/2005",
        pessoa.getDataDeNascimento().format(fmt),
        "Atribuição da Data de Nascimento válida");

    LocalDate invalidDate = LocalDate.now().plusDays(1);
    pessoa.setDataDeNascimento(invalidDate);
    assertEquals(
        dataDeAniversario,
        pessoa.getDataDeNascimento(),
        "Atribuição da Data de Nascimento inválida");
  }

  @Test
  void testSetAltura() {
    pessoa.setAltura(1.70f);
    assertEquals(1.70f, pessoa.getAltura(), "Atribuição de altura válida");

    pessoa.setAltura(2.80f);
    assertEquals(1.70f, pessoa.getAltura(), "Atribuição de altura inválida");
  }

  @Test
  void testSetPeso() {
    pessoa.setPeso(80);
    assertEquals(80, pessoa.getPeso(), "Atribuição de peso válida limite inferior");

    pessoa.setPeso(0);
    assertEquals(80, pessoa.getPeso(), "Atribuição de peso inválida");
  }

  @Test
  void testSetRenda() {
    pessoa.setRenda(50000f);
    assertEquals(50000f, pessoa.getRenda(), "Atribuição de renda válida");

    pessoa.setRenda(-1f);
    assertEquals(50000f, pessoa.getRenda(), "Atribuição de renda inválida");
  }
}
