package br.vivenciasextensao.prancheta.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "acaoSocial_id")
	private AcaoSocial acaoSocial;
        
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public AcaoSocial getAcaoSocial() {
        return acaoSocial;
    }

    public void setAcaoSocial(AcaoSocial acaoSocial) {
        this.acaoSocial = acaoSocial;
    }
    
}
