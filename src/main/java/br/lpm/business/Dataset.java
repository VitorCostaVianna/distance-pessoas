package br.lpm.business;

import java.util.Arrays;
import java.util.Comparator;

public class Dataset {
  private Pessoa[] pessoas;
  private static final int MAX_PESSOAS = 100;
  private int numPessoasVetor;
  private static DistanceMeasure distanceMeasure;

  public Dataset() {
    pessoas = new Pessoa[MAX_PESSOAS];
    this.numPessoasVetor = 0;
    distanceMeasure = new DistanceMeasure(this);
  }

  public void addPessoa(Pessoa pessoa) {
    if (size() < MAX_PESSOAS) {
      pessoas[numPessoasVetor] = pessoa;
      numPessoasVetor++;
    }
  }

  public void removePessoa(Pessoa pessoa) {
    for (int j = 0; j < size(); j++) {
      if (pessoa.equals(pessoas[j])) {
        for (int p = j; p < size() - 1; p++) {
          pessoas[p] = pessoas[p + 1];
        }
        pessoas[size() - 1] = null;
        numPessoasVetor--;
        break;
      }
    }
  }

  public void removePessoaByName(String name) {
    for (int j = 0; j < size(); j++) {
      if (pessoas[j] != null && pessoas[j].getNome().equalsIgnoreCase(name)) {
        for (int p = j; p < size() - 1; p++) {
          pessoas[p] = pessoas[p + 1];
        }
        pessoas[size() - 1] = null;
        numPessoasVetor--;
        break;
      }
    }
  }

  public void replacePessoa(Pessoa oldPessoa, Pessoa newPessoa) {
    for (int j = 0; j < size(); j++) {
      if (oldPessoa.equals(pessoas[j])) {
        pessoas[j] = newPessoa;
        break;
      }
    }
  }

  public Pessoa getPessoaByName(String name) {
    for (int j = 0; j < size(); j++) {
      if (pessoas[j] != null && pessoas[j].getNome().equalsIgnoreCase(name)) {
        return pessoas[j];
      }
    }
    return null;
  }

  public void removeAll() {
    for (int j = 0; j < size(); j++) {
      pessoas[j] = null;
    }
    numPessoasVetor = 0;
  }

  public Pessoa[] getAll() {
    if (pessoas != null) {
      return pessoas;
    }
    return null;
  }

  public int size() {
    return numPessoasVetor;
  }

  public float maxRenda() {
    if (size() > 0 && pessoas[0] != null) {
      float maxRenda;
      maxRenda = pessoas[0].getRenda();
      for (int j = 1; j < size(); j++) {
        if (pessoas[j] != null && pessoas[j].getRenda() > maxRenda) {
          maxRenda = pessoas[j].getRenda();
        }
      }
      return maxRenda;
    }
    return -1;
  }

  public float minRenda() {
    if (size() > 0 && pessoas[0] != null) {
      float minRenda;
      minRenda = pessoas[0].getRenda();
      for (int j = 1; j < size(); j++) {
        if (pessoas[j] != null && pessoas[j].getRenda() < minRenda) {
          minRenda = pessoas[j].getRenda();
        }
      }
      return minRenda;
    }
    return -1;
  }

  public float maxIdade() {
    if (size() > 0 && pessoas[0] != null) {
      float maxIdade;
      maxIdade = pessoas[0].calculaIdade();
      for (int j = 1; j < size(); j++) {
        if (pessoas[j] != null && pessoas[j].calculaIdade() > maxIdade) {
          maxIdade = pessoas[j].calculaIdade();
        }
      }
      return maxIdade;
    }
    return -1;
  }

  public float minIdade() {
    if (size() > 0 && pessoas[0] != null) {
      float minIdade;
      minIdade = pessoas[0].calculaIdade();
      for (int j = 1; j < size(); j++) {
        if (pessoas[j] != null && pessoas[j].calculaIdade() < minIdade) {
          minIdade = pessoas[j].calculaIdade();
        }
      }
      return minIdade;
    }
    return -1;
  }

  public float maxAltura() {
    if (size() > 0 && pessoas[0] != null) {
      float maxAltura;
      maxAltura = pessoas[0].getAltura();
      for (int j = 1; j < size(); j++) {
        if (pessoas[j] != null && pessoas[j].getAltura() > maxAltura) {
          maxAltura = pessoas[j].getAltura();
        }
      }
      return maxAltura;
    }
    return -1;
  }

  public float minAltura() {
    if (size() > 0 && pessoas[0] != null) {
      float minAltura;
      minAltura = pessoas[0].getAltura();
      for (int j = 1; j < size(); j++) {
        if (pessoas[j] != null && pessoas[j].getAltura() < minAltura) {
          minAltura = pessoas[j].getAltura();
        }
      }
      return minAltura;
    }
    return -1;
  }

