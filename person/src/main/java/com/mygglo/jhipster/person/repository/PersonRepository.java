package com.mygglo.jhipster.person.repository;

import com.mygglo.jhipster.person.domain.Person;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Person entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    
}
