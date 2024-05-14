package com.dalim.todo.ui.dto;

import com.dalim.sprint.domain.Sprint;
import com.dalim.todo.domain.Todo;

public record TodoRequest(
        String content,
        Long due,
        boolean done
) {

    public Todo toEntity(final Sprint sprint) {
        return new Todo(sprint, content, done);
    }
}
