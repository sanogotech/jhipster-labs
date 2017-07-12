package com.mygglo.jhipster.person.service.mapper;

import com.mygglo.jhipster.person.domain.*;
import com.mygglo.jhipster.person.service.dto.PersonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Person and its DTO PersonDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PersonMapper extends EntityMapper <PersonDTO, Person> {
    
    @Mapping(target = "cars", ignore = true)
    Person toEntity(PersonDTO personDTO); 
    default Person fromId(Long id) {
        if (id == null) {
            return null;
        }
        Person person = new Person();
        person.setId(id);
        return person;
    }
}
