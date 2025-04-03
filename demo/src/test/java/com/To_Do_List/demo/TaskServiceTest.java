package com.To_Do_List.demo;

import com.To_Do_List.demo.Model.Tasks;
import com.To_Do_List.demo.Repository.Repositorio;
import com.To_Do_List.demo.Service.TaskService;
import com.To_Do_List.demo.dto.TaskDTO;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private Repositorio taskRepository;

    @InjectMocks
    private TaskService taskService;

    // Objetos de teste
    private Tasks task;
    private TaskDTO taskDTO;

    @BeforeEach
    void setUp() {
        task = new Tasks();
        task.setId(1L);
        task.setTitulo("Teste");
        task.setDescricao("Descrição teste");
        task.setStatus(Tasks.Status.PENDENTE);
        task.setPrioridade(Tasks.Prioridade.MEDIA);

        taskDTO = new TaskDTO();
        taskDTO.setTitulo("Teste");
        taskDTO.setDescricao("Descrição teste");
        taskDTO.setPrioridade(Tasks.Prioridade.MEDIA);
    }
    @Test
    void criarTarefa_ComDadosValidos_RetornaTaskDTO() {
        // Configura o mock
        when(taskRepository.save(any(Tasks.class))).thenReturn(task);

        // Executa o método
        TaskDTO result = taskService.criarTarefa(taskDTO);

        // Verificações
        assertNotNull(result);
        assertEquals("Teste", result.getTitulo());
        verify(taskRepository, times(1)).save(any(Tasks.class));
    }

    @Test
    void buscarTarefaPorId_ComIdInexistente_LancaExcecao() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            taskService.buscarTarefaPorId(1L);
        });
    }
}



