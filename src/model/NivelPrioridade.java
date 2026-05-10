package model;

public enum NivelPrioridade {
    BAIXA(1), MEDIA(2), ALTA(3), URGENTE(4), CRITICA(5);

    private final int valor;

    NivelPrioridade(int valor){
        this.valor = valor; 
    }

    public int getValor(){
        return valor; 
    }
}
