package br.lpm.business;

public class DistanceMeasure {
    private Dataset dataset;
    private Pessoa[] pessoas = dataset.getAll();

    public DistanceMeasure(Dataset dataset){
        this.dataset = dataset;
    }

    private Float[] normalizeField(String fieldName){
        Float[] normalizedField = new Float[dataset.size() -1];
        
        if (fieldName.equalsIgnoreCase("Peso")){
            Float[] pesos = new Float[dataset.size() -1 ];
            float maxPeso = dataset.maxPeso();
            float minPeso = dataset.minPeso();
            for (int i = 0; i< dataset.size(); i++){
                pesos[i] = (float) pessoas[i].getPeso();
                normalizedField[i] = (pesos[i] - minPeso) / (maxPeso - minPeso); 
            }
            return normalizedField;
        }
        
        if (fieldName.equalsIgnoreCase("Altura")){
            Float[] alturas = new Float[dataset.size() -1 ];
            float maxAltura = dataset.maxAltura();
            float minAltura = dataset.minAltura();
            for (int i = 0; i< dataset.size(); i++){
                alturas[i] = (float) pessoas[i].getAltura();
                normalizedField[i] = (alturas[i] - minAltura) / (maxAltura - minAltura); 
            }
            return normalizedField;
        }

        if (fieldName.equalsIgnoreCase("Renda")){
            Float[] rendas = new Float[dataset.size() -1 ];
            float maxRenda = dataset.maxRenda();
            float minRenda = dataset.minRenda();
            for (int i = 0; i< dataset.size(); i++){
                rendas[i] = (float) pessoas[i].getRenda();
                normalizedField[i] = (rendas[i] - minRenda) / (maxRenda - minRenda); 
            }
            return normalizedField;
        }

        if (fieldName.equalsIgnoreCase("Idade")){
            Float[] idades = new Float[dataset.size() -1 ];
            float maxIdade = dataset.maxIdade();
            float minIdade = dataset.minIdade();
            for (int i = 0; i< dataset.size(); i++){
                idades[i] = (float) pessoas[i].calculaIdade();
                normalizedField[i] = (idades[i] - minIdade) / (maxIdade - minIdade); 
            }
            return normalizedField;
        }

        return null;
    }

    private int getPessoaPosition(Pessoa pessoa){
        for (int i =0 ; i < dataset.size() -1 ; i++){
             if (pessoas[i].equals(pessoa)){
                return i;
            }
         }
         return -1;
    }


    public float calcDistance(Pessoa firstPessoa, Pessoa secondPessoa){
        float sumDistances = 0;
        Float[] normalizedPesos = normalizeField("Peso");
        Float[] normalizedAlturas = normalizeField("Altura");
        Float[] normalizedIdades = normalizeField("Idade");
        Float[] normalizedRendas = normalizeField("Renda");
        int firstPessoaPosition = getPessoaPosition(firstPessoa);
        int secondPessoaPosition = getPessoaPosition(secondPessoa);
        final int TOTAL_ATRIBUTOS = 11;
       
        if (firstPessoa.getHobby().equals(secondPessoa.getHobby())){
            sumDistances++;
        }
        if (firstPessoa.getEstadoCivil().equals(secondPessoa.getEstadoCivil())){
            sumDistances++;
        }
        if (firstPessoa.getMoradia().equals(secondPessoa.getMoradia())){
            sumDistances++;
        }
        if (firstPessoa.getEscolaridade().equals(secondPessoa.getEscolaridade())){
            sumDistances++;
        }
        if (firstPessoa.getGenero().equals(secondPessoa.getGenero())){
            sumDistances++;
        }
        if (firstPessoa.getNaturalidade().equalsIgnoreCase(secondPessoa.getNaturalidade())){
            sumDistances++;
        }
        if (firstPessoa.getFeliz() == secondPessoa.getFeliz()){
            sumDistances++;
        }
       
        sumDistances += Math.pow(Math.abs(normalizedAlturas[firstPessoaPosition] - normalizedAlturas[secondPessoaPosition]), 2);
        sumDistances += Math.pow(Math.abs(normalizedPesos[firstPessoaPosition] - normalizedPesos[secondPessoaPosition]), 2);
        sumDistances += Math.pow(Math.abs(normalizedIdades[firstPessoaPosition] - normalizedIdades[secondPessoaPosition]), 2);
        sumDistances += Math.pow(Math.abs(normalizedRendas[firstPessoaPosition] - normalizedRendas[secondPessoaPosition]), 2);

        return (float) Math.sqrt(sumDistances /  TOTAL_ATRIBUTOS);

    }

    public Float[] calcDistanceVector(Pessoa pessoa){
        Float[] distanceVector = new Float[dataset.size() - 1 ];
        for (int i= 0;  i < distanceVector.length ; i++){
            distanceVector[i] = (pessoa != pessoas[i]) ? calcDistance(pessoa, pessoas[i]) : 0;
        }
        return distanceVector;
    }

    public Float[][] calcDistanceMatrix() {
       int row = dataset.size();
       int column = row;
       Float[][] matrizDistancias = new Float[row][column];
       for (int i =0 ; i < row ; i++){
        for (int j = i; j < column; j++){
            if (i == j){
                matrizDistancias[i][j] = (float)0;
            }
            if (j > i){
                matrizDistancias[i][j] = calcDistance(pessoas[i],pessoas[j]);
            }
            else {
                matrizDistancias[i][j] = matrizDistancias[j][i];
            }
        }
       } 
       return matrizDistancias;
    }
    public Pessoa[] getSimilar(Pessoa pessoa , int n){
        
        Float[] distanceVector = ordenaDescrescente(calcDistanceVector(pessoa));
        Pessoa[] similarPessoa = new Pessoa[n];
        for (int i = 0 ; i <= n ; i++){
           similarPessoa[i] = foundPessoa(pessoa, distanceVector[i]); 
        }
        return similarPessoa;
    }

    private Pessoa foundPessoa(Pessoa pessoa ,Float distance){
        for (int i = 0 ; i <= pessoas.length ; i++){
            if (distance == calcDistance(pessoa, pessoas[i])){
                return pessoas[i];
            }
        }
        return null;
    }

    private Float[] ordenaDescrescente(Float[] distanceVector){
        for (int i =0 ; i < distanceVector.length; i++){
            for (int j =0 ; j < distanceVector.length ; j++){

                if (distanceVector[j] < distanceVector[j+1]){

                    float mudaVetor = distanceVector[j];
                    distanceVector[j] = distanceVector[j+1];
                    distanceVector[j+1] = mudaVetor;
                }
            }
        }
        return distanceVector;
    }

}
