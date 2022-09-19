package com.spring.mydiv.Service;

import javax.transaction.Transactional;

import com.spring.mydiv.Dto.ParticipateCreateDto;
import com.spring.mydiv.Dto.PersonCreateDto;
import com.spring.mydiv.Entity.Travel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.mydiv.Dto.PersonDto;
import com.spring.mydiv.Dto.UserCreateDto;
import com.spring.mydiv.Entity.Person;
import com.spring.mydiv.Entity.User;
import com.spring.mydiv.Repository.PersonRepository;
import com.spring.mydiv.Repository.TravelRepository;
import com.spring.mydiv.Repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

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
                        .id(request.getUser().getId())
                        .name(request.getUser().getName())
                        .email(request.getUser().getEmail())
                        .password(request.getUser().getPassword())
                        .account(request.getUser().getAccount())
                        .build())
        		.travel(Travel.builder()
                        .id(request.getTravel().getId())
                        .name(request.getTravel().getName())
                        .build())
                .build();
        personRepository.save(person);
        return PersonDto.fromEntity(person);
    }

    @Transactional
    public void deleteJoinTravel(int userId, int travelId) {
        personRepository.deleteByUser_IdAndTravel_Id(Long.valueOf(userId), Long.valueOf(travelId));
    }

    public List<PersonCreateDto.Simple> getPersonNameInTravel(int travelId){
        List<Person> list = personRepository.findByTravel_Id(Long.valueOf(travelId));
        List<PersonCreateDto.Simple> result = new ArrayList<PersonCreateDto.Simple>();
        for (Person p : list){
            PersonCreateDto.Simple person = PersonCreateDto.Simple.fromEntity(p);
            result.add(person);
        }
        return result;
    }

    public List<PersonCreateDto.HomeView> getPersonInfoInTravel(int travelId){
        List<Person> list = personRepository.findByTravel_Id(Long.valueOf(travelId));
        List<PersonCreateDto.HomeView> result = new ArrayList<PersonCreateDto.HomeView>();
        for (Person p : list){
            PersonCreateDto.HomeView person = PersonCreateDto.HomeView.fromEntity(p);
            result.add(person);
        }
        return result;
    }

    public int getPersonCountInTravel(int travelId){
        return personRepository.countDistinctByTravel_Id(Long.valueOf(travelId));
    }

    @Transactional
    public ResponseEntity<ParticipateCreateDto> updatePersonWithEvent(){
        return null;
    }

}
