package com.To_Do_List.demo.Repository;

import com.To_Do_List.demo.Model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface Repositorio extends JpaRepository<Tasks, Long> {
    List<Tasks> findByStatus(Tasks.Status status);
    List<Tasks> findByPrioridade(Tasks.Prioridade prioridade);
    List<Tasks> findByDataCriacaoBetween(LocalDateTime start, LocalDateTime end);
}// esta interface serve para que possamos fazer as buscas que sao possiveis
