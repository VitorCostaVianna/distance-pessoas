package br.lpm.business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa {
  DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private String nome;
  private LocalDate dataDeNascimento;
  private Genero genero;
  private Float altura;
  private int peso;
  private float renda;
  private String naturalidade;
  private Hobby hobby;
  private EstadoCivil estadoCivil;
  private Escolaridade escolaridade;
  private boolean feliz;
  private Moradia moradia;

  public Pessoa() {
    this.setNome("Nome Padrao");
    this.altura = 0f;
    this.peso = 0;
    this.renda = 0;
    this.dataDeNascimento = LocalDate.parse("01/01/2024", fmt);

  }

  public Pessoa(
      String nome,
      LocalDate dataDeNascimento,
      Genero genero,
      Float altura,
      int peso,
      float renda,
      String naturalidade,
      Hobby hobby,
      EstadoCivil estadoCivil,
      Escolaridade escolaridade,
      boolean feliz,
      Moradia moradia) {
    this.setNome(nome);
    this.setDataDeNascimento(dataDeNascimento);
    this.setGenero(genero);
    this.setAltura(altura);
    this.setPeso(peso);
    this.setRenda(renda);
    this.setNaturalidade(naturalidade);
    this.setHobby(hobby);
    this.setEstadoCivil(estadoCivil);
    this.setEscolaridade(escolaridade);
    this.setFeliz(feliz);
    this.setMoradia(moradia);
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    if (validaNome(nome)) {
      this.nome = nome;
    }
  }

  public LocalDate getDataDeNascimento() {
    return dataDeNascimento;
  }

  public void setDataDeNascimento(LocalDate dataDeNascimento) {
    if (validaDataDeNascimento(dataDeNascimento)) {
      this.dataDeNascimento = dataDeNascimento;
    }
  }

  public Genero getGenero() {
    return genero;
  }

  public void setGenero(Genero genero) {
    if (genero != null){
      this.genero = genero;
    }
  }

  public Float getAltura() {
    return altura;
  }

  public void setAltura(Float altura) {
    if (validaAltura(altura)) {
      this.altura = altura;
    }
  }

  public int getPeso() {
    return peso;
  }

  public void setPeso(int peso) {
    if (validaPeso(peso)) {
      this.peso = peso;
    }
  }

  public float getRenda() {
    return renda;
  }

  public void setRenda(float renda) {
    if (validaRenda(renda)) {
      this.renda = renda;
    }
  }

  public String getNaturalidade() {
    return naturalidade;
  }

  public void setNaturalidade(String naturalidade) {
    if (naturalidade != null){
      this.naturalidade = naturalidade;
    }
  }

  public Hobby getHobby() {
    return hobby;
  }

  public void setHobby(Hobby hobby) {
    if (hobby != null){
      this.hobby = hobby;
    }
  }

  public EstadoCivil getEstadoCivil() {
    return estadoCivil;
  }

  public void setEstadoCivil(EstadoCivil estadoCivil) {
    if (estadoCivil != null){
      this.estadoCivil = estadoCivil;
    }
  }

  public Escolaridade getEscolaridade() {
    return escolaridade;
  }

  public void setEscolaridade(Escolaridade escolaridade) {
    if (escolaridade != null){
      this.escolaridade = escolaridade;
    }
  }

  public boolean getFeliz() {
    return feliz;
  }

  public void setFeliz(boolean feliz) {
    this.feliz = feliz;
  }

  public Moradia getMoradia() {
    return moradia;
  }

  public void setMoradia(Moradia moradia) {
    if (moradia != null){
      this.moradia = moradia;
    }
  }

  private boolean validaRenda(float renda) {
    return renda >= 0;
  }

  private boolean validaPeso(int peso) {
    return peso > 0 && peso < 600;
  }

  public boolean validaNome(String nomee) {
    String nome = nomee.toLowerCase();
    for (int i = 0; i < nome.length(); i++) {
      char letraNome = nome.charAt(i);
      if (!((letraNome >= 'a' && letraNome <= 'z')
          || (letraNome >= 'A' && letraNome <= 'Z')
          || letraNome == ' ' && nome != null)) {
        return false;
      }
    }
    return true;
  }

  public boolean validaDataDeNascimento(LocalDate dataDeNascimento) {
    LocalDate now = LocalDate.now();
    return (dataDeNascimento.isBefore(now) && dataDeNascimento != null);
  }

  public boolean validaAltura(Float altura) {
    return altura > 0 && altura < 2.60;
  }

  public String ImprimeFeliz() {
    if (!feliz) {
      return nome + " é infeliz";
    }
    return nome + " é feliz";
  }

  public int calculaIdade(){
    int anoAtual = LocalDate.now().getYear();  
    return anoAtual -  dataDeNascimento.getYear();
  }

  @Override
  public String toString() {
    return "\n -----Dados da Pessoa-----\n"
        + " \n nome: "
        + nome
        + ", \n Data De Nascimento: "
        + dataDeNascimento.format(fmt)
        + ", \n Genero: "
        + genero
        + ", \n Altura: "
        + altura
        + ", \n Peso: "
        + peso
        + ", \n Renda: "
        + renda
        + ", \n Naturalidade: "
        + naturalidade
        + ", \n Hobby: "
        + hobby
        + ", \n Estado Civil: "
        + estadoCivil
        + ", \n Escolaridade: "
        + escolaridade
        + ", \n "
        + ImprimeFeliz()
        + ", \n Moradia: "
        + moradia;
  }
}
