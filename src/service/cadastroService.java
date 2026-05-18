package service;

import model.Doador;
import model.Beneficiario;
import model.ItemDoacao;
import model.StatusItem;
import model.CategoriaItem;
import model.EstadoConservacao;
import model.TipoBeneficiario;
import model.NivelPrioridade;
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
        TipoBeneficiario tipo = MenuUtils.lerEnum("Tipo de Beneficiário: ", TipoBeneficiario.class);
        NivelPrioridade prioridade = MenuUtils.lerEnum("Nível de Prioridade: ", NivelPrioridade.class);

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

        int idDoador = MenuUtils.lerInteiro("ID do Doador responsável: ");
        Doador doadorEncontrado = usuarioRepo.buscarDoadorPorId(idDoador);

        if (doadorEncontrado == null) {
            System.out.println("Erro: Doador não encontrado! Cadastre o doador antes do item.");
            return;
        }

        int id = MenuUtils.lerInteiro("ID do Item: ");
        String nome = MenuUtils.lerString("Nome do Item: ");
        String descricao = MenuUtils.lerString("Descrição: ");
        int quantidade = MenuUtils.lerInteiro("Quantidade: ");
        CategoriaItem categoria = MenuUtils.lerEnum("Categoria: ", CategoriaItem.class);
        EstadoConservacao estado = MenuUtils.lerEnum("Estado de Conservação: ", EstadoConservacao.class);
        LocalDate dataCadastro = LocalDate.now();
        StatusItem status = StatusItem.DISPONIVEL;
        
        ItemDoacao item = new ItemDoacao();
        item.setDoador(doadorEncontrado);
        item.setId(id);
        item.setNome(nome);
        item.setDescricao(descricao);
        item.setQuantidade(quantidade);
        item.setCategoria(categoria);
        item.setEstadoConservacao(estado);
        item.setDataCadastro(dataCadastro);
        item.setStatus(status);

        itemRepo.salvar(item);
        System.out.println("Item " + nome + " vinculado ao doador " + doadorEncontrado.getNome());

    }
}
