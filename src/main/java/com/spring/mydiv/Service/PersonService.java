package com.spring.mydiv.Service;

import javax.transaction.Transactional;

import com.spring.mydiv.Dto.ParticipantCreateDto;
import com.spring.mydiv.Dto.PersonCreateDto;
import com.spring.mydiv.Dto.TravelCreateDto;
import com.spring.mydiv.Entity.Travel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.mydiv.Dto.PersonDto;
import com.spring.mydiv.Entity.Person;
import com.spring.mydiv.Entity.User;
import com.spring.mydiv.Repository.PersonRepository;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author 12nov
 */
@Service
@RequiredArgsConstructor
public class PersonService {
	private final PersonRepository personRepository;
	
    @Transactional
    public PersonDto createPerson(PersonCreateDto.Request request, boolean superUser) {
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
                .sumGet(0.0)
                .sumSend(0.0)
                .difference(0.0)
                .role(superUser)
                .isSuper(superUser)
                .build();
        personRepository.save(person);
        return PersonDto.fromEntity(person);
    }

    @Transactional
    public void deleteJoinTravel(int personId) {
        personRepository.deleteById(Long.valueOf(personId));
    }

    public List<PersonCreateDto.Simple> getPersonNameInTravel(int travelId){
        Optional<List<Person>> list = personRepository.findByTravel_Id(Long.valueOf(travelId));
        List<PersonCreateDto.Simple> result = new ArrayList<PersonCreateDto.Simple>();
        for (Person p : list.get()){
            PersonCreateDto.Simple person = PersonCreateDto.Simple.fromEntity(p);
            result.add(person);
        }
        return result;
    }

    public boolean checkisUserinTravel(Long userId, int travelId){
        return personRepository.existsByUser_IdAndTravel_Id(userId, Long.valueOf(travelId));
    }

    public Optional<Person> getPersonEntityByPersonId(Long id){
        return personRepository.findById(id);
    }

    public List<PersonCreateDto.HomeView> getPersonInfoInTravel(int travelId){
        Optional<List<Person>> list = personRepository.findByTravel_Id(Long.valueOf(travelId));
        List<PersonCreateDto.HomeView> result = new ArrayList<PersonCreateDto.HomeView>();
        list.ifPresent(
                list_ ->
                {for (Person p : list_){
                    PersonCreateDto.HomeView person = PersonCreateDto.HomeView.fromEntity(p);
                    result.add(person);
                }}
        );
        return result;
    }

    public int getPersonCountInTravel(int travelId){
        return personRepository.countDistinctByTravel_Id(Long.valueOf(travelId));
    }

    public PersonCreateDto.Detail getPersonToDetailView(int personId){
        //- 사용자 개인 정보 -> user(name, email, account)
        //- travel에서의 정보 -> person(sumsend, sumget, diff, travelrole)
        Optional<Person> info = personRepository.findById(Long.valueOf(personId));
        return PersonCreateDto.Detail.fromEntity(info.get());
    }

    public PersonCreateDto.HomeView getPayerInTravel(int travelId){
        return PersonCreateDto.HomeView.fromEntity(personRepository
                .findByTravel_IdAndRole(Long.valueOf(travelId), true));
    }

    public void updatePersonMoneyByCreating(List<Person> personList, Long payer_personId,
                                       Double dividePrice, Double takePrice){
        for (Person p : personList){
            if (p.getId().equals(payer_personId)){ // payer
                p.setSumGet(p.getSumGet() + takePrice);
                p.setDifference(p.getDifference() + takePrice);
                personRepository.updateSumGetAndDifferenceById(p.getSumGet(), p.getDifference(), p.getId());
            } else { // ~payer
                p.setSumSend(p.getSumSend() + dividePrice);
                p.setDifference(p.getDifference() - dividePrice);
                personRepository.updateSumSendAndDifferenceById(p.getSumSend(), p.getDifference(), p.getId());
            }
        }
    }

    public void updatePersonMoneyByDeleting(List<Person> personList, Long payer_personId,
                                            Double dividePrice, Double takePrice){
        for (Person p : personList){
            if (p.getId().equals(payer_personId)){ // payer
                p.setSumGet(p.getSumGet() - takePrice);
                p.setDifference(p.getDifference() - takePrice);
                personRepository.updateSumGetAndDifferenceById(p.getSumGet(), p.getDifference(), p.getId());
            } else { // ~payer
                p.setSumSend(p.getSumSend() - dividePrice);
                p.setDifference(p.getDifference() + dividePrice);
                personRepository.updateSumSendAndDifferenceById(p.getSumSend(), p.getDifference(), p.getId());
            }
        }
    }


    public void updatePersonRole(int travelId){
        Optional<List<Person>> People = personRepository.findByTravel_Id(Long.valueOf(travelId));
        Person currManager = personRepository.findByTravel_IdAndRole(Long.valueOf(travelId), true);
        personRepository.updateRoleById(FALSE, currManager.getId());
        Double maxDifference = currManager.getDifference();

        for(Person p : People.get()) {
            Double currDifference = p.getDifference();
            if (currDifference > maxDifference) {
                maxDifference = currDifference;
                currManager = p;
            }
        }
        personRepository.updateRoleById(TRUE, currManager.getId());
    }
}