  public float avgAltura() {
    float sum = 0;
    int total = 0;
    for (int j = 0; j < size(); j++) {
      if (pessoas[j] != null && pessoas[j].getAltura() != 0) {
        sum += pessoas[j].getAltura();
        total++;
      }
    }
    return sum / (float) total;
  }

  public float maxPeso() {
    if (size() > 0 && pessoas[0] != null) {
      float maxPeso;
      maxPeso = pessoas[0].getPeso();
      for (int j = 1; j < size(); j++) {
        if (pessoas[j] != null && pessoas[j].getPeso() > maxPeso) {
          maxPeso = pessoas[j].getPeso();
        }
      }
      return (float) maxPeso;
    }
    return -1;
  }

  public float minPeso() {
    if (size() > 0 && pessoas[0] != null) {
      float minPeso;
      minPeso = pessoas[0].getPeso();
      for (int j = 1; j < size(); j++) {
        if (pessoas[j] != null && pessoas[j].getPeso() < minPeso) {
          minPeso = pessoas[j].getPeso();
        }
      }
      return minPeso;
    }
    return -1;
  }

  public float avgPeso() {
    float sum = 0;
    int total = 0;
    for (int j = 0; j < size(); j++) {
      if (pessoas[j] != null && pessoas[j].getPeso() != 0) {
        sum += pessoas[j].getPeso();
        total++;
      }
    }
    return sum / (float) total;
  }

  public float percentAdult() {
    int sum = 0;
    int total = 0;
    for (int j = 0; j < size(); j++) {
      if (pessoas[j] != null && pessoas[j].getDataDeNascimento() != null) {
        total++;
        if (pessoas[j].calculaIdade() >= 18) {
          sum++;
        }
      }
    }
    return (sum / (float) total) * 100;
  }

  public float percentEstadoCivil(EstadoCivil estadoCivil) {
    int sum = 0;
    int total = 0;
    for (int j = 0; j < size(); j++) {
      if (pessoas[j] != null && pessoas[j].getEstadoCivil() != null) {
        total++;
        if (pessoas[j].getEstadoCivil().equals(estadoCivil)) {
          sum++;
        }
      }
    }
    return ((float) sum / total) * 100;
  }

  public EstadoCivil modeEstadoCivil() {
    float qtdSolteiro = percentEstadoCivil(EstadoCivil.SOLTEIRO);
    float qtdCasado = percentEstadoCivil(EstadoCivil.CASADO);
    float qtdViuvo = percentEstadoCivil(EstadoCivil.VIUVO);
    float qtdSeparado = percentEstadoCivil(EstadoCivil.SEPARADO);
    float qtdDivorciado = percentEstadoCivil(EstadoCivil.DIVORCIADO);

    float max = qtdSolteiro;
    EstadoCivil moda = EstadoCivil.SOLTEIRO;

    if (qtdCasado > max) {
      max = qtdCasado;
      moda = EstadoCivil.CASADO;
    }
    if (qtdViuvo > max) {
      max = qtdViuvo;
      moda = EstadoCivil.VIUVO;
    }
    if (qtdSeparado > max) {
      max = qtdSolteiro;
      moda = EstadoCivil.SOLTEIRO;
    }
    if (qtdDivorciado > max) {
      max = qtdDivorciado;
      moda = EstadoCivil.DIVORCIADO;
    }

    return moda;
  }

  public float percentEscolaridade(Escolaridade escolaridade) {
    int sum = 0;
    int total = 0;
    for (int j = 0; j < size(); j++) {
      if (pessoas[j] != null && pessoas[j].getEscolaridade() != null) {
        total++;
        if (pessoas[j].getEscolaridade().equals(escolaridade)) {
          sum++;
        }
      }
    }
    return ((float) sum / total) * 100;
  }

  public Escolaridade modeEscolaridade() {
    float qtdFundamental = percentEscolaridade(Escolaridade.FUNDAMENTAL);
    float qtdMedio = percentEscolaridade(Escolaridade.MEDIO);
    float qtdPosGraduação = percentEscolaridade(Escolaridade.POS_GRADUACAO);
    float qtdSuperior = percentEscolaridade(Escolaridade.SUPERIOR);
    float qtdNenhuma = percentEscolaridade(Escolaridade.NENHUMA);

    float max = qtdFundamental;
    Escolaridade moda = Escolaridade.FUNDAMENTAL;

    if (qtdMedio > max) {
      max = qtdMedio;
      moda = Escolaridade.MEDIO;
    }
    if (qtdPosGraduação > max) {
      max = qtdPosGraduação;
      moda = Escolaridade.POS_GRADUACAO;
    }
    if (qtdSuperior > max) {
      max = qtdSuperior;
      moda = Escolaridade.SUPERIOR;
    }
    if (qtdNenhuma > max) {
      max = qtdNenhuma;
      moda = Escolaridade.NENHUMA;
    }

    return moda;
  }

