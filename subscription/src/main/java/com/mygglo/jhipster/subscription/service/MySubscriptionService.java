package com.mygglo.jhipster.subscription.service;

import com.mygglo.jhipster.subscription.domain.MySubscription;
import java.util.List;

/**
 * Service Interface for managing MySubscription.
 */
public interface MySubscriptionService {

    /**
     * Save a mySubscription.
     *
     * @param mySubscription the entity to save
     * @return the persisted entity
     */
    MySubscription save(MySubscription mySubscription);

    /**
     *  Get all the mySubscriptions.
     *
     *  @return the list of entities
     */
    List<MySubscription> findAll();

    /**
     *  Get the "id" mySubscription.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MySubscription findOne(Long id);

    /**
     *  Delete the "id" mySubscription.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    List<MySubscription> findByPersonId(Long personId);
}
