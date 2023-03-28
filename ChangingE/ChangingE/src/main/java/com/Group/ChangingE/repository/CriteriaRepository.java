package com.Group.ChangingE.repository;

import com.Group.ChangingE.entity.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriteriaRepository extends JpaRepository<Criteria, Long> {

//    @Query("SELECT c FROM criteria c WHERE c.parent = ?1")
    List<Criteria> findByParent(Long parent);
//    List<Criteria> searchCriteriaByParentContains(Long id);
    List<Criteria> findByLevel(int level);

    @Query("SELECT c FROM Criteria c WHERE c.level = :level GROUP BY c.parent ORDER BY c.parent")
    List<Criteria> findCriteriaByLevelGroupByParentOrderByParent(@Param("level") int level);

    List<Criteria> findCriteriaByLevel(int level);

}
