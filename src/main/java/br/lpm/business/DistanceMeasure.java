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
}
