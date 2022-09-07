package com.spring.mydiv.Service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.spring.mydiv.Dto.PersonDto;
import com.spring.mydiv.Dto.UserCreateDto;
import com.spring.mydiv.Entity.Person;
import com.spring.mydiv.Entity.User;
import com.spring.mydiv.Repository.PersonRepository;
import com.spring.mydiv.Repository.TravelRepository;
import com.spring.mydiv.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author 12nov
 */
@Service
@RequiredArgsConstructor
public class PersonService {
	private final PersonRepository personRepository;
	
    @Transactional
    public PersonDto createPerson(PersonDto request) {
        Person person = Person.builder()
        		//.Id(userdto.getId())
        		.user(request.getUser())
        		.travel(request.getTravel())
        		.sumSend(request.getSumSend())
        		.sumGet(request.getSumGet())
        		.difference(request.getDifference())
        		.role(request.getRole())
                .build();
        personRepository.save(person);
        return PersonDto.fromEntity(person);
    }

}
