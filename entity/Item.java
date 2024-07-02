package br.edu.univille.projeto_vivencias_extensao_V.entity;
//alterar o pacote conforme a necessidade
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;


@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 1000)
    @NotBlank(message = "Campo nome não pode ser em branco")
    private String nome;
    @NotBlank(message = "Campo nome não pode ser em branco")
    

    

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

    

}
