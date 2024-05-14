package com.dalim.todo.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.dalim.sprint.application.SprintService;
import com.dalim.sprint.domain.Sprint;
import com.dalim.todo.domain.Todo;
import com.dalim.todo.domain.repository.TodoRepository;
import com.dalim.todo.ui.dto.TodoRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private SprintService sprintService;

    @InjectMocks
    private TodoService todoService;

    @Nested
    class create_테스트 {

        @Test
        void todo를_생성한다() {
            // given
            final TodoRequest request = new TodoRequest("content", 1704078000L, false);
            final Sprint sprint = new Sprint(LocalDateTime.parse("2024-01-01T12:00:00"), LocalDate.parse("2024-01-01"));
            given(sprintService.getOrCreateSprint(any()))
                    .willReturn(sprint);
            final Todo todo = new Todo(sprint, "content", false);
            given(todoRepository.save(any()))
                    .willReturn(todo);

            // when
            todoService.create(request);

            // then
            verify(todoRepository).save(any());
        }
    }
}
