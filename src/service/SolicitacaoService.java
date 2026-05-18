package service;

import java.util.List;

import model.*;
import repository.*;
import util.MenuUtils;

public class SolicitacaoService {
    private ItemRepository itemRepo;
    private UsuarioRepository usuarioRepo;
    private SolicitacaoRepository solRepo;
    private DoacaoRepository doacaoRepo;
    private static int contadorId = 1;

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
    
        solicitacao.setStatus("CONCLUIDA");

        if (item.getQuantidade() == 0) {
            item.setStatus(StatusItem.ENTREGUE); 
        } else {
            item.setStatus(StatusItem.DISPONIVEL);
        }

        String obs = MenuUtils.lerString("Observações sobre a entrega: ");
        
        DoacaoEfetivada registro = new DoacaoEfetivada(
            contadorId++, 
            solicitacao,
            obs
        );

        doacaoRepo.salvar(registro);

        System.out.println("Entrega de " + solicitacao.getQuantidadeSolicitada() + " unidades do item '" + item.getNome() + "' concluída!");
        System.out.println("Estoque atual do item no sistema: " + item.getQuantidade() + " unidades.");
    }

    public void cancelarSolicitacao() {
        System.out.println("\n--- CANCELAR SOLICITAÇÃO ---");
        int idSolicitacao = MenuUtils.lerInteiro("Digite o ID da Solicitação para cancelar: ");
        
        Solicitacao solicitacao = solRepo.buscarPorId(idSolicitacao);

        if (solicitacao == null) {
            System.out.println("Erro: Solicitação não encontrada!");
            return;
        }

        if (!solicitacao.getStatus().equalsIgnoreCase("PENDENTE")) {
            System.out.println("Erro: Apenas solicitações PENDENTES podem ser canceladas.");
            return;
        }

        ItemDoacao item = solicitacao.getItem();
        int estoqueAtualizado = item.getQuantidade() + solicitacao.getQuantidadeSolicitada();
        item.setQuantidade(estoqueAtualizado);

        item.setStatus(StatusItem.DISPONIVEL);

        solicitacao.setStatus("CANCELADA");

        System.out.println("Sucesso! Solicitação #" + solicitacao.getId() + " foi CANCELADA.");
        System.out.println("Estoque do item '" + item.getNome() + "' reabastecido para " + estoqueAtualizado + " unidades.");
    }

    public void gerenciarSolicitacoesPendentes() {
        System.out.println("\n--- GERENCIAR SOLICITAÇÕES PENDENTES ---");

        List<Solicitacao> pendentes = solRepo.listarTodas().stream()
                .filter(s -> s.getStatus().equalsIgnoreCase("PENDENTE"))
                .toList();

        if (pendentes.isEmpty()) {
            System.out.println("Nenhuma solicitação pendente para gerenciar.");
            return;
        }

        System.out.println("-------------------------------------------------------------------");
        pendentes.forEach(s -> {
            System.out.println("ID: " + s.getId() +
                            " | Item: " + s.getItem().getNome() +
                            " | Qtd: " + s.getQuantidadeSolicitada() +
                            " | Beneficiário: " + s.getBeneficiario().getNome() +
                            " (Prioridade: " + s.getBeneficiario().getNivelPrioridade() + ")");
        });
        System.out.println("-------------------------------------------------------------------");

        int idEscolhido = MenuUtils.lerInteiro("Digite o ID da solicitação que deseja gerenciar (ou 0 para voltar): ");
        
        if (idEscolhido == 0) return;

        Solicitacao solicitacao = solRepo.buscarPorId(idEscolhido);

        if (solicitacao == null || !solicitacao.getStatus().equalsIgnoreCase("PENDENTE")) {
            System.out.println("Erro: ID inválido ou solicitação não está mais pendente!");
            return;
        }

        System.out.println("\nO que deseja fazer com a solicitação #" + idEscolhido + "?");
        System.out.println("[1] Efetivar (Concluir Entrega)");
        System.out.println("[2] Cancelar Solicitação");
        System.out.println("[0] Voltar sem alterar");
        int opcao = MenuUtils.lerInteiro("Opção: ");

        switch (opcao) {
            case 1 -> concluirDoacao();
            case 2 -> cancelarSolicitacao();
            default -> System.out.println("Operação cancelada.");
        }
    }
}
