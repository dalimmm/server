package com.dalim.todo.ui.dto;

public record TodoRequest(
        String content,
        Long due,
        boolean done
) {
}
