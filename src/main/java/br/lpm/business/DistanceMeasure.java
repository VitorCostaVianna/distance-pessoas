package br.lpm.business;

public class DistanceMeasure {
  private Dataset dataset;
  private Pessoa[] pessoas;

  public DistanceMeasure(Dataset dataset) {
    this.dataset = dataset;
    pessoas = dataset.getAll();
  }

  private Float[] normalizeField(String fieldName) {
    Float[] normalizedField = new Float[dataset.size()];

    if (fieldName.equalsIgnoreCase("Peso")) {
      Float[] pesos = new Float[dataset.size()];
      float maxPeso = dataset.maxPeso();
      float minPeso = dataset.minPeso();
      for (int i = 0; i < pesos.length; i++) {
        pesos[i] = (float) pessoas[i].getPeso();
        normalizedField[i] = (pesos[i] - minPeso) / (maxPeso - minPeso);
      }
      return normalizedField;
    }

    if (fieldName.equalsIgnoreCase("Altura")) {
      Float[] alturas = new Float[dataset.size()];
      float maxAltura = dataset.maxAltura();
      float minAltura = dataset.minAltura();
      for (int i = 0; i < alturas.length; i++) {
        alturas[i] = (float) pessoas[i].getAltura();
        normalizedField[i] = (alturas[i] - minAltura) / (maxAltura - minAltura);
      }
      return normalizedField;
    }

    if (fieldName.equalsIgnoreCase("Renda")) {
      Float[] rendas = new Float[dataset.size()];
      float maxRenda = dataset.maxRenda();
      float minRenda = dataset.minRenda();
      for (int i = 0; i < rendas.length; i++) {
        rendas[i] = (float) pessoas[i].getRenda();
        normalizedField[i] = (rendas[i] - minRenda) / (maxRenda - minRenda);
      }
      return normalizedField;
    }

    if (fieldName.equalsIgnoreCase("Idade")) {
      Float[] idades = new Float[dataset.size()];
      float maxIdade = dataset.maxIdade();
      float minIdade = dataset.minIdade();
      for (int i = 0; i < idades.length; i++) {
        idades[i] = (float) pessoas[i].calculaIdade();
        normalizedField[i] = (idades[i] - minIdade) / (maxIdade - minIdade);
      }
      return normalizedField;
    }
    return null;
  }

  private int getPessoaPosition(Pessoa pessoa) {
    for (int i = 0; i < dataset.size(); i++) {
      if (pessoas[i].equals(pessoa)) {
        return i;
      }
    }
    return -1;
  }

  public float calcDistance(Pessoa firstPessoa, Pessoa secondPessoa) {
    if (firstPessoa.equals(null) || secondPessoa.equals(null)) {
      return -1;
    }
    
    float sumDistances = 0;
    Float[] normalizedPesos = normalizeField("Peso");
    Float[] normalizedAlturas = normalizeField("Altura");
    Float[] normalizedIdades = normalizeField("Idade");
    Float[] normalizedRendas = normalizeField("Renda");
    int firstPessoaPosition = getPessoaPosition(firstPessoa);
    int secondPessoaPosition = getPessoaPosition(secondPessoa);
    final int TOTAL_ATRIBUTOS = 11;

    if (!firstPessoa.getHobby().equals(secondPessoa.getHobby())) {
      sumDistances++;
    }
    if (!firstPessoa.getEstadoCivil().equals(secondPessoa.getEstadoCivil())) {
      sumDistances++;
    }
    if (!firstPessoa.getMoradia().equals(secondPessoa.getMoradia())) {
      sumDistances++;
    }
    if (!firstPessoa.getEscolaridade().equals(secondPessoa.getEscolaridade())) {
      sumDistances++;
    }
    if (!firstPessoa.getGenero().equals(secondPessoa.getGenero())) {
      sumDistances++;
    }
    if (!firstPessoa.getNaturalidade().equalsIgnoreCase(secondPessoa.getNaturalidade())) {
      sumDistances++;
    }
    if (!(firstPessoa.getFeliz() == secondPessoa.getFeliz())) {
      sumDistances++;
    }

    sumDistances +=
        Math.pow(
            Math.abs(
                normalizedAlturas[firstPessoaPosition] - normalizedAlturas[secondPessoaPosition]),
            2);
    sumDistances +=
        Math.pow(
            Math.abs(normalizedPesos[firstPessoaPosition] - normalizedPesos[secondPessoaPosition]),
            2);
    sumDistances +=
        Math.pow(
            Math.abs(
                normalizedIdades[firstPessoaPosition] - normalizedIdades[secondPessoaPosition]),
            2);
    sumDistances +=
        Math.pow(
            Math.abs(
                normalizedRendas[firstPessoaPosition] - normalizedRendas[secondPessoaPosition]),
            2);

    return (float) Math.sqrt(sumDistances / TOTAL_ATRIBUTOS);
  }

  public Float[] calcDistanceVector(Pessoa pessoa) {
    int qtdePessoasDiferente = calcQtdePessoasDiferentes(pessoa);

    Float[] distanceVector = new Float[qtdePessoasDiferente];
    int i = 0;
    for (Pessoa p : pessoas) {
      if (!pessoa.equals(p)) {
        distanceVector[i] = calcDistance(pessoa, p);
        i++;
      }
    }

    return distanceVector;
  }

  private int calcQtdePessoasDiferentes(Pessoa pessoa){
    int qtdePessoasDiferente = 0;
    for (Pessoa p : pessoas) {
      if (!pessoa.equals(p)) {
        qtdePessoasDiferente++;
      }
    }
    return qtdePessoasDiferente;
  }

  public Float[][] calcDistanceMatrix() {
    int row = dataset.size();
    int column = row;
    Float[][] matrizDistancias = new Float[row][column];
    for (int i = 0; i < row; i++) {
      for (int j = i; j < column; j++) {
        if (i == j) {
          matrizDistancias[i][j] = 0f;
        } else {
          matrizDistancias[i][j] = calcDistance(pessoas[i], pessoas[j]);

          matrizDistancias[j][i] = matrizDistancias[i][j];
        }
      }
    }
    return matrizDistancias;
  }

  public Pessoa[] getSimilar(Pessoa pessoa, int n) {
    Float[] distanceVector = ordenaCrescente(calcDistanceVector(pessoa));
    Pessoa[] similarPessoa = new Pessoa[n];
    int limiteArray = 0;

    for (int i = 0; i < distanceVector.length && limiteArray < n; i++) {
      Pessoa found = foundPessoa(pessoa, distanceVector[i]);
      if (found != null) {
        similarPessoa[limiteArray] = found;
        limiteArray++;
      }
    }
    return similarPessoa;
  }

  private Pessoa foundPessoa(Pessoa pessoa, Float distance) {
    float delta = 0.001f;
    for (int i = 0; i < dataset.size(); i++) {
      if (Math.abs(distance - calcDistance(pessoa, pessoas[i])) < delta) {
        if (!pessoa.equals(pessoas[i])) {
          return pessoas[i];
        }
      }
    }
    return null;
  }

  private Float[] ordenaCrescente(Float[] distanceVector) {
    for (int i = 0; i < distanceVector.length - 2; i++) {
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
}
