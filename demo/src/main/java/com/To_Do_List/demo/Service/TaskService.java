package com.To_Do_List.demo.Service;

import com.To_Do_List.demo.Model.Tasks;
import com.To_Do_List.demo.Repository.Repositorio;
import com.To_Do_List.demo.dto.TaskDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class TaskService {

    private final Repositorio repositorio;

    public TaskService(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    // Metodo para criar uma nova tarefa
    public TaskDTO criarTarefa(TaskDTO taskDTO) {
        Tasks task = new Tasks();
        task.setTitulo(taskDTO.getTitulo());
        task.setDescricao(taskDTO.getDescricao());
        task.setDataCriacao(LocalDateTime.now()); // Define a data de criação como agora
        task.setStatus(taskDTO.getStatus() != null ? taskDTO.getStatus() : Tasks.Status.PENDENTE);
        task.setPrioridade(taskDTO.getPrioridade());

        Tasks novaTarefa = repositorio.save(task); // Salva no banco
        return toDTO(novaTarefa); // Converte a entidade para DTO antes de retornar
    }


    public List<TaskDTO> listarTarefas() {
        List<Tasks> tarefas = repositorio.findAll();
        return tarefas.stream().map(this::toDTO).collect(Collectors.toList());
    }


    public TaskDTO buscarTarefaPorId(Long id) {
        Tasks tarefa = repositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada."));
        return toDTO(tarefa);
    }

    // Metodo para atualizar uma tarefa existente
    public TaskDTO atualizarTarefa(Long id, TaskDTO taskDTO) {
        Tasks tarefaExistente = repositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada."));

        tarefaExistente.setTitulo(taskDTO.getTitulo());
        tarefaExistente.setDescricao(taskDTO.getDescricao());
        tarefaExistente.setStatus(taskDTO.getStatus());
        tarefaExistente.setPrioridade(taskDTO.getPrioridade());

        Tasks tarefaAtualizada = repositorio.save(tarefaExistente);
        return toDTO(tarefaAtualizada);
    }

    // Metodo para deletar uma tarefa
    public void deletarTarefa(Long id) {
        Tasks tarefa = repositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada."));
        repositorio.delete(tarefa);
    }

    // Metodo para converter Tasks para TaskDTO
    private TaskDTO toDTO(Tasks task) {
        return new TaskDTO(
                task.getId(),
                task.getTitulo(),
                task.getDescricao(),
                task.getDataCriacao(),
                task.getStatus(),
                task.getPrioridade()
        );
    }

}



