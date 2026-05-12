package repository;

import model.DoacaoEfetivada;
import java.util.ArrayList;
import java.util.List;

public class DoacaoRepository {
    private List<DoacaoEfetivada> doacoesRealizadas = new ArrayList<>();

    public void salvar(DoacaoEfetivada doacao) {
        doacoesRealizadas.add(doacao);
    }

    public List<DoacaoEfetivada> listarTodas() {
        return doacoesRealizadas;
    }
}
