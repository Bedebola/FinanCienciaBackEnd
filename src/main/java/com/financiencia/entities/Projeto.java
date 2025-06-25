package com.financiencia.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Entity
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String tituloProjeto;

    @Column
    private String descricaoProjeto;

    @Column
    private String alunos;

    @Column
    private String email;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "universidade", nullable = false)
    private Universidade universidade;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cidade", nullable = false)
    private Cidade cidade;

    public Projeto() {
    }

    public Projeto(String tituloProjeto, String descricaoProjeto, String alunos, String email, Universidade universidade, Cidade cidade) {
        this.tituloProjeto = tituloProjeto;
        this.descricaoProjeto = descricaoProjeto;
        this.alunos = alunos;
        this.email = email;
        this.universidade = universidade;
        this.cidade = cidade;
    }

    public String getNomeCidade (){
        return cidade != null ? cidade.getNome() : null;
    }

    public String getNomeUniversidade (){
        return universidade != null ? universidade.getNome() : null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTituloProjeto() {
        return tituloProjeto;
    }

    public void setTituloProjeto(String tituloProjeto) {
        this.tituloProjeto = tituloProjeto;
    }

    public String getDescricaoProjeto() {
        return descricaoProjeto;
    }

    public void setDescricaoProjeto(String descricaoProjeto) {
        this.descricaoProjeto = descricaoProjeto;
    }

    public String getAlunos() {
        return alunos;
    }

    public void setAlunos(String alunos) {
        this.alunos = alunos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Universidade getUniversidade() {
        return universidade;
    }

    public void setUniversidade(Universidade universidade) {
        this.universidade = universidade;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
