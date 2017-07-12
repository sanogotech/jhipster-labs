package com.mygglo.jhipster.subscription.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mygglo.jhipster.subscription.domain.MySubscription;
import com.mygglo.jhipster.subscription.service.MySubscriptionService;
import com.mygglo.jhipster.subscription.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MySubscription.
 */
@RestController
@RequestMapping("/api")
public class MySubscriptionResource {

    private final Logger log = LoggerFactory.getLogger(MySubscriptionResource.class);

    private static final String ENTITY_NAME = "mySubscription";

    private final MySubscriptionService mySubscriptionService;

    public MySubscriptionResource(MySubscriptionService mySubscriptionService) {
        this.mySubscriptionService = mySubscriptionService;
    }

    /**
     * POST  /my-subscriptions : Create a new mySubscription.
     *
     * @param mySubscription the mySubscription to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mySubscription, or with status 400 (Bad Request) if the mySubscription has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/my-subscriptions")
    @Timed
    public ResponseEntity<MySubscription> createMySubscription(@RequestBody MySubscription mySubscription) throws URISyntaxException {
        log.debug("REST request to save MySubscription : {}", mySubscription);
        if (mySubscription.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new mySubscription cannot already have an ID")).body(null);
        }
        MySubscription result = mySubscriptionService.save(mySubscription);
        return ResponseEntity.created(new URI("/api/my-subscriptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /my-subscriptions : Updates an existing mySubscription.
     *
     * @param mySubscription the mySubscription to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mySubscription,
     * or with status 400 (Bad Request) if the mySubscription is not valid,
     * or with status 500 (Internal Server Error) if the mySubscription couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/my-subscriptions")
    @Timed
    public ResponseEntity<MySubscription> updateMySubscription(@RequestBody MySubscription mySubscription) throws URISyntaxException {
        log.debug("REST request to update MySubscription : {}", mySubscription);
        if (mySubscription.getId() == null) {
            return createMySubscription(mySubscription);
        }
        MySubscription result = mySubscriptionService.save(mySubscription);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mySubscription.getId().toString()))
            .body(result);
    }

    /**
     * GET  /my-subscriptions : get all the mySubscriptions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of mySubscriptions in body
     */
    @GetMapping("/my-subscriptions")
    @Timed
    public List<MySubscription> getAllMySubscriptions() {
        log.debug("REST request to get all MySubscriptions");
        return mySubscriptionService.findAll();
    }

    /**
     * GET  /my-subscriptions/:id : get the "id" mySubscription.
     *
     * @param id the id of the mySubscription to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mySubscription, or with status 404 (Not Found)
     */
    @GetMapping("/my-subscriptions/{id}")
    @Timed
    public ResponseEntity<MySubscription> getMySubscription(@PathVariable Long id) {
        log.debug("REST request to get MySubscription : {}", id);
        MySubscription mySubscription = mySubscriptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(mySubscription));
    }

    /**
     * DELETE  /my-subscriptions/:id : delete the "id" mySubscription.
     *
     * @param id the id of the mySubscription to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/my-subscriptions/{id}")
    @Timed
    public ResponseEntity<Void> deleteMySubscription(@PathVariable Long id) {
        log.debug("REST request to delete MySubscription : {}", id);
        mySubscriptionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
