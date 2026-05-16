package service;

import model.*;
import repository.*;
import util.MenuUtils;

public class SolicitacaoService {
    private ItemRepository itemRepo;
    private UsuarioRepository usuarioRepo;
    private SolicitacaoRepository solRepo;
    private DoacaoRepository doacaoRepo;
    private static int contadorId = 1; // Simulação de ID auto-incremento

    public SolicitacaoService(ItemRepository itemRepo, UsuarioRepository usuarioRepo, SolicitacaoRepository solRepo, DoacaoRepository doacaoRepo) {
        this.itemRepo = itemRepo;
        this.usuarioRepo = usuarioRepo;
        this.solRepo = solRepo;
        this.doacaoRepo = doacaoRepo;
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
            sol.setId(contadorId);
            sol.setBeneficiario(benef);
            sol.setItem(item);
            sol.setQuantidadeSolicitada(qtdSolicitada);
            sol.setJustificativa(justificativa);
            sol.setStatus("PENDENTE");

            solRepo.salvar(sol); 
            contadorId++;    
            System.out.println("Solicitação registrada com ID: " + sol.getId());

            System.out.println("Solicitação registrada! Restam " + item.getQuantidade() + " unidades deste item.");
        } else {
            System.out.println("Erro: Quantidade solicitada excede o disponível! \nRestam apenas " + item.getQuantidade() + " unidades.");
        }
    }

    public void concluirDoacao() {
        System.out.println("\n--- FINALIZAR ENTREGA DE DOAÇÃO ---");
        int idSolicitacao = MenuUtils.lerInteiro("Digite o ID da Solicitação: ");
        
        Solicitacao solicitacao = solRepo.buscarPorId(idSolicitacao);

        if (solicitacao == null) {
            System.out.println("Erro: Solicitação não encontrada!");
            return;
        }

        if (solicitacao.getStatus().equalsIgnoreCase("CONCLUIDA")) {
            System.out.println("Erro: Esta doação já foi finalizada anteriormente.");
            return;
        }
 
        ItemDoacao item = solicitacao.getItem();
        item.setStatus(StatusItem.ENTREGUE);

        solicitacao.setStatus("CONCLUIDA");

        String obs = MenuUtils.lerString("Observações sobre a entrega: ");
        
        DoacaoEfetivada registro = new DoacaoEfetivada(
            contadorId++, 
            solicitacao,
            obs
        );

        doacaoRepo.salvar(registro);

        System.out.println("Item: " + item.getNome() + " entregue a " + solicitacao.getBeneficiario().getNome());
        System.out.println("A doação foi concluída e registrada no histórico.");
    }
}
