package com.dalim.sprint.application;

import com.dalim.sprint.domain.Sprint;
import com.dalim.sprint.domain.repository.SprintRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SprintService {

    private final SprintRepository sprintRepository;

    @Transactional
    public Sprint getOrCreateSprint(final LocalDateTime due) {
        return sprintRepository.findByDate(due.toLocalDate())
            .orElseGet(() -> sprintRepository.save(new Sprint(due, due.toLocalDate())));
    }
}
