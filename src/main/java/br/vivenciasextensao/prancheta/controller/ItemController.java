package br.vivenciasextensao.prancheta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.vivenciasextensao.prancheta.entity.AcaoSocial;
import br.vivenciasextensao.prancheta.entity.Item;
import br.vivenciasextensao.prancheta.repository.AcaoSocialRepository;
import br.vivenciasextensao.prancheta.repository.ItemRepository;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


@Controller
@RequestMapping("/acaoSocial/{acaoSocialId}/itens")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private AcaoSocialRepository acaoSocialRepository;

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

    @GetMapping("/cadastrar-item-acao-social/")
    public String cadastrarItemAcaoSocial(Model model, @PathVariable("acaoSocialId") Long acaoSocialId) {
        AcaoSocial acaoSocial = acaoSocialRepository.findById(acaoSocialId).get();
        model.addAttribute("acaoSocial", acaoSocial);
        return "gestaoDoacao/html/cadastroAcao";
    }

  

    @Transactional
    @PostMapping("/save")
    public String saveItems(@ModelAttribute("acaoSocialId") Long acaoSocialId, @RequestParam(name = "nome", required = true) String nome, @RequestParam(name = "descricao", required = true) String descricao, @RequestParam(name = "codigoIdentificacao", required = true) String codigoIdentificacao, @RequestParam(name = "infoAdicional", required = true) String infoAdicional, @RequestParam(name = "categoria", required = true) String categoria, RedirectAttributes redirectAttributes) {
        // Primeiro, buscar a AcaoSocial pelo ID
        Optional<AcaoSocial> acaoSocialOptional = acaoSocialRepository.findById(acaoSocialId);
        
        if (acaoSocialOptional.isPresent()) {
            AcaoSocial acaoSocial = acaoSocialOptional.get();
            
            // Agora que temos o objeto AcaoSocial, podemos associá-lo ao item
            Item item = new Item();
            item.setNome(nome);
            item.setDescricao(descricao);
            item.setCodigoIdentificacao(codigoIdentificacao);
            item.setInfoAdicional(infoAdicional);
            item.setCategoria(categoria);
            item.setAcaoSocial(acaoSocial);
            itemRepository.save(item);
            
        } else {
            redirectAttributes.addFlashAttribute("message", "Ação social não encontrada");
        }

        return "redirect:/editar-acao-social/" + acaoSocialId;
    }

}
