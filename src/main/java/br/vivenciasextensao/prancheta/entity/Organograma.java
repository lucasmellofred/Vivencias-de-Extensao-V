package br.vivenciasextensao.prancheta.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Organograma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    private Item item;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    private AcaoSocial acao_social;

    @NotBlank(message = "Campo quantidade necessaria não pode ser em branco")
    private int quantidade_necessaria;

    @NotBlank(message = "Campo quantidade_pronta não pode ser em branco")
    private int quantidade_pronta;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public AcaoSocial getAcao_social() {
        return acao_social;
    }
    public void setAcao_social(AcaoSocial acao_social) {
        this.acao_social = acao_social;
    }
    public int getQuantidade_necessaria() {
        return quantidade_necessaria;
    }
    public void setQuantidade_necessaria(int quantidade_necessaria) {
        this.quantidade_necessaria = quantidade_necessaria;
    }
    public int getQuantidade_pronta() {
        return quantidade_pronta;
    }
    public void setQuantidade_pronta(int quantidade_pronta) {
        this.quantidade_pronta = quantidade_pronta;
    }
    
}
