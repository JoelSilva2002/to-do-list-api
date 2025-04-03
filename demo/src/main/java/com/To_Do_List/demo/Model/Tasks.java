package com.To_Do_List.demo.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // gera o id automaticamente

    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING) // @Enumerated anotação do JPA para mapear atributos enum em entidades no banco de dados
    private Prioridade prioridade;

    public void setDataCriacao(LocalDateTime now) {
    }

    public enum Status {
        PENDENTE, EM_ANDAMENTO, CONCLUIDA
    }

    public enum Prioridade {
        BAIXA, MEDIA, ALTA
    }

    @PrePersist // executa antes dos dados irem para o banco
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
        if (this.status == null) {
            this.status = Status.PENDENTE;
        }
    }

    // Getters e Setters
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}

