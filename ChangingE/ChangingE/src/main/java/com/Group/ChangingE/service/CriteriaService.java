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
    public List<Criteria> getAllDetail(){
        List<Criteria> detailList= criteriaRepository.searchCriteriaByLevelContains(getMinLevel());
        return detailList;
    }

    public void saveAllCriteria(List<Integer> scoreList, School school){
        List<Criteria> detailList = criteriaRepository.searchCriteriaByLevelContains(getMinLevel());
        if(scoreList.size()==detailList.size()){
            for(int i = 0; i<= scoreList.size();i++){
                Score newScore = new Score();
                newScore.setScore(scoreList.get(i));
                newScore.setCriteria(detailList.get(i));
                newScore.setSchool(school);
                scoreRepository.save(newScore);

            }
        }

    }

    //ham return list criteria goc ( la cha, ong noi, ong co noi ,.. cua cac criteria khac )
    public List<Criteria> getAllRoot(){
        List<Criteria> allRoot = criteriaRepository.searchCriteriaByParentContains(0L);
        return allRoot;
    }
    //ham de quy tinh diem cua 1 criteria va 1 result
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
    //ham sep loai cho 1 school
    public String getResult(School school){
        String result = "chưa hoàn thành";
        List<Criteria> rootCriteriaList= getAllRoot();
        for(Criteria rootCriteria:rootCriteriaList){
            if(getScore(rootCriteria, school)<50){
                return result;
            }
            if(getScore(rootCriteria, school)<75){
                return "hoàn thành";
            }
            else result="hoàn thành tốt";

        }
        return result;
    }
}
