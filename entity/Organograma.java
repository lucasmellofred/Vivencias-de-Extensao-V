package br.edu.univille.projeto_fabrica_software.entity;

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

    public AcaoSocial getAcaoSocial() {
        return acao_social;
    }

    public void setAcaoSocial(AcaoSocial acao_social) {
        this.acao_social = acao_social;
    }

    public void setQuantidadePronta(int quantidade_pronta) {
        this.quantidade_pronta = quantidade_pronta;
    }

    public int getQuantidadePronta(){
        return quantidade_pronta;
    }

    public int getQuantidadeFaltante(){
        return quantidade_faltante;
    }

    public void setQuantidadeFaltante(int quantidade_faltante){
        this.quantidade_faltante = quantidade_faltante;
    }

    

}
