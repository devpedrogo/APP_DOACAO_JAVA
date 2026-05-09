package service;

import model.Doador;
import model.Beneficiario;
import model.ItemDoacao;
import model.StatusItem;
import repository.UsuarioRepository;
import repository.ItemRepository;
import util.MenuUtils;
import java.time.LocalDate;


public class CadastroService {

    private UsuarioRepository usuarioRepo;
    private ItemRepository itemRepo;

    public CadastroService(UsuarioRepository usuarioRepo, ItemRepository itemRepo) {
        this.usuarioRepo = usuarioRepo;
        this.itemRepo = itemRepo;
    }

    public void cadastrarDoador() {
        System.out.println("\n--- NOVO CADASTRO DE DOADOR ---");
        
        int id = MenuUtils.lerInteiro("ID: ");
        String nome = MenuUtils.lerString("Nome: ");
        String fone = MenuUtils.lerString("Telefone: ");
        String email = MenuUtils.lerString("Email: ");
        String endereco = MenuUtils.lerString("Endereco: ");
        
        Doador doador = new Doador();
        doador.setId(id);
        doador.setNome(nome);
        doador.setTelefone(fone);
        doador.setEmail(email);
        doador.setEndereco(endereco);

        usuarioRepo.salvarDoador(doador);

        System.out.println("Doador " + nome + " registrado com sucesso!");
    }

    public void cadastrarBeneficiario() {
        System.out.println("\n--- NOVO CADASTRO DE BENEFICIÁRIO ---");

        int id = MenuUtils.lerInteiro("ID: ");
        String nome = MenuUtils.lerString("Nome: ");
        String fone = MenuUtils.lerString("Telefone: ");
        String email = MenuUtils.lerString("Email: ");
        String endereco = MenuUtils.lerString("Endereco: ");
        String tipo = MenuUtils.lerString("Tipo (individual/familia/organizacao): ");
        int prioridade = MenuUtils.lerInteiro("Nivel de Prioridade (1-5): ");

        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setId(id);
        beneficiario.setNome(nome);
        beneficiario.setEmail(email);
        beneficiario.setTelefone(fone);
        beneficiario.setEndereco(endereco);
        beneficiario.setTipo(tipo);
        beneficiario.setNivelPrioridade(prioridade);

        usuarioRepo.salvarBeneficiario(beneficiario);
        System.out.println("Beneficiário " + nome + " registrado com sucesso!");
    }

    public void cadastrarItem() {
        System.out.println("\n--- NOVO CADASTRO DE ITEM PARA DOAÇÃO ---");

        int id = MenuUtils.lerInteiro("ID do Item: ");
        String nome = MenuUtils.lerString("Nome do Item: ");
        String descricao = MenuUtils.lerString("Descrição: ");
        int quantidade = MenuUtils.lerInteiro("Quantidade: ");
        String categoria = MenuUtils.lerString("Categoria: ");
        String estado = MenuUtils.lerString("Estado de Conservação: ");
        LocalDate dataCadastro = LocalDate.now();
        StatusItem status = StatusItem.DISPONIVEL;
        
        ItemDoacao item = new ItemDoacao();
        item.setId(id);
        item.setNome(nome);
        item.setDescricao(descricao);
        item.setQuantidade(quantidade);
        item.setCategoria(categoria);
        item.setEstadoConservacao(estado);
        item.setDataCadastro(dataCadastro);
        item.setStatus(status);

        itemRepo.salvar(item);
        System.out.println("Item " + nome + " registrado com sucesso!");

    }
}
