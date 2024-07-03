package br.vivenciasextensao.prancheta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.vivenciasextensao.prancheta.entity.Item;
import br.vivenciasextensao.prancheta.repository.ItemRepository;
import java.util.List;


@Controller
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/todos-itens")
    public String item(Model model){
        List<Item> itens = itemRepository.findAll();
        model.addAttribute("itens", itens);
        return "items/todos-itens";
    }

    @GetMapping("/novo-item")
    public String novoItem() {
        return "itens/novo-item";
    }

    @PostMapping("/salvar-itens")
    public String salvarItem(@RequestParam(name = "nome", required = true) String nome){
        Item item = new Item();
        item.setNome(nome);

        itemRepository.save(item);
        return "redirect:/todos-itens";
    }

    @GetMapping("/editar-itens/{id}")
    public String editarItem(Model model, @PathVariable Long id) {
        Item item = itemRepository.findById(id).get();
        model.addAttribute("item", item);
        return "acoesSociais/editar-itens";
    }

    @PostMapping("/salvar-itens/{id}")
    public String salvarItem(@PathVariable("id") Long id,
                             @RequestParam(name = "nome", required = true) String nome) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Item ID:" + id));

        item.setNome(nome);

        itemRepository.save(item);
        return "redirect:/todos-itens";
    }

    @PostMapping("/deletar-itens/{id}")
    public String deletarItem(@PathVariable("id") Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Item ID:" + id));
        itemRepository.delete(item);
        return "redirect:/todos-itens";
    }
}
