package com.dalim.sprint.domain.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dalim.sprint.domain.Sprint;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class SprintRepositoryTest {

    @Autowired
    private SprintRepository sprintRepository;

    @BeforeEach
    void setUp() {
        final LocalDateTime createdAt = LocalDateTime.parse("2024-01-01T12:00:00");
        final Sprint sprint = new Sprint(createdAt, createdAt.toLocalDate());
        sprintRepository.save(sprint);
    }

    @Nested
    class findByDate_테스트 {

        @Test
        void 해당_날짜의_스프린트를_조회한다() {
            // given
            // when
            final Optional<Sprint> actual = sprintRepository.findByDate(LocalDate.parse("2024-01-01"));

            // then
            assertTrue(actual.isPresent());
        }

        @Test
        void 해당_날짜의_스프린트가_존재하지_않으면_값을_반환하지_않는다() {
            // given
            // when
            final Optional<Sprint> actual = sprintRepository.findByDate(LocalDate.parse("2024-01-02"));

            // then
            assertFalse(actual.isPresent());
        }
    }
}
