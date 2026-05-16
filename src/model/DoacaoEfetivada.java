package model;

import java.time.LocalDate;

public class DoacaoEfetivada {
    private int id;
    private Solicitacao solicitacao;
    private LocalDate data;
    private String observacoes;

    public DoacaoEfetivada(int id, Solicitacao solicitacao, String observacoes) {
        this.id = id;
        this.solicitacao = solicitacao;
        this.data = LocalDate.now();
        this.observacoes = observacoes;
    }
    
    public int getId() {
        return id;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public LocalDate getData() {
        return data;
    }

    public String getObservacoes() {
        return observacoes;
    }

}
