package com.To_Do_List.demo.dto;

import com.To_Do_List.demo.Model.Tasks;
import com.To_Do_List.demo.Model.Tasks.Status;
import com.To_Do_List.demo.Model.Tasks.Prioridade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class TaskDTO {
    private Long id;
    @NotBlank(message = "O título não pode estar vazio.")
    @Size(max = 100, message = "O título deve ter no máximo 100 caracteres.")
    private String titulo;
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres.")
    private String descricao;
    private LocalDateTime dataCriacao;
    private Status status;
    @NotNull(message = "A prioridade não pode ser nula.")
    private Tasks.Prioridade prioridade;

    // construtor padrão
    public TaskDTO() {
    }

    // construtor com parametros
    public TaskDTO(Long id, String titulo, String descricao, LocalDateTime dataCriacao, Status status, Prioridade prioridade) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.status = status;
        this.prioridade = prioridade;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
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
