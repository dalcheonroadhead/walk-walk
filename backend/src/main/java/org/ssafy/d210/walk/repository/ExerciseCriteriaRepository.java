package org.ssafy.d210.walk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.d210.walk.entity.ExerciseCriteria;

@Repository
public interface ExerciseCriteriaRepository extends JpaRepository<ExerciseCriteria, Long> {
}
