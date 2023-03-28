package com.Group.ChangingE.api;

import com.Group.ChangingE.entity.Criteria;
import com.Group.ChangingE.entity.School;
import com.Group.ChangingE.service.CriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/rate")
public class CriteriaAPI {
    @Autowired
    CriteriaService service = new CriteriaService();
    @PostMapping("/")
    public String rating(@RequestBody List<Integer> list, @PathVariable School school){
        service.saveAllCriteria(list,school);
        return service.getResult(school);
    }
}
