package main;

import repository.ItemRepository;
import repository.UsuarioRepository;
import service.CadastroService;
import service.SolicitacaoService;
import util.MenuUtils;

public class Main {
    public static void main(String[] args) {
        ItemRepository itemRepo = new ItemRepository();
        UsuarioRepository usuarioRepo = new UsuarioRepository();
        CadastroService cadastroService = new CadastroService(usuarioRepo, itemRepo);
        SolicitacaoService solicitacaoService = new SolicitacaoService(itemRepo, usuarioRepo);
        int opcao = 0;

        do {
            System.out.println("=== REDE SOLIDÁRIA ===");
            System.out.println("1. Cadastrar Doador");
            System.out.println("2. Cadastrar Beneficiário");
            System.out.println("3. Cadastrar Item para Doação");
            System.out.println("4. Realizar Solicitação");
            System.out.println("5. Listar Tudo");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = MenuUtils.lerInteiro("");

            switch(opcao) {
                case 1:
                    cadastroService.cadastrarDoador(); 
                    break;
                case 2:
                    cadastroService.cadastrarBeneficiario(); 
                    break;
                case 3:
                    cadastroService.cadastrarItem(); 
                    break;
                case 4:
                    solicitacaoService.realizarSolicitacao();
                    break;
                case 5:
                    System.out.println("\n--- DOADORES ---");
                    usuarioRepo.listarDoadores().forEach(d -> System.out.println(d.getNome()));
                    System.out.println("\n--- BENEFICIÁRIOS ---");
                    usuarioRepo.listarBeneficiarios().forEach(b -> System.out.println(b.getNome()));
                    System.out.println("\n--- ITENS PARA DOAÇÃO ---");
                    itemRepo.listarTodos().forEach(i -> System.out.println(i.getNome()));
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}
