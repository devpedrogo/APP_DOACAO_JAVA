package model;

import java.time.LocalDate;

public class ItemDoacao {
    private int id;
    private String nome;
    private Doador doador;
    private String descricao;
    private int quantidade;
    private String categoria;
    private String estadoConservacao;
    private LocalDate dataCadastro;
    private StatusItem status;

    public ItemDoacao() {
    }

    public ItemDoacao(int id, String nome, Doador doador, String descricao, int quantidade, String categoria, String estadoConservacao, LocalDate dataCadastro, StatusItem status) {
        this.id = id;
        this.nome = nome;
        this.doador = doador;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.estadoConservacao = estadoConservacao;
        this.dataCadastro = dataCadastro;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doador getDoador() {
        return doador;
    }

    public void setDoador(Doador doador) {
        this.doador = doador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(String estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public StatusItem getStatus() {
        return status;
    }

    public void setStatus(StatusItem status) {
        this.status = status;
    }
}
