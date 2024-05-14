package com.dalim.todo.ui;

import com.dalim.todo.application.TodoService;
import com.dalim.todo.ui.dto.TodoRequest;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<Void> createTodo(@RequestBody final TodoRequest request) {
        final Long id = todoService.create(request);
        return ResponseEntity.created(URI.create("/todos/" + id)).build();
    }
}
