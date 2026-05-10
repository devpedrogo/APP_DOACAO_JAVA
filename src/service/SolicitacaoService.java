package service;

import model.*;
import repository.*;
import util.MenuUtils;

public class SolicitacaoService {
    private ItemRepository itemRepo;
    private UsuarioRepository usuarioRepo;

    public SolicitacaoService(ItemRepository itemRepo, UsuarioRepository usuarioRepo) {
        this.itemRepo = itemRepo;
        this.usuarioRepo = usuarioRepo;
    }

    public void realizarSolicitacao() {
        System.out.println("\n--- NOVA SOLICITAÇÃO ---");
        
        int idBenef = MenuUtils.lerInteiro("ID do Beneficiário: ");
        Beneficiario benef = usuarioRepo.buscarBeneficiarioPorId(idBenef);
        
        if (benef == null) {
            System.out.println("Erro: Beneficiário não encontrado!");
            return;
        }

        int idItem = MenuUtils.lerInteiro("ID do Item desejado: ");
        ItemDoacao item = itemRepo.buscarPorId(idItem);

        // REGRA DE NEGÓCIO: Impedir solicitação de item indisponível [cite: 85]
        if (item == null || item.getStatus() != StatusItem.DISPONIVEL) {
            System.out.println("Erro: Item não disponível para solicitação!");
            return;
        }

        int qtd = MenuUtils.lerInteiro("Quantidade desejada: ");

        if (qtd > item.getQuantidade()) {
            System.out.println("Erro: Quantidade superior ao estoque disponível!");
            return;
        }

        Solicitacao sol = new Solicitacao();
        sol.setBeneficiario(benef);
        sol.setItem(item);
        sol.setQuantidadeSolicitada(qtd);
        sol.setStatus("PENDENTE");

        item.setStatus(StatusItem.RESERVADO);
        
        System.out.println("Solicitação registrada! O item agora está RESERVADO.");
    }
}
