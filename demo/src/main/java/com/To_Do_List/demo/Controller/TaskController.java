package com.To_Do_List.demo.Controller;

import com.To_Do_List.demo.Service.TaskService;
import com.To_Do_List.demo.dto.TaskDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // criando nova tarefa
    @PostMapping
    public ResponseEntity<TaskDTO> criarTarefa(@Valid @RequestBody TaskDTO taskDTO){
        TaskDTO novaTarefa = taskService.criarTarefa(taskDTO);
        return ResponseEntity.status(201).body(novaTarefa);
    }
    // Listar todas as tarefas
    @GetMapping
    public ResponseEntity<List<TaskDTO>> listarTarefas() {
        List<TaskDTO> tarefas = taskService.listarTarefas();
        return ResponseEntity.ok(tarefas);
    }
    // Buscar tarefa por ID
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> buscarTarefa(@PathVariable Long id) {
        TaskDTO tarefa = taskService.buscarTarefaPorId(id);
        return ResponseEntity.ok(tarefa);
    }
    // Atualizar uma tarefa
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> atualizarTarefa(@PathVariable Long id, @Valid @RequestBody TaskDTO taskDTO) {
        TaskDTO tarefaAtualizada = taskService.atualizarTarefa(id, taskDTO);
        return ResponseEntity.ok(tarefaAtualizada);
    }
    // Deletar uma tarefa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        taskService.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }

}
