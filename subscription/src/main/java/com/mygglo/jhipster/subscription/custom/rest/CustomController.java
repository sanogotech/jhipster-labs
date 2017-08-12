package com.mygglo.jhipster.subscription.custom.rest;

import com.codahale.metrics.annotation.Timed;
import com.mygglo.jhipster.subscription.domain.MySubscription;
import com.mygglo.jhipster.subscription.service.MySubscriptionService;
import com.mygglo.jhipster.subscription.web.rest.MySubscriptionResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jgaglo on 12/08/2017.
 */
@RestController
@RequestMapping("/api")
public class CustomController {

    private final Logger log = LoggerFactory.getLogger(CustomController.class);

    @Autowired
    MySubscriptionService mySubscriptionService;

    @GetMapping("/my-subscriptions/person/{personId}")
    @Timed
    public List<MySubscription> getAllMySubscriptions(@PathVariable Long personId) {
        log.debug("REST request to get Person all MySubscriptions");
        return mySubscriptionService.findByPersonId(personId);
    }
}
