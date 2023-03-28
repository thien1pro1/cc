package com.Group.ChangingE.service;

import com.Group.ChangingE.entity.Criteria;
import com.Group.ChangingE.entity.School;
import com.Group.ChangingE.entity.Score;
import com.Group.ChangingE.repository.CriteriaRepository;
import com.Group.ChangingE.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriteriaService {
    @Autowired
    CriteriaRepository criteriaRepository;

    @Autowired
    ScoreRepository scoreRepository;

    public int getMinLevel(){
        int level = -6;
        List<Criteria> list = criteriaRepository.findAll();
        for(Criteria criteria:list){
            if(criteria.getLevel()>level){
                level=criteria.getLevel();
            }
        }
        return level;
    }

    public List<Criteria> findAllRootCriteria(Long id){
        List<Criteria> allRoot = criteriaRepository.searchCriteriaByParentContains(id);
        return allRoot;
    }
    public int getScore(Criteria criteria, School school){
        if(criteria.getParent()==getMinLevel()){
            Score sc = scoreRepository.findByCriteriaAndSchool(school.getId(), criteria.getId());
            return sc.getScore();
        }
        else{
            List<Criteria> criteriaList = criteriaRepository.findCriteriaByParent(criteria.getId());
            int score = 0;
            for(Criteria criteriaChild:criteriaList){
                if(getScore(criteriaChild,school)+score<criteria.getMaxScore()){
                    score += getScore(criteriaChild, school);
                }
                else {
                    score=criteria.getMaxScore();
                    continue;
                }
            }
            return  score;
        }
    }
}
