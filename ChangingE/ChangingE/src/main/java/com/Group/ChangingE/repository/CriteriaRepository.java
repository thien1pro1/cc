package com.Group.ChangingE.repository;

import com.Group.ChangingE.entity.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriteriaRepository extends JpaRepository<Criteria, Long> {

//    @Query("SELECT c FROM criteria c WHERE c.parent = ?1")
    List<Criteria> findCriteriaByParent(Long parent);
    List<Criteria> searchCriteriaByParentContains(Long id);
    List<Criteria> searchCriteriaByLevelContains(int level);

}
