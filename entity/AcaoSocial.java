package br.edu.univille.projeto_fabrica_software.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.Date;
import java.time.LocalDate;

@Entity
public class AcaoSocial {

    //id
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
    public void getDataInicial() {
        return data_inicial;
    }
    public void setDataInicial(String data_inicial) {
        this.data_inicial = data_inicial;
    }
    public void getDataFinal() {
        return data_final;
    }
    public void setDataFinal(String data_final) {
        this.data_final = data_final;
    }
   

    
    
}
