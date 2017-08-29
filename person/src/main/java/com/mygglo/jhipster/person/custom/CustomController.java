package com.mygglo.jhipster.person.custom;

import com.mygglo.jhipster.person.domain.Person;
import com.mygglo.jhipster.person.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * Created by jgaglo on 29/08/2017.
 */
@RestController
@RequestMapping("/api")
public class CustomController {
    private static Logger log = LoggerFactory.getLogger(CustomController.class);

    @Autowired
    PersonService personService;

    @RequestMapping(method = RequestMethod.POST, value = "/deleteperson")
    public ResponseEntity deletePerson(@RequestBody Person[] persons) {
        log.info(" Batch delete " + persons);
        personService.delete(Arrays.asList(persons));
        return ResponseEntity.ok().build();
    }

}
