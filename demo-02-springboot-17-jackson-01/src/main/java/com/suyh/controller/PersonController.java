package com.suyh.controller;

import com.suyh.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class PersonController {
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @RequestMapping("/v1/person")
    public Person getPerson(@RequestBody(required = false) Person person) {
        Person resPerson = new Person("id", "name", "sex");
        if (person != null) {
            resPerson = person;
        }
        logger.info("person: " + person);
        logger.info("resPerson: " + resPerson);
        resPerson.setCreatedDate(new Date());
        resPerson.setId(null);
        return resPerson;
    }
}
