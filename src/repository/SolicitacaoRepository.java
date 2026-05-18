package repository;

import model.Solicitacao;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoRepository {
    private List<Solicitacao> solicitacoes = new ArrayList<>();

    public void salvar(Solicitacao solicitacao) {
        solicitacoes.add(solicitacao);
    }

    public Solicitacao buscarPorId(int id) {
        return solicitacoes.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Solicitacao> listarTodas() {
        return this.solicitacoes;
    }
}
