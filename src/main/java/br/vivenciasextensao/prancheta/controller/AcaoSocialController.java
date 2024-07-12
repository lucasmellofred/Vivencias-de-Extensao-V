package br.vivenciasextensao.prancheta.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.vivenciasextensao.prancheta.entity.AcaoSocial;
import br.vivenciasextensao.prancheta.projection.AcaoSocialProjection;
import br.vivenciasextensao.prancheta.repository.AcaoSocialRepository;
import br.vivenciasextensao.prancheta.service.AcaoSocialService;
import br.vivenciasextensao.prancheta.service.ItemService;

@Controller
public class AcaoSocialController {
    @Autowired
    private AcaoSocialRepository acaoSocialRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private AcaoSocialService acaoSocialService;

    @GetMapping
    public String index(Model model) {
        List<AcaoSocial> acoesSociais = acaoSocialRepository.findAll();
        model.addAttribute("acoesSociais", acoesSociais);
        return "index";
    }
    
    /*@GetMapping("/listar-todas-acoes-sociais")
    public String acaoSocial(Model model) {
        List<AcaoSocial> acoesSociais = acaoSocialRepository.findAll();
        model.addAttribute("acoesSociais", acoesSociais);
        return "acoesSociais/todas-acoes-sociais";
    }*/
    @GetMapping("/listar-todas-acoes-sociais")
    public String listarTodasAcoesSociais(Model model) {
        List<AcaoSocial> acoesSociais = acaoSocialRepository.findAll();
        

        List<AcaoSocialProjection> countItensByAcaoSocial = acaoSocialService.countItensByAcaoSocial();
        Map<Long, Long> countItensMap = countItensByAcaoSocial.stream()
                .collect(Collectors.toMap(AcaoSocialProjection::getAcaoSocialId,
                                          AcaoSocialProjection::getCountItens));
        model.addAttribute("countItensMap", countItensMap);
        model.addAttribute("acoesSociais", acoesSociais);

        return "acoesSociais/todas-acoes-sociais";
    }

   

    @GetMapping("/nova-acao-social")
    public String novaAcaoSocial() {
        return "acoesSociais/nova-acao-social";
    }

    @GetMapping("/cadastrar-acao-social")
    public String cadastrarAcaoSocial(Model model) {
        model.addAttribute("doacao", new AcaoSocial());
        return "gestaoDoacao/html/cadastroAcao";
    }

    @PostMapping("/doacao/submit")
    public String doacaoSubmit(@ModelAttribute AcaoSocial acaoSocial) {
        acaoSocialRepository.save(acaoSocial);
        return "redirect:/cadastrar-acao-social";
    }

    @PostMapping("/salvar-acao-social")
    public String salvarAcaoSocial(@RequestParam(name = "nome", required = true) String nome,
                                   @RequestParam(name = "local", required = true) String local,
                                   @RequestParam(name = "data_inicial", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date data_inicial,
                                   @RequestParam(name = "data_final", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date data_final) {
        AcaoSocial acaoSocial = new AcaoSocial();
        acaoSocial.setNome(nome);
        acaoSocial.setLocal(local);
        acaoSocial.setData_inicial(data_inicial);
        acaoSocial.setData_final(data_final);

        acaoSocialRepository.save(acaoSocial);
        return "redirect:/listar-todas-acoes-sociais";
    }

    /*@GetMapping("/editar-acao-social/{id}")
    public String editarAcaoSocial(Model model, @PathVariable Long id) {
        AcaoSocial acaoSocial = acaoSocialRepository.findById(id).get();
        model.addAttribute("acaoSocial", acaoSocial);

        return "acoesSociais/editar-acao-social";
    }*/

    // O método acima editarAcaoSocial atribui apenas o objeto acaoSocial ao model, mas não os itens associados a ele. Então vamos refazer esse método para atribuir também os itens associados a acaoSocial ao model.
    @GetMapping("/editar-acao-social/{id}")
    public String editarAcaoSocial(Model model, @PathVariable Long id) {
        AcaoSocial acaoSocial = acaoSocialRepository.findById(id).get();
        model.addAttribute("acaoSocial", acaoSocial);
        model.addAttribute("itens", acaoSocial.getItens());

        return "acoesSociais/editar-acao-social";
    }

    /*@PostMapping("/salvar-acao-social/{id}")
    public String salvarAcaoSocial(@PathVariable("id") Long id,
                                   @RequestParam(name = "nome", required = true) String nome,
                                   @RequestParam(name = "local", required = true) String local,
                                   @RequestParam(name = "data_inicial", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date data_inicial,
                                   @RequestParam(name = "data_final", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date data_final) {
        AcaoSocial acaoSocial = acaoSocialRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid AcaoSocial ID:" + id));

        acaoSocial.setNome(nome);
        acaoSocial.setLocal(local);
        acaoSocial.setData_inicial(data_inicial);
        acaoSocial.setData_final(data_final);

        acaoSocialRepository.save(acaoSocial);
        return "redirect:/editar-acao-social/" + acaoSocial.getId();
    }*/
    @PostMapping("/salvar-acao-social/{id}")
    public String salvarAcaoSocial(@PathVariable("id") Long id,
                                   @RequestParam(name = "nome", required = true) String nome,
                                   @RequestParam(name = "local", required = true) String local,
                                   @RequestParam(name = "data_inicial", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date data_inicial,
                                   @RequestParam(name = "data_final", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date data_final,
                                   RedirectAttributes redirectAttributes) {
        AcaoSocial acaoSocial = acaoSocialRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid AcaoSocial ID:" + id));

        acaoSocial.setNome(nome);
        acaoSocial.setLocal(local);
        acaoSocial.setData_inicial(data_inicial);
        acaoSocial.setData_final(data_final);

        acaoSocialRepository.save(acaoSocial);
        return "redirect:/editar-acao-social/" + acaoSocial.getId();
    }

    @PostMapping("/deletar-acao-social/{id}")
    public String deletarAcaoSocial(@PathVariable("id") Long id) {
        AcaoSocial acaoSocial = acaoSocialRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid AcaoSocial ID:" + id));
        acaoSocialRepository.delete(acaoSocial);
        return "redirect:/listar-todas-acoes-sociais";
    }

}
