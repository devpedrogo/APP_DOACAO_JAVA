package repository;

import model.ItemDoacao;
import model.StatusItem;
import model.CategoriaItem;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemRepository {
    private List<ItemDoacao> itens = new ArrayList<>();

    public void salvar(ItemDoacao item) {
        itens.add(item);
    }

    public List<ItemDoacao> listarTodos() {
        return itens;
    }

    public ItemDoacao buscarPorId(int id) {
        return itens.stream()
                    .filter(i -> i.getId() == id)
                    .findFirst()
                    .orElse(null);
    }
 
    public List<ItemDoacao> filtrarPorStatus(StatusItem status) {
        return itens.stream()
                    .filter(i -> i.getStatus() == status)
                    .collect(Collectors.toList());
    }

    // Filtra apenas itens com status DISPONIVEL 
    public List<ItemDoacao> listarDisponiveis() {
        return itens.stream()
                    .filter(i -> i.getStatus() == StatusItem.DISPONIVEL)
                    .collect(Collectors.toList());
    }

    // Filtra por categoria (Ex: "Alimentos", "Roupas") 
    public List<ItemDoacao> filtrarPorCategoria(CategoriaItem categoria) {
        return itens.stream()
                    .filter(i -> i.getCategoria().equals(categoria))
                    .collect(Collectors.toList());
    }
}
