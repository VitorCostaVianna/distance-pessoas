package br.lpm.business;

public class DistanceMeasure {
  private Dataset dataset;
  private Pessoa[] pessoas;

  public DistanceMeasure(Dataset dataset) {
    this.dataset = dataset;
    pessoas = dataset.getAll();
  }

  private int getPessoaPosition(Pessoa pessoa) {
    if (pessoas == null || pessoa == null) {
        return -1;
    }

    for (int i = 0; i < dataset.size(); i++) {
        if (pessoas[i] != null && pessoas[i].equals(pessoa)) {
            return i;
        }
    }
    return -1;
    }


    public float calcDistance(Pessoa firstPessoa, Pessoa secondPessoa) {
      if (firstPessoa == null || secondPessoa == null) {
          return -1;
      }

      int firstPessoaPosition = getPessoaPosition(firstPessoa);
      int secondPessoaPosition = getPessoaPosition(secondPessoa);
      if (firstPessoaPosition == -1 || secondPessoaPosition == -1) {
          return -1;
      }
      float sumDistances = 0;

      Float[] normalizedPesos = dataset.normalizeField("Peso");
      Float[] normalizedAlturas = dataset.normalizeField("Altura");
      Float[] normalizedIdades = dataset.normalizeField("Idade");
      Float[] normalizedRendas = dataset.normalizeField("Renda");
  
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

      sumDistances += Math.pow(Math.abs(normalizedAlturas[firstPessoaPosition] - normalizedAlturas[secondPessoaPosition]), 2);
      sumDistances += Math.pow(Math.abs(normalizedPesos[firstPessoaPosition] - normalizedPesos[secondPessoaPosition]), 2);
      sumDistances += Math.pow(Math.abs(normalizedIdades[firstPessoaPosition] - normalizedIdades[secondPessoaPosition]), 2);
      sumDistances += Math.pow(Math.abs(normalizedRendas[firstPessoaPosition] - normalizedRendas[secondPessoaPosition]), 2);
  
      return (float) Math.sqrt(sumDistances / TOTAL_ATRIBUTOS);
  }
  

public Float[] calcDistanceVector(Pessoa pessoa) {
    if (pessoas == null || pessoa == null) {
        return new Float[0]; 
    }

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