  public float percentMoradia(Moradia moradia) {
    int sum = 0;
    int total = 0;
    for (int j = 0; j < size(); j++) {
      if (pessoas[j] != null && pessoas[j].getMoradia() != null) {
        total++;
        if (pessoas[j].getMoradia().equals(moradia)) {
          sum++;
        }
      }
    }
    return ((float) sum / total) * 100;
  }

  public Moradia modeMoradia() {
    float qtdAluguel = percentMoradia(Moradia.ALUGUEL);
    float qtdCasaPorpria = percentMoradia(Moradia.CASA_PROPRIA);
    float qtdComFamilia = percentMoradia(Moradia.COM_FAMILIA);

    float max = qtdAluguel;
    Moradia moda = Moradia.ALUGUEL;

    if (qtdCasaPorpria > max) {
      max = qtdCasaPorpria;
      moda = Moradia.CASA_PROPRIA;
    }
    if (qtdComFamilia > max) {
      max = qtdComFamilia;
      moda = Moradia.COM_FAMILIA;
    }

    return moda;
  }

  public float percentHobby() {
    int withHobby = 0;
    int total = 0;
    for (int j = 0; j < size(); j++) {
      if (pessoas[j] != null && pessoas[j].getHobby() != null) {
        total++;
        if (!pessoas[j].getHobby().equals(Hobby.NENHUM)) {
          withHobby++;
        }
      }
    }
    return ((float) (withHobby) / total) * 100;
  }

  public float percentFeliz() {
    int isFeliz = 0;
    int total = 0;
    for (int j = 0; j < size(); j++) {
      if (pessoas[j] != null && pessoas[j].getFeliz() == true) {
        isFeliz++;
      }
      total++;
    }
    return ((float) isFeliz / total) * 100;
  }

  public Float[] normalizeField(String fieldName) {
    Float[] normalizedField = new Float[size()];

    if (fieldName.equalsIgnoreCase("Peso")) {
      Float[] pesos = new Float[size()];
      float maxPeso = maxPeso();
      float minPeso = minPeso();
      for (int i = 0; i < pesos.length; i++) {
        pesos[i] = (float) pessoas[i].getPeso();
        normalizedField[i] = (pesos[i] - minPeso) / (maxPeso - minPeso);
      }
      return normalizedField;
    }

    if (fieldName.equalsIgnoreCase("Altura")) {
      Float[] alturas = new Float[size()];
      float maxAltura = maxAltura();
      float minAltura = minAltura();
      for (int i = 0; i < alturas.length; i++) {
        alturas[i] = (float) pessoas[i].getAltura();
        normalizedField[i] = (alturas[i] - minAltura) / (maxAltura - minAltura);
      }
      return normalizedField;
    }

    if (fieldName.equalsIgnoreCase("Renda")) {
      Float[] rendas = new Float[size()];
      float maxRenda = maxRenda();
      float minRenda = minRenda();
      for (int i = 0; i < rendas.length; i++) {
        rendas[i] = (float) pessoas[i].getRenda();
        normalizedField[i] = (rendas[i] - minRenda) / (maxRenda - minRenda);
      }
      return normalizedField;
    }

    if (fieldName.equalsIgnoreCase("Idade")) {
      Float[] idades = new Float[size()];
      float maxIdade = maxIdade();
      float minIdade = minIdade();
      for (int i = 0; i < idades.length; i++) {
        idades[i] = (float) pessoas[i].calculaIdade();
        normalizedField[i] = (idades[i] - minIdade) / (maxIdade - minIdade);
      }
      return normalizedField;
    }
    return null;
  }

  public Float[] calcDistanceVector(Pessoa pessoa) {
    if (pessoas == null || pessoa == null) {
      return new Float[0];
    }

    int qtdePessoasDiferente = calcQtdePessoasDiferentes(pessoa);
    Float[] distanceVector = new Float[qtdePessoasDiferente];
    Pessoa[] pessoasDiferentes = new Pessoa[qtdePessoasDiferente];

    int i = 0;
    for (Pessoa p : pessoas) {
      if (!pessoa.equals(p)) {
        distanceVector[i] = distanceMeasure.calcDistance(pessoa, p);
        pessoasDiferentes[i] = p;
        i++;
      }
    }

    sortByDistance(distanceVector, pessoasDiferentes);

    return distanceVector;
  }

