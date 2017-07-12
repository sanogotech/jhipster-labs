package com.mygglo.jhipster.subscription.repository;

import com.mygglo.jhipster.subscription.domain.MySubscription;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MySubscription entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MySubscriptionRepository extends JpaRepository<MySubscription,Long> {
    
}
