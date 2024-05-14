package com.dalim.sprint.domain.repository;

import com.dalim.sprint.domain.Sprint;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<Sprint, Long> {

    Optional<Sprint> findByDate(final LocalDate date);
}
