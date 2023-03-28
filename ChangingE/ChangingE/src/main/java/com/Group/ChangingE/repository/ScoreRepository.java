package com.Group.ChangingE.repository;

import com.Group.ChangingE.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    @Query("SELECT s FROM Score s WHERE s.school = ?1 and s.criteria = ?2")
    Score findByCriteriaAndSchool(Long schoolId, Long criteriaId);
}
