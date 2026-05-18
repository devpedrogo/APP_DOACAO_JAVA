package main;

import repository.ItemRepository;
import repository.SolicitacaoRepository;
import repository.DoacaoRepository;
import repository.UsuarioRepository;
import service.CadastroService;
import service.SolicitacaoService;
import service.ConsultaService;
import util.MenuUtils;

public class Main {
    public static void main(String[] args) {
        ItemRepository itemRepo = new ItemRepository();
        UsuarioRepository usuarioRepo = new UsuarioRepository();
        CadastroService cadastroService = new CadastroService(usuarioRepo, itemRepo);
        SolicitacaoRepository solRepo = new SolicitacaoRepository();
        DoacaoRepository doacaoRepo = new DoacaoRepository();
        SolicitacaoService solicitacaoService = new SolicitacaoService(itemRepo, usuarioRepo, solRepo, doacaoRepo);
        ConsultaService consultaService = new ConsultaService(itemRepo, usuarioRepo, doacaoRepo);
        int opcao = 0;

        do {
            System.out.println("=== REDE SOLIDÁRIA ===");
            System.out.println("1. Cadastrar Doador");
            System.out.println("2. Cadastrar Beneficiário");
            System.out.println("3. Cadastrar Item para Doação");
            System.out.println("4. Realizar Solicitação");
            System.out.println("5. Listar Doadores");
            System.out.println("6. Listar Beneficiários");
            System.out.println("7. Listar Itens Disponíveis");
            System.out.println("8. Filtrar Itens por Categoria");
            System.out.println("9. Filtrar Itens por Status");
            System.out.println("10. Concluir Doação");
            System.out.println("0. Sair");
            opcao = MenuUtils.lerInteiro("Escolha: ");

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
                    consultaService.listarDoadores();
                    break;
                case 6:
                    consultaService.listarBeneficiarios();
                    break;
                case 7:
                    consultaService.listarItensDisponiveis();
                    break;
                case 8:
                    consultaService.filtrarItensPorCategoria();
                    break;
                case 9:
                    consultaService.filtrarItensPorStatus();
                    break;
                case 10:
                    solicitacaoService.concluirDoacao();
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
