package model;

import java.time.LocalDate;

public class DoacaoEfetivada {
    private int id;
    private ItemDoacao item;
    private Doador doador;
    private Beneficiario beneficiario;
    private LocalDate data;
    private String observacoes;

    public DoacaoEfetivada(int id, ItemDoacao item, Doador doador, Beneficiario beneficiario, String observacoes) {
        this.id = id;
        this.item = item;
        this.doador = doador;
        this.beneficiario = beneficiario;
        this.data = LocalDate.now();
        this.observacoes = observacoes;
    }
    
    public int getId() {
        return id;
    }

    public ItemDoacao getItem() {
        return item;
    }

    public Doador getDoador() {
        return doador;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public LocalDate getData() {
        return data;
    }

    public String getObservacoes() {
        return observacoes;
    }

}
