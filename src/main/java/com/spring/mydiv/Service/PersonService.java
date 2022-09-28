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

    @Transactional
    public void updatePersonWithEvent(List<Person> personList,
                                      Long payer_person_id,
                                      Double dividePrice,
                                      Double takePrice){
        int TAKER_index = -1; // ?
        Double tmpDifference = Double.MIN_VALUE;
        int index = 0;

        for (Person p : personList){
            // update sumget & sumsend
            if (p.getId().equals(payer_person_id)){ // payer
                p.setSumGet(p.getSumGet() + takePrice);
            } else { // ~payer
                p.setSumSend(p.getSumSend() + dividePrice);
            }

            // update difference
            if (p.getSumGet() == null) p.setDifference(-p.getSumSend());
            else if (p.getSumSend() == null) p.setDifference(p.getSumGet());
            else p.setDifference(p.getSumGet() - p.getSumSend());

            if (p.getDifference() > tmpDifference) {
                TAKER_index = index;
                tmpDifference = p.getDifference();
            }

            // set default Role
            p.setRole(false);

            index++;
        }
        // update TAKER role
        personList.get(TAKER_index).setRole(true); // ERROR 발생

        for (Person p : personList)
            personRepository.updateSumSendAndSumGetAndDifferenceAndRoleById(p.getSumSend(),
                    p.getSumGet(), p.getDifference(), p.getRole(),
                    p.getId());
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
