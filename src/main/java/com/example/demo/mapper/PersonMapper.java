package com.example.demo.mapper;

import com.example.demo.domain.Person;
import com.example.demo.dto.PersonDto;
import org.mapstruct.Mapper;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring",unmappedTargetPolicy = IGNORE)
public interface PersonMapper {

    PersonDto convertToDto(Person person);
}
