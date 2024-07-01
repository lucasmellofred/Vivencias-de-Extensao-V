package br.vivenciasextensao.prancheta.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity
public class AcaoSocial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Campo codigo não pode ser em branco")
    private String nome;

    @NotBlank(message = "Campo estado reparo não pode ser em branco")
    private String local;

    @NotBlank(message = "Campo data final não pode ser em branco")
    private Date data_final;

    @NotBlank(message = "Campo data inicial não pode ser em branco")
    private Date data_inicial;

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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
    
    public Date getData_final() {
        return data_final;
    }
    
    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }
    
    public Date getData_inicial() {
        return data_inicial;
    }
    
    public void setData_inicial(Date data_inicial) {
        this.data_inicial = data_inicial;
    }
    
}
