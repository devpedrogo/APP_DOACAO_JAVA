package model;

public class Beneficiario extends Usuario {
    private TipoBeneficiario tipo;
    private NivelPrioridade nivelPrioridade;

    public Beneficiario() {
        super();
    }

    public Beneficiario(int id, String nome, String email, String telefone, String endereco, TipoBeneficiario tipo, NivelPrioridade nivelPrioridade) {
        super(id, nome, email, telefone, endereco);
        this.tipo = tipo;
        this.nivelPrioridade = nivelPrioridade;
    }

    public TipoBeneficiario getTipo() {
        return tipo;
    }

    public void setTipo(TipoBeneficiario tipo) {
        this.tipo = tipo;
    }

    public NivelPrioridade getNivelPrioridade() {
        return nivelPrioridade;
    }

    public void setNivelPrioridade(NivelPrioridade nivelPrioridade) {
        this.nivelPrioridade = nivelPrioridade;
    }
}
