package com.mygglo.jhipster.subscription.service.impl;

import com.mygglo.jhipster.subscription.service.MySubscriptionService;
import com.mygglo.jhipster.subscription.domain.MySubscription;
import com.mygglo.jhipster.subscription.repository.MySubscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing MySubscription.
 */
@Service
@Transactional
public class MySubscriptionServiceImpl implements MySubscriptionService{

    private final Logger log = LoggerFactory.getLogger(MySubscriptionServiceImpl.class);

    private final MySubscriptionRepository mySubscriptionRepository;

    public MySubscriptionServiceImpl(MySubscriptionRepository mySubscriptionRepository) {
        this.mySubscriptionRepository = mySubscriptionRepository;
    }

    /**
     * Save a mySubscription.
     *
     * @param mySubscription the entity to save
     * @return the persisted entity
     */
    @Override
    public MySubscription save(MySubscription mySubscription) {
        log.debug("Request to save MySubscription : {}", mySubscription);
        return mySubscriptionRepository.save(mySubscription);
    }

    /**
     *  Get all the mySubscriptions.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MySubscription> findAll() {
        log.debug("Request to get all MySubscriptions");
        return mySubscriptionRepository.findAll();
    }

    /**
     *  Get one mySubscription by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MySubscription findOne(Long id) {
        log.debug("Request to get MySubscription : {}", id);
        return mySubscriptionRepository.findOne(id);
    }

    /**
     *  Delete the  mySubscription by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MySubscription : {}", id);
        mySubscriptionRepository.delete(id);
    }
}
