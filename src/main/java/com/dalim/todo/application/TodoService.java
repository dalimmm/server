package com.dalim.todo.application;

import com.dalim.sprint.application.SprintService;
import com.dalim.sprint.domain.Sprint;
import com.dalim.todo.domain.Todo;
import com.dalim.todo.domain.repository.TodoRepository;
import com.dalim.todo.ui.dto.TodoRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;
    private final SprintService sprintService;

    @Transactional
    public Long create(final TodoRequest request) {
        final Instant instant = Instant.ofEpochSecond(request.due());
        final LocalDateTime due = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        final Sprint sprint = sprintService.getOrCreateSprint(due);

        final Todo todo = request.toEntity(sprint);
        return todoRepository.save(todo).getId();
    }
}
