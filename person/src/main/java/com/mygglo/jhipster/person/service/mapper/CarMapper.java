package com.mygglo.jhipster.person.service.mapper;

import com.mygglo.jhipster.person.domain.*;
import com.mygglo.jhipster.person.service.dto.CarDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Car and its DTO CarDTO.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class, })
public interface CarMapper extends EntityMapper <CarDTO, Car> {

    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.name", target = "personName")
    CarDTO toDto(Car car); 

    @Mapping(source = "personId", target = "person")
    Car toEntity(CarDTO carDTO); 
    default Car fromId(Long id) {
        if (id == null) {
            return null;
        }
        Car car = new Car();
        car.setId(id);
        return car;
    }
}
