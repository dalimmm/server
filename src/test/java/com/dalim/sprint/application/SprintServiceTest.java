package com.dalim.sprint.application;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.dalim.sprint.domain.Sprint;
import com.dalim.sprint.domain.repository.SprintRepository;
import java.time.LocalDateTime;
import java.util.Optional;
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
class SprintServiceTest {

    @Mock
    private SprintRepository sprintRepository;

    @InjectMocks
    private SprintService sprintService;

    @Nested
    class getOrCreateSprint_테스트 {

        @Test
        void 해당_날짜의_스프린트가_존재하면_해당_스프린트를_반환한다() {
            // given
            final LocalDateTime due = LocalDateTime.parse("2024-01-01T12:00:00");
            given(sprintRepository.findByDate(any()))
                    .willReturn(Optional.of(new Sprint(due, due.toLocalDate())));

            // when
            final Sprint sprint = sprintService.getOrCreateSprint(due);

            // then
            assertSoftly(softly -> {
                assertEquals(due, sprint.getCreatedAt());
                assertEquals(due.toLocalDate(), sprint.getDate());
            });
        }

        @Test
        void 해당_날짜의_스프린트가_존재하지_않으면_새로운_스프린트를_생성한다() {
            // given
            final LocalDateTime due = LocalDateTime.parse("2024-01-01T12:00:00");
            given(sprintRepository.findByDate(any()))
                    .willReturn(Optional.empty());
            given(sprintRepository.save(any()))
                    .willReturn(new Sprint(due, due.toLocalDate()));

            // when
            final Sprint sprint = sprintService.getOrCreateSprint(due);

            // then
            assertSoftly(softly -> {
                assertEquals(due, sprint.getCreatedAt());
                assertEquals(due.toLocalDate(), sprint.getDate());
            });
        }
    }
}
