package br.lpm.main;

import br.lpm.business.Dataset;
import br.lpm.business.Escolaridade;
import br.lpm.business.EstadoCivil;
import br.lpm.business.Genero;
import br.lpm.business.Hobby;
import br.lpm.business.Moradia;
import br.lpm.business.Pessoa;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Main {

  public static Dataset dataset = new Dataset();

  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    ShowMenu();
  }

  public static void ShowMenu() {
    String[] options = {
      "Cadastrar Pessoa",
      "Remover Pessoa",
      "Pesquisar Pessoa",
      "Estatisticas",
      "Exibir Histograma de Formação Acadêmica",
      "Exibir Grafico de torta de distribuição Academica",
      "Sair"
    };

    int escolha;

    do {
      escolha =
          JOptionPane.showOptionDialog(
              null,
              "Escolha uma opção:",
              "Menu Principal",
              JOptionPane.DEFAULT_OPTION,
              JOptionPane.INFORMATION_MESSAGE,
              null,
              options,
              options[0]);

      switch (escolha) {
        case 0:
          cadastrarPessoas();
          break;
        case 1:
          removerPessoa();
          break;
        case 2:
          pesquisarPessoa();
          break;
        case 3:
          exibirEstatisticas();
          break;
        case 4:
          histogramaFormacaoAcadêmica();
          break;
        case 5:
          pieEstadoCivil();
          break;
        case 6:
          JOptionPane.showMessageDialog(null, "Saindo do programa.");
          break;
        default:
          JOptionPane.showMessageDialog(null, "Opção inválida!");
          break;
      }
    } while (escolha != 6);
  }

  public static void cadastrarPessoas() {
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    String nome = JOptionPane.showInputDialog("Digite seu nome: ");

    String dataDeAniversarioAux =
        JOptionPane.showInputDialog("Digite a data de aniversário (dd/MM/yyyy): ");
        LocalDate dataDeAniversario = null;
    if (dataDeAniversarioAux != null) {
      dataDeAniversario = LocalDate.parse(dataDeAniversarioAux, fmt);
    }
    else {
      JOptionPane.showMessageDialog(null, "Nenhuma data foi inserida! ");
    }

    Genero genero =
        Genero.valueOf(
            JOptionPane.showInputDialog(
                "Digite o gênero (MASCULINO, FEMININO, NAO_BINARIO, NAO_RESPONDER): "));

    Float altura = Float.parseFloat(JOptionPane.showInputDialog("Digite a altura (em metros): "));

    int peso = Integer.parseInt(JOptionPane.showInputDialog("Digite o peso (em kg): "));

    float renda = Float.parseFloat(JOptionPane.showInputDialog("Digite a renda: "));

    String naturalidade = JOptionPane.showInputDialog("Digite a naturalidade: ");

    Hobby hobby =
        Hobby.valueOf(
            JOptionPane.showInputDialog(
                "Digite o hobby [ARTE, ESPORTE, CINEMA, LIVRO, MÚSICA, CULINÁRIA, GAME, NENHUM]:"
                    + " ").toUpperCase());

    EstadoCivil estadoCivil =
        EstadoCivil.valueOf(
            JOptionPane.showInputDialog(
                "Digite o estado civil (SOLTEIRO, CASADO, DIVORCIADO, VIUVO, SEPARADO): ").toUpperCase());

    Escolaridade escolaridade =
        Escolaridade.valueOf(
            JOptionPane.showInputDialog(
                    "Digite a escolaridade (FUNDAMENTAL, MEDIO, SUPERIOR, POS_GRADUACAO, NENHUMA):"
                        + " ")
                .toUpperCase());

    boolean feliz =
        Boolean.parseBoolean(JOptionPane.showInputDialog("Você está feliz? (true/false): "));

    Moradia moradia =
        Moradia.valueOf(
            JOptionPane.showInputDialog(
                "Digite o tipo de moradia [COM_FAMILIA, ALUGUEL, CASA_PROPRIA]: ").toUpperCase());

    Pessoa pessoa =
        new Pessoa(
            nome,
            dataDeAniversario,
            genero,
            altura,
            peso,
            renda,
            naturalidade,
            hobby,
            estadoCivil,
            escolaridade,
            feliz,
            moradia);
    dataset.addPessoa(pessoa);
    JOptionPane.showMessageDialog(
        null, pessoa, "Pessoa Cadastrada", JOptionPane.INFORMATION_MESSAGE);
  }

  public static void removerPessoa() {
    String pessoa = JOptionPane.showInputDialog("Digite o nome da pessoa que deseja remover: ");
    dataset.removePessoaByName(pessoa);
    JOptionPane.showMessageDialog(null, null, "Pessoa removida", 0);
  }

  public static void pesquisarPessoa() {
    String pessoa = JOptionPane.showInputDialog("Digite o nome da pessoa que deseja encontrar: ");
    Pessoa pessoaEncontrada = dataset.getPessoaByName(pessoa);

    if (pessoaEncontrada != null) {
      JOptionPane.showMessageDialog(
          null, pessoaEncontrada, "Pessoa Encontrada", JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(
          null, "Pessoa não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
    }
  }

  private static void exibirEstatisticas() {
    String estatisticas =
        "Estatísticas:\n"
            + "Altura Máxima: "
            + dataset.maxAltura()
            + " m\n"
            + "Altura Média: "
            + dataset.avgAltura()
            + " m\n"
            + "Altura Mínima: "
            + dataset.minAltura()
            + " m\n"
            + "Peso Máximo: "
            + dataset.maxPeso()
            + " kg\n"
            + "Peso Mínimo: "
            + dataset.minPeso()
            + " kg\n"
            + "Peso Médio: "
            + dataset.avgPeso()
            + " kg\n"
            + "Porcentagem de Adultos: "
            + dataset.percentAdult()
            + "%\n"
            + "Moda da Moradia: "
            + dataset.modeMoradia()
            + "\n"
            + "Moda da Escolaridade: "
            + dataset.modeEscolaridade()
            + "\n"
            + "Moda do Estado Civil: "
            + dataset.modeEstadoCivil()
            + "\n"
            + "Porcentagem de Pessoas Felizes: "
            + dataset.percentFeliz()
            + "%";

    JOptionPane.showMessageDialog(
        null, estatisticas, "Estatísticas", JOptionPane.INFORMATION_MESSAGE);
  }

  public static void histogramaFormacaoAcadêmica() {

    Pessoa[] listaPessoas = dataset.getAll();
    Escolaridade[] tipoEscolaridades = Escolaridade.values();
    int[] contEscolaridades = new int[Escolaridade.values().length];

    for (int i = 0; i < dataset.size(); i++) {
      Escolaridade escolaridade = listaPessoas[i].getEscolaridade();
      for (int j = 0; j < contEscolaridades.length; j++) {
        if (tipoEscolaridades[j] == escolaridade) {
          contEscolaridades[j]++;
        }
      }
    }

    DefaultCategoryDataset datasetGrafico = new DefaultCategoryDataset();
    for (int i = 0; i < contEscolaridades.length; i++) {
      datasetGrafico.addValue(
          contEscolaridades[i], "Formação Acadêmica", tipoEscolaridades[i].name());
    }

    JFreeChart grafico =
        ChartFactory.createBarChart(
            "Distribuição de Formação Acadêmica",
            "Formação Acadêmica",
            "Número de Pessoas",
            datasetGrafico,
            PlotOrientation.VERTICAL,
            true,
            true,
            false);

    ChartPanel painelGrafico = new ChartPanel(grafico);
    painelGrafico.setPreferredSize(new java.awt.Dimension(400, 300));

    JFrame frameGrafico = new JFrame();
    frameGrafico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frameGrafico.add(painelGrafico);
    frameGrafico.pack();
    frameGrafico.setLocationRelativeTo(null);

    JOptionPane.showMessageDialog(
        null, frameGrafico.getContentPane(), "Histograma", JOptionPane.PLAIN_MESSAGE);
  }

  public static void pieEstadoCivil() {

    DefaultPieDataset<String> datasetGrafico = new DefaultPieDataset<>();
    datasetGrafico.setValue("Solteiro", dataset.percentEstadoCivil(EstadoCivil.SOLTEIRO));
    datasetGrafico.setValue("Casado", dataset.percentEstadoCivil(EstadoCivil.CASADO));
    datasetGrafico.setValue("Divorciado", dataset.percentEstadoCivil(EstadoCivil.DIVORCIADO));
    datasetGrafico.setValue("Separado", dataset.percentEstadoCivil(EstadoCivil.SEPARADO));
    datasetGrafico.setValue("Viúvo", dataset.percentEstadoCivil(EstadoCivil.VIUVO));

    JFreeChart grafico =
        ChartFactory.createPieChart(
            "Distribuição dos Estados Civis", datasetGrafico, true, true, false);

    ChartPanel painelGrafico = new ChartPanel(grafico);
    painelGrafico.setPreferredSize(new java.awt.Dimension(400, 300));

    JFrame frameGrafico = new JFrame();
    frameGrafico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frameGrafico.add(painelGrafico);
    frameGrafico.pack();
    frameGrafico.setLocationRelativeTo(null);

    JOptionPane.showMessageDialog(
        null, frameGrafico.getContentPane(), "Pie Chart", JOptionPane.PLAIN_MESSAGE);
  }
}
