package repository;

import model.Doador;
import model.Beneficiario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private List<Doador> doadores = new ArrayList<>();
    private List<Beneficiario> beneficiarios = new ArrayList<>();

    public void salvarDoador(Doador doador) {
        doadores.add(doador);
    }

    public void salvarBeneficiario(Beneficiario beneficiario) {
        beneficiarios.add(beneficiario);
    }

    public List<Doador> listarDoadores() {
        return doadores;
    }

    public List<Beneficiario> listarBeneficiarios() {
        return beneficiarios;
    }
    
    public Beneficiario buscarBeneficiarioPorId(int id) {
        return beneficiarios.stream()
                            .filter(b -> b.getId() == id)
                            .findFirst()
                            .orElse(null);
    }
}
