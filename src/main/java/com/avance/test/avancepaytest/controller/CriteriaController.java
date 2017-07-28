package com.avance.test.avancepaytest.controller;

import com.avance.test.avancepaytest.domain.Criteria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by a.kuci on 7/29/2017.
 */
@RestController
@RequestMapping(value = "/criterias")
public class CriteriaController {

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Criteria> getAllCriterias() {
        return Arrays.asList(Criteria.values());
    }


}