  private void sortByDistance(Float[] distanceVector, Pessoa[] pessoasDiferentes) {
    DistancePessoaPair[] pairs = new DistancePessoaPair[distanceVector.length];
    for (int i = 0; i < distanceVector.length; i++) {
      pairs[i] = new DistancePessoaPair(distanceVector[i], pessoasDiferentes[i]);
    }

    Arrays.sort(pairs, Comparator.comparingDouble(DistancePessoaPair::getDistance));

    for (int i = 0; i < pairs.length; i++) {
      distanceVector[i] = pairs[i].getDistance();
      pessoasDiferentes[i] = pairs[i].getPessoa();
    }
  }

  private int calcQtdePessoasDiferentes(Pessoa pessoa) {
    int qtdePessoasDiferente = 0;
    for (Pessoa p : pessoas) {
      if (!pessoa.equals(p)) {
        qtdePessoasDiferente++;
      }
    }
    return qtdePessoasDiferente;
  }

  public Float[][] calcDistanceMatrix() {
    int row = size();
    int column = row;
    Float[][] matrizDistancias = new Float[row][column];
    for (int i = 0; i < row; i++) {
      for (int j = i; j < column; j++) {
        if (i == j) {
          matrizDistancias[i][j] = 0f;
        } else {
          matrizDistancias[i][j] = distanceMeasure.calcDistance(pessoas[i], pessoas[j]);

          matrizDistancias[j][i] = matrizDistancias[i][j];
        }
      }
    }
    return matrizDistancias;
  }

  public Pessoa[] getSimilar(Pessoa pessoa, int n) {
    if (pessoas == null || pessoa == null || n <= 0) {
      return new Pessoa[0];
    }

    Float[][] distanceMatrix = calcDistanceMatrix();
    int indexPessoa = findPessoaIndex(pessoa);

    if (indexPessoa == -1) {
      return new Pessoa[0];
    }

    DistancePessoaPair[] distancePessoaPairs = new DistancePessoaPair[size() -1];
    int pairIndex = 0;

    for (int i = 0; i < size() -1; i++) {
      if (i != indexPessoa) {
        distancePessoaPairs[pairIndex] =
            new DistancePessoaPair(distanceMatrix[indexPessoa][i], pessoas[i]);
        pairIndex++;
      }
    }

    Arrays.sort(distancePessoaPairs, Comparator.comparingDouble(DistancePessoaPair::getDistance));

    int numSimilar = Math.min(n, distancePessoaPairs.length);
    Pessoa[] similarPessoas = new Pessoa[numSimilar];
    for (int i = 0; i < numSimilar; i++) {
      similarPessoas[i] = distancePessoaPairs[i].getPessoa();
    }

    return similarPessoas;
  }

  private static class DistancePessoaPair {
    private final Float distance;
    private final Pessoa pessoa;

    public DistancePessoaPair(Float distance, Pessoa pessoa) {
      this.distance = distance;
      this.pessoa = pessoa;
    }

    public Float getDistance() {
      return distance;
    }

    public Pessoa getPessoa() {
      return pessoa;
    }
  }

  private int findPessoaIndex(Pessoa pessoa) {
    for (int i = 0; i < pessoas.length; i++) {
      if (pessoas[i].equals(pessoa)) {
        return i;
      }
    }
    return -1;
  }

  private Float[] ordenaCrescente(Float[] distanceVector) {
    for (int i = 0; i < distanceVector.length - 1; i++) {
      for (int j = 0; j < distanceVector.length - 1; j++) {

        if (distanceVector[j] > distanceVector[j + 1]) {

          float mudaVetor = distanceVector[j];
          distanceVector[j] = distanceVector[j + 1];
          distanceVector[j + 1] = mudaVetor;
        }
      }
    }
    return distanceVector;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Estatísticas:\n")
        .append("Altura Máxima: " + maxAltura() + " m\n")
        .append(maxAltura())
        .append(" m\n")
        .append("Altura Média: ")
        .append(avgAltura())
        .append(" m\n")
        .append("Altura Mínima: ")
        .append(minAltura())
        .append(" m\n")
        .append("Peso Máximo: ")
        .append(maxPeso())
        .append(" kg\n")
        .append("Peso Mínimo: ")
        .append(minPeso())
        .append(" kg\n")
        .append("Peso Médio: ")
        .append(avgPeso())
        .append(" kg\n")
        .append("Porcentagem de Adultos: ")
        .append(percentAdult())
        .append("%\n")
        .append("Moda da Moradia: ")
        .append(modeMoradia())
        .append("\n")
        .append("Moda da Escolaridade: ")
        .append(modeEscolaridade())
        .append("\n")
        .append("Moda do Estado Civil: ")
        .append(modeEstadoCivil())
        .append("\n")
        .append("Porcentagem de Pessoas Felizes: ")
        .append(percentFeliz())
        .append("%\n");

    return sb.toString();
  }
}
