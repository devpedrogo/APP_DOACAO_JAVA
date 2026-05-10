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

        int qtdSolicitada = MenuUtils.lerInteiro("Quantidade desejada: ");

        if (qtdSolicitada <= item.getQuantidade()) {
            int novaQuantidade = item.getQuantidade() - qtdSolicitada;
            item.setQuantidade(novaQuantidade);

            if (item.getQuantidade() == 0) {
                item.setStatus(StatusItem.RESERVADO);
            } else {
                item.setStatus(StatusItem.DISPONIVEL);
            }

            String justificativa = MenuUtils.lerString("Justificativa para a solicitação: ");
    
            Solicitacao sol = new Solicitacao();
            sol.setBeneficiario(benef);
            sol.setItem(item);
            sol.setQuantidadeSolicitada(qtdSolicitada);
            sol.setJustificativa(justificativa);
            sol.setStatus("PENDENTE");

            System.out.println("Solicitação registrada! Restam " + item.getQuantidade() + " unidades deste item.");
        } else {
            System.out.println("Erro: Quantidade solicitada excede o disponível! \nRestam apenas " + item.getQuantidade() + " unidades.");
        }

    }
}
