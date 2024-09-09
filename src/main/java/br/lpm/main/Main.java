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

    Pessoa pessoaPrincipal = new Pessoa();
    cadastrarDataset();
    cadastrarPessoa(pessoaPrincipal);
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
    String qtdePessoasDatasetAux =
        JOptionPane.showInputDialog(
            "Digite a quantidade de pessoas que deseja cadastrar na base de dados: ");
    int qtdePessoasDataset = 0;
    if (qtdePessoasDatasetAux != null) {
      qtdePessoasDataset = Integer.parseInt(qtdePessoasDatasetAux);
    } else {
      while (qtdePessoasDatasetAux == null) {
        qtdePessoasDatasetAux = JOptionPane.showInputDialog("Digite um valor válido: ");
      }
      qtdePessoasDataset = Integer.parseInt(qtdePessoasDatasetAux);
    }
    for (int i = 0; i < qtdePessoasDataset; i++) {
      cadastrarPessoa();
    }
  }

  public static void cadastrarPessoa() {
    String nomeAux = JOptionPane.showInputDialog("Digite seu nome: ");
    String nome = null;
    while (nomeAux == null) {
      nomeAux = JOptionPane.showInputDialog("Digite seu nome: ");
    }
    nome = nomeAux;

    String dataDeAniversarioAux =
        JOptionPane.showInputDialog("Digite a data de aniversário (dd/MM/yyyy): ");
    LocalDate dataDeAniversario = null;

    while (dataDeAniversarioAux == null) {
      dataDeAniversarioAux =
          JOptionPane.showInputDialog("Insira a data de aniversário (dd/MM/yyyy): ");
    }
    dataDeAniversario = LocalDate.parse(dataDeAniversarioAux, fmt);

    Genero generoAux =
        Genero.valueOf(
            JOptionPane.showInputDialog(
                    "Digite o gênero (MASCULINO, FEMININO, NAO_BINARIO, NAO_RESPONDER): ")
                .toUpperCase());
    Genero genero = null;
    while (generoAux == null) {
      generoAux =
          Genero.valueOf(
              JOptionPane.showInputDialog(
                      "Digite um gênero (MASCULINO, FEMININO, NAO_BINARIO, NAO_RESPONDER): ")
                  .toUpperCase());
    }
    genero = generoAux;

    Float alturaAux =
        Float.parseFloat(JOptionPane.showInputDialog("Digite a altura (em metros): "));
    Float altura = null;
    while (alturaAux == null) {
      alturaAux = Float.parseFloat(JOptionPane.showInputDialog("Digite uma altura (em metros): "));
    }
    altura = alturaAux;

    Integer pesoAux = Integer.parseInt(JOptionPane.showInputDialog("Digite o peso (em kg): "));
    Integer peso = null;
    while (pesoAux == null) {
      pesoAux = Integer.parseInt(JOptionPane.showInputDialog("Digite o peso (em kg): "));
    }
    peso = pesoAux;

    Float rendaAux = Float.parseFloat(JOptionPane.showInputDialog("Digite a renda: "));
    Float renda = null;
    while (rendaAux == null) {
      rendaAux = Float.parseFloat(JOptionPane.showInputDialog("Digite a renda: "));
    }
    renda = rendaAux;

    String naturalidadeAux = JOptionPane.showInputDialog("Digite a naturalidade: ");
    String naturalidade = null;
    while (naturalidadeAux == null) {
      naturalidadeAux = JOptionPane.showInputDialog("Digite a naturalidade: ");
    }
    naturalidade = naturalidadeAux;

    Hobby hobbyAux =
        Hobby.valueOf(
            JOptionPane.showInputDialog(
                    "Digite o hobby [ARTE, ESPORTE, CINEMA, LIVRO, MÚSICA, CULINÁRIA, GAME,"
                        + " NENHUM]: ")
                .toUpperCase());
    Hobby hobby = null;
    while (hobbyAux == null) {
      hobbyAux =
          Hobby.valueOf(
              JOptionPane.showInputDialog(
                      "Digite o hobby [ARTE, ESPORTE, CINEMA, LIVRO, MÚSICA, CULINÁRIA, GAME,"
                          + " NENHUM]: ")
                  .toUpperCase());
    }
    hobby = hobbyAux;

    EstadoCivil estadoCivilAux =
        EstadoCivil.valueOf(
            JOptionPane.showInputDialog(
                    "Digite o estado civil (SOLTEIRO, CASADO, DIVORCIADO, VIUVO, SEPARADO): ")
                .toUpperCase());
    EstadoCivil estadoCivil = null;
    while (estadoCivilAux == null) {
      EstadoCivil.valueOf(
          JOptionPane.showInputDialog(
                  "Digite o estado civil (SOLTEIRO, CASADO, DIVORCIADO, VIUVO, SEPARADO): ")
              .toUpperCase());
    }
    estadoCivil = estadoCivilAux;

    Escolaridade escolaridadeAux =
        Escolaridade.valueOf(
            JOptionPane.showInputDialog(
                    "Digite a escolaridade (FUNDAMENTAL, MEDIO, SUPERIOR, POS_GRADUACAO, NENHUMA):"
                        + " ")
                .toUpperCase());
    Escolaridade escolaridade = null;
    while (escolaridadeAux == null) {
      Escolaridade.valueOf(
          JOptionPane.showInputDialog(
                  "Digite a escolaridade (FUNDAMENTAL, MEDIO, SUPERIOR, POS_GRADUACAO, NENHUMA):"
                      + " ")
              .toUpperCase());
    }
    escolaridade = escolaridadeAux;

    Boolean felizAux =
        Boolean.parseBoolean(JOptionPane.showInputDialog("Você está feliz? (true/false): "));
    Boolean feliz = null;
    while (felizAux == null) {
      felizAux =
          Boolean.parseBoolean(JOptionPane.showInputDialog("Você está feliz? (true/false): "));
    }
    feliz = felizAux;

    Moradia moradiaAux =
        Moradia.valueOf(
            JOptionPane.showInputDialog(
                    "Digite o tipo de moradia [COM_FAMILIA, ALUGUEL, CASA_PROPRIA]: ")
                .toUpperCase());
    Moradia moradia = null;
    while (moradiaAux == null) {
      moradiaAux =
          Moradia.valueOf(
              JOptionPane.showInputDialog(
                      "Digite o tipo de moradia [COM_FAMILIA, ALUGUEL, CASA_PROPRIA]: ")
                  .toUpperCase());
    }
    moradia = moradiaAux;

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

  public static void cadastrarPessoa(Pessoa newPessoa) {
    JOptionPane.showMessageDialog(null, "Cadastro da pessoa a ser comparada: ");

    String nomeAux = JOptionPane.showInputDialog("Digite seu nome: ");
    String nome = null;

    while (nomeAux == null) {
      nomeAux = JOptionPane.showInputDialog("Digite seu nome: ");
    }
    nome = nomeAux;

    String dataDeAniversarioAux =
        JOptionPane.showInputDialog("Digite a data de aniversário (dd/MM/yyyy): ");
    LocalDate dataDeAniversario = null;

    while (dataDeAniversarioAux == null) {
      dataDeAniversarioAux =
          JOptionPane.showInputDialog("Insira a data de aniversário (dd/MM/yyyy): ");
    }
    dataDeAniversario = LocalDate.parse(dataDeAniversarioAux, fmt);

    Genero generoAux =
        Genero.valueOf(
            JOptionPane.showInputDialog(
                    "Digite o gênero (MASCULINO, FEMININO, NAO_BINARIO, NAO_RESPONDER): ")
                .toUpperCase());
    Genero genero = null;
    while (generoAux == null) {
      generoAux =
          Genero.valueOf(
              JOptionPane.showInputDialog(
                      "Digite um gênero (MASCULINO, FEMININO, NAO_BINARIO, NAO_RESPONDER): ")
                  .toUpperCase());
    }
    genero = generoAux;

    Float alturaAux =
        Float.parseFloat(JOptionPane.showInputDialog("Digite a altura (em metros): "));
    Float altura = null;
    while (alturaAux == null) {
      alturaAux = Float.parseFloat(JOptionPane.showInputDialog("Digite uma altura (em metros): "));
    }
    altura = alturaAux;

    Integer pesoAux = Integer.parseInt(JOptionPane.showInputDialog("Digite o peso (em kg): "));
    Integer peso = null;
    while (pesoAux == null) {
      pesoAux = Integer.parseInt(JOptionPane.showInputDialog("Digite o peso (em kg): "));
    }
    peso = pesoAux;

    Float rendaAux = Float.parseFloat(JOptionPane.showInputDialog("Digite a renda: "));
    Float renda = null;
    while (rendaAux == null) {
      rendaAux = Float.parseFloat(JOptionPane.showInputDialog("Digite a renda: "));
    }
    renda = rendaAux;

    String naturalidadeAux = JOptionPane.showInputDialog("Digite a naturalidade: ");
    String naturalidade = null;
    while (naturalidadeAux == null) {
      naturalidadeAux = JOptionPane.showInputDialog("Digite a naturalidade: ");
    }
    naturalidade = naturalidadeAux;

    Hobby hobbyAux =
        Hobby.valueOf(
            JOptionPane.showInputDialog(
                    "Digite o hobby [ARTE, ESPORTE, CINEMA, LIVRO, MÚSICA, CULINÁRIA, GAME,"
                        + " NENHUM]: ")
                .toUpperCase());
    Hobby hobby = null;
    while (hobbyAux == null) {
      hobbyAux =
          Hobby.valueOf(
              JOptionPane.showInputDialog(
                      "Digite o hobby [ARTE, ESPORTE, CINEMA, LIVRO, MÚSICA, CULINÁRIA, GAME,"
                          + " NENHUM]: ")
                  .toUpperCase());
    }
    hobby = hobbyAux;

    EstadoCivil estadoCivilAux =
        EstadoCivil.valueOf(
            JOptionPane.showInputDialog(
                    "Digite o estado civil (SOLTEIRO, CASADO, DIVORCIADO, VIUVO, SEPARADO): ")
                .toUpperCase());
    EstadoCivil estadoCivil = null;
    while (estadoCivilAux == null) {
      EstadoCivil.valueOf(
          JOptionPane.showInputDialog(
                  "Digite o estado civil (SOLTEIRO, CASADO, DIVORCIADO, VIUVO, SEPARADO): ")
              .toUpperCase());
    }
    estadoCivil = estadoCivilAux;

    Escolaridade escolaridadeAux =
        Escolaridade.valueOf(
            JOptionPane.showInputDialog(
                    "Digite a escolaridade (FUNDAMENTAL, MEDIO, SUPERIOR, POS_GRADUACAO, NENHUMA):"
                        + " ")
                .toUpperCase());
    Escolaridade escolaridade = null;
    while (escolaridadeAux == null) {
      Escolaridade.valueOf(
          JOptionPane.showInputDialog(
                  "Digite a escolaridade (FUNDAMENTAL, MEDIO, SUPERIOR, POS_GRADUACAO, NENHUMA):"
                      + " ")
              .toUpperCase());
    }
    escolaridade = escolaridadeAux;

    Boolean felizAux =
        Boolean.parseBoolean(JOptionPane.showInputDialog("Você está feliz? (true/false): "));
    Boolean feliz = null;
    while (felizAux == null) {
      felizAux =
          Boolean.parseBoolean(JOptionPane.showInputDialog("Você está feliz? (true/false): "));
    }
    feliz = felizAux;

    Moradia moradiaAux =
        Moradia.valueOf(
            JOptionPane.showInputDialog(
                    "Digite o tipo de moradia [COM_FAMILIA, ALUGUEL, CASA_PROPRIA]: ")
                .toUpperCase());
    Moradia moradia = null;
    while (moradiaAux == null) {
      moradiaAux =
          Moradia.valueOf(
              JOptionPane.showInputDialog(
                      "Digite o tipo de moradia [COM_FAMILIA, ALUGUEL, CASA_PROPRIA]: ")
                  .toUpperCase());
    }
    moradia = moradiaAux;

    newPessoa =
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
    dataset.addPessoa(newPessoa);
    distance = new DistanceMeasure(dataset);
    JOptionPane.showMessageDialog(
        null, newPessoa, "Pessoa Cadastrada", JOptionPane.INFORMATION_MESSAGE);
    exibePessoasSimilares(newPessoa);
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

  private static void exibePessoasSimilares(Pessoa pessoa) {
    Pessoa[] pessoasSimilares = distance.getSimilar(pessoa, 3);

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
