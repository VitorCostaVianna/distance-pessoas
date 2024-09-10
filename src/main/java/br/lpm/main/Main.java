package br.lpm.main;

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

  public static Dataset dataset;
  public static DistanceMeasure distance;
  static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public static void main(String[] args) {
    Locale.setDefault(Locale.US);

    cadastrarDataset();

    Pessoa pessoaConsulta = cadastrarPessoa();
    JOptionPane.showMessageDialog(
        null, pessoaConsulta, "Pessoa cadastrada para consulta", JOptionPane.INFORMATION_MESSAGE);

    exibePessoasSimilares(pessoaConsulta, 3);
    ShowMenu();
  }

  public static void ShowMenu() {
    String[] options = {
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
          pesquisarPessoa();
          break;
        case 1:
          exibirEstatisticas();
          break;
        case 2:
          histogramaFormacaoAcadêmica();
          break;
        case 3:
          pieEstadoCivil();
          break;
        case 4:
          JOptionPane.showMessageDialog(null, "Saindo do programa.");
          break;
        default:
          JOptionPane.showMessageDialog(null, "Opção inválida!");
          break;
      }
    } while (escolha != 4);
  }

  private static void cadastrarDataset() {
    dataset = new Dataset();
    int qtdePessoasDataset = obterQuantidadePessoas();
    for (int i = 0; i < qtdePessoasDataset; i++) {
      Pessoa newPessoa = cadastrarPessoa();
      dataset.addPessoa(newPessoa);
    }
  }

  private static Integer obterQuantidadePessoas() {
    Integer qtdePessoasDatasetAux =
        Integer.parseInt(
            (JOptionPane.showInputDialog(
                "Digite a quantidade de pessoas que deseja cadastrar na base de dados: ")));
    while (qtdePessoasDatasetAux == null) {
      qtdePessoasDatasetAux =
          Integer.parseInt(
              (JOptionPane.showInputDialog(
                  "Digite a quantidade de pessoas que deseja cadastrar na base de dados: ")));
    }
    int qtdePessoasDataset = qtdePessoasDatasetAux;
    return qtdePessoasDataset;
  }

  public static Pessoa cadastrarPessoa() {
    String nomeAux = JOptionPane.showInputDialog("Digite seu nome: ");
    while (nomeAux == null) {
      nomeAux = JOptionPane.showInputDialog("Digite seu nome: ");
    }
    String nome = nomeAux;

    String dataDeAniversarioAux =
        JOptionPane.showInputDialog("Digite a data de aniversário (dd/MM/yyyy): ");
    while (dataDeAniversarioAux == null) {
      dataDeAniversarioAux =
          JOptionPane.showInputDialog("Insira a data de aniversário (dd/MM/yyyy): ");
    }
    LocalDate dataDeAniversario = LocalDate.parse(dataDeAniversarioAux, fmt);

    Genero generoAux =
        Genero.valueOf(
            JOptionPane.showInputDialog(
                    "Digite o gênero (MASCULINO, FEMININO, NAO_BINARIO, NAO_RESPONDER): ")
                .toUpperCase());
    while (generoAux == null) {
      generoAux =
          Genero.valueOf(
              JOptionPane.showInputDialog(
                      "Digite um gênero (MASCULINO, FEMININO, NAO_BINARIO, NAO_RESPONDER): ")
                  .toUpperCase());
    }
    Genero genero = generoAux;

    Float alturaAux =
        Float.parseFloat(JOptionPane.showInputDialog("Digite a altura (em metros): "));
    while (alturaAux == null) {
      alturaAux = Float.parseFloat(JOptionPane.showInputDialog("Digite uma altura (em metros): "));
    }
    float altura = alturaAux;

    Integer pesoAux = Integer.parseInt(JOptionPane.showInputDialog("Digite o peso (em kg): "));
    while (pesoAux == null) {
      pesoAux = Integer.parseInt(JOptionPane.showInputDialog("Digite o peso (em kg): "));
    }
    int peso = pesoAux;

    Float rendaAux = Float.parseFloat(JOptionPane.showInputDialog("Digite a renda: "));
    while (rendaAux == null) {
      rendaAux = Float.parseFloat(JOptionPane.showInputDialog("Digite a renda: "));
    }
    float renda = rendaAux;

    String naturalidadeAux = JOptionPane.showInputDialog("Digite a naturalidade: ");
    while (naturalidadeAux == null) {
      naturalidadeAux = JOptionPane.showInputDialog("Digite a naturalidade: ");
    }
    String naturalidade = naturalidadeAux;

    Hobby hobbyAux =
        Hobby.valueOf(
            JOptionPane.showInputDialog(
                    "Digite o hobby [ARTE, ESPORTE, CINEMA, LIVRO, MÚSICA, CULINÁRIA, GAME,"
                        + " NENHUM]: ")
                .toUpperCase());
    while (hobbyAux == null) {
      hobbyAux =
          Hobby.valueOf(
              JOptionPane.showInputDialog(
                      "Digite o hobby [ARTE, ESPORTE, CINEMA, LIVRO, MÚSICA, CULINÁRIA, GAME,"
                          + " NENHUM]: ")
                  .toUpperCase());
    }
    Hobby hobby = hobbyAux;

    EstadoCivil estadoCivilAux =
        EstadoCivil.valueOf(
            JOptionPane.showInputDialog(
                    "Digite o estado civil (SOLTEIRO, CASADO, DIVORCIADO, VIUVO, SEPARADO): ")
                .toUpperCase());
    while (estadoCivilAux == null) {
      EstadoCivil.valueOf(
          JOptionPane.showInputDialog(
                  "Digite o estado civil (SOLTEIRO, CASADO, DIVORCIADO, VIUVO, SEPARADO): ")
              .toUpperCase());
    }
    EstadoCivil estadoCivil = estadoCivilAux;

    Escolaridade escolaridadeAux =
        Escolaridade.valueOf(
            JOptionPane.showInputDialog(
                    "Digite a escolaridade (FUNDAMENTAL, MEDIO, SUPERIOR, POS_GRADUACAO, NENHUMA):"
                        + " ")
                .toUpperCase());
    while (escolaridadeAux == null) {
      Escolaridade.valueOf(
          JOptionPane.showInputDialog(
                  "Digite a escolaridade (FUNDAMENTAL, MEDIO, SUPERIOR, POS_GRADUACAO, NENHUMA):"
                      + " ")
              .toUpperCase());
    }
    Escolaridade escolaridade = escolaridadeAux;

    Boolean felizAux =
        Boolean.parseBoolean(JOptionPane.showInputDialog("Você está feliz? (true/false): "));
    while (felizAux == null) {
      felizAux =
          Boolean.parseBoolean(JOptionPane.showInputDialog("Você está feliz? (true/false): "));
    }
    boolean feliz = felizAux;

    Moradia moradiaAux =
        Moradia.valueOf(
            JOptionPane.showInputDialog(
                    "Digite o tipo de moradia [COM_FAMILIA, ALUGUEL, CASA_PROPRIA]: ")
                .toUpperCase());
    while (moradiaAux == null) {
      moradiaAux =
          Moradia.valueOf(
              JOptionPane.showInputDialog(
                      "Digite o tipo de moradia [COM_FAMILIA, ALUGUEL, CASA_PROPRIA]: ")
                  .toUpperCase());
    }
    Moradia moradia = moradiaAux;

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
    JOptionPane.showMessageDialog(
        null, pessoa, "Pessoa Cadastrada", JOptionPane.INFORMATION_MESSAGE);
    return pessoa;
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
    JOptionPane.showMessageDialog(null, dataset, "Estatísticas", JOptionPane.INFORMATION_MESSAGE);
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

  private static void exibePessoasSimilares(Pessoa pessoa, int n) {
    Pessoa[] pessoasSimilares = distance.getSimilar(pessoa, n);

    StringBuilder sb = new StringBuilder();
    sb.append(pessoasSimilares[0])
        .append("\n")
        .append(pessoasSimilares[1])
        .append("\n")
        .append(pessoasSimilares[2]);

    JOptionPane.showMessageDialog(
        null, sb.toString(), "Pessoas Similares", JOptionPane.INFORMATION_MESSAGE);
  }
}
