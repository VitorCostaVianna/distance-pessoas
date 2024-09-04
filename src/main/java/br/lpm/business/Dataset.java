package br.lpm.business;

public class Dataset {
  private Pessoa[] pessoas;
  private static final int MAX_PESSOAS = 3;
  private int numPessoasVetor;

  public Dataset() {
    pessoas = new Pessoa[MAX_PESSOAS];
    this.numPessoasVetor = 0;
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

  public Pessoa[] getAll(){
    return pessoas;
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

  public float percentAdult(){
    int sum = 0;
    int total =0;
    for (int j = 0; j < size(); j++){
      if (pessoas[j] != null && pessoas[j].getDataDeNascimento() != null) {
        total++;
        if (pessoas[j].calculaIdade() >=  18) {
          sum++;
        }
    }
  }
    return (sum/(float)total) * 100;
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
    return ((float)isFeliz / total) * 100;
  }
}
