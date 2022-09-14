package com.spring.mydiv.Service;

import javax.transaction.Transactional;

import com.spring.mydiv.Dto.PersonCreateDto;
import com.spring.mydiv.Entity.Travel;
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
    public PersonDto createPerson(PersonCreateDto.Request request) {
        Person person = Person.builder()
        		.user(User.builder()
                        .name(request.getUser().getName())
                        .email(request.getUser().getEmail())
                        .password(request.getUser().getPassword())
                        .account(request.getUser().getAccount())
                        .build())
        		.travel(Travel.builder()
                        .name(request.getTravel().getName())
                        .build())
                .build();
        personRepository.save(person);
        return PersonDto.fromEntity(person);
    }

}
