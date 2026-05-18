package service;

import model.*;
import repository.*;
import util.MenuUtils;
import java.util.List;

public class ConsultaService {
    private ItemRepository itemRepo;
    private UsuarioRepository usuarioRepo;
    private DoacaoRepository doacaoRepo;

    public ConsultaService(ItemRepository itemRepo, UsuarioRepository usuarioRepo, DoacaoRepository doacaoRepo) {
        this.itemRepo = itemRepo;
        this.usuarioRepo = usuarioRepo;
        this.doacaoRepo = doacaoRepo;
    }

    public void listarDoadores() {

        if(usuarioRepo.listarDoadores().isEmpty()) {
            System.out.println("Nenhum doador cadastrado.");
            return;
        }

        System.out.println("\n--- LISTA DE DOADORES ---");
        usuarioRepo.listarDoadores().forEach(d -> 
            System.out.println("ID: " + d.getId() + " | Nome: " + d.getNome() + " | Email: " + d.getEmail() + " | Telefone: " + d.getTelefone() + " | Endereço: " + d.getEndereco()));
    }

    public void listarBeneficiarios() {

        if(usuarioRepo.listarBeneficiarios().isEmpty()) {
            System.out.println("Nenhum beneficiário cadastrado.");
            return;
        }

        System.out.println("\n--- LISTA DE BENEFICIÁRIOS ---");
        usuarioRepo.listarBeneficiarios().forEach(b -> 
            System.out.println("ID: " + b.getId() + " | Nome: " + b.getNome() + " | Prioridade: " + b.getNivelPrioridade()));
    }

    public void listarItensDisponiveis() {
        System.out.println("\n--- ITENS DISPONÍVEIS PARA DOAÇÃO --- ");
        List<ItemDoacao> disponiveis = itemRepo.listarDisponiveis();
        if (disponiveis.isEmpty()) {
            System.out.println("Nenhum item disponível no momento.");
        } else {
            disponiveis.forEach(i -> 
                System.out.println("ID: " + i.getId() + " | " + i.getNome() + " | Qtd: " + i.getQuantidade()));
        }
    }

    public void filtrarItensPorCategoria() {
        CategoriaItem cat = MenuUtils.lerEnum("Digite a categoria para filtrar: ", CategoriaItem.class);

        if(itemRepo.filtrarPorCategoria(cat).isEmpty()) {
            System.out.println("Nenhum item encontrado na categoria: " + cat);
            return;
        }

        System.out.println("\n--- ITENS NA CATEGORIA: " + cat + " --- ");
        itemRepo.filtrarPorCategoria(cat).forEach(i -> 
            System.out.println(i.getNome() + " | Status: " + i.getStatus()));
    }

    public void filtrarItensPorStatus() {
        StatusItem status = MenuUtils.lerEnum("Digite o status para filtrar: ", StatusItem.class);

        if (status == StatusItem.ENTREGUE) {
            System.out.println("\n--- HISTÓRICO DE ITENS ENTREGUES ---");
            List<DoacaoEfetivada> entregues = doacaoRepo.listarTodas();
            
            if (entregues.isEmpty()) {
                System.out.println("Nenhum item foi entregue até o momento.");
                return;
            }
            
            entregues.forEach(d -> 
                System.out.println("Item: " + d.getSolicitacao().getItem().getNome() + 
                                " | Qtd: " + d.getSolicitacao().getQuantidadeSolicitada() + 
                                " | Entregue para: " + d.getSolicitacao().getBeneficiario().getNome())
            );
            return;
        }

        List<ItemDoacao> itensFiltrados = itemRepo.filtrarPorStatus(status);

        if (itensFiltrados.isEmpty()) {
            System.out.println("Nenhum item encontrado no estoque com status: " + status);
            return;
        }

        System.out.println("\n--- ITENS NO ESTOQUE COM STATUS: " + status + " --- ");
        itensFiltrados.forEach(i -> 
            System.out.println("ID: " + i.getId() + " | " + i.getNome() + " | Qtd Lote: " + i.getQuantidade() + " | Categoria: " + i.getCategoria())
        );
    }
}
