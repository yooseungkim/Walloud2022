package com.spring.mydiv.Service;

import com.spring.mydiv.Dto.PersonCreateDto;
import com.spring.mydiv.Dto.PersonDto;
import com.spring.mydiv.Dto.TravelCreateDto;
import com.spring.mydiv.Entity.Event;
import com.spring.mydiv.Entity.Person;
import com.spring.mydiv.Entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PersonServiceTest {
    @Autowired(required=true)
    private PersonService personService;
    @Autowired(required=true)
    private UserService userService;
    @Autowired(required=true)
    private TravelService travelService;
    @Autowired(required=true)
    private EventService eventService;

    @Test
    @Commit
    @DisplayName("여행 생성")
    void createPerson() {
        //given
        int userNo = 13;
        TravelCreateDto.Request travelInfo = TravelCreateDto.Request.builder()
                .Name("광주 여행")
                .build();
        PersonCreateDto.Request request = PersonCreateDto.Request.builder()
                .User(userService.getUserInfo(userNo))
                .Travel(travelService.createTravel(travelInfo))
                .build();

        //when
        PersonDto person = personService.createPerson(request);

        //then
        System.out.println("User name = " + person.getUser().getName());
        System.out.println("Travel name = " + person.getTravel().getName());
    }

    @Test
    @Commit
    @DisplayName("여행 참가자 리스트 리턴")
    void getPersonNameInTravel(){
        //given
        int travelId = 57; //서울 여행

        //when
        List<PersonCreateDto.Simple> list = personService.getPersonNameInTravel(travelId);

        //then
        for (PersonCreateDto.Simple p : list){
            System.out.println("Name: " + p.getName());
        }

    }

    @Test
    @Commit
    @DisplayName("여행 삭제")
    void deleteJoinTravel() {
        //given
        int userId = 16;
        int travelId = 67;

        //when
        personService.deleteJoinTravel(userId, travelId);

        //then
        System.out.println("check DB please!");
    }

    @Test
    @Commit
    @DisplayName("사람 뽑아오기")
    void getPersonEntityByPersonId() {
        //given
        Long person_id = Long.valueOf(50); //이하은
        //when
        Person person = personService.getPersonEntityByPersonId(person_id)
                .get();
        //then
        System.out.println("id: " + person.getId());
        System.out.println("id: " + person.getSumSend());
        System.out.println("id: " + person.getSumGet());
        System.out.println("id: " + person.getDifference());
        System.out.println("id: " + person.getRole());
    }

    @Test
    @Commit
    @DisplayName("이벤트로 person 정보 업데이트")
    void updatePersonWithEvent() {
        //given
        Event event = eventService.getEventEntityByEventId(Long.valueOf(2)).get(); //대치동
        List<Person> personList = new ArrayList<>();
        Person person1 = personService.getPersonEntityByPersonId(Long.valueOf(50)).get();
        personList.add(person1);
        Person person2 = personService.getPersonEntityByPersonId(Long.valueOf(52)).get();
        personList.add(person2);
        Long payer_person_id = Long.valueOf(52);
        Double dividePrice = event.getDividePrice();
        Double takePrice = event.getTakePrice();

        //when
        personService.updatePersonWithEvent(personList, payer_person_id, dividePrice, takePrice);

        //then
        System.out.println("finish!");
    }

    @Test
    @Commit
    @DisplayName("사람 뽑아오기")
    void getPersonToDetailView() {
        //given
        int person_id = 50; //이하은
        //when
        PersonCreateDto.Detail detail = personService.getPersonToDetailView(person_id);
        //then
        System.out.println("personID: " + detail.getPersonId());
        System.out.println("personID: " + detail.getUserName());
        System.out.println("personID: " + detail.getUserEmail());
        System.out.println("personID: " + detail.getUserAccount());
        System.out.println("personID: " + detail.getSumSend());
        System.out.println("personID: " + detail.getSumGet());
        System.out.println("personID: " + detail.getDifference());
        System.out.println("personID: " + detail.getTravelRole());
    }
    @Test
    @Commit
    @DisplayName("사람 뽑아오기")
    void getPayerInTravel() {
        //given
        int travelId = 57; // 서울 여행
        //when
        PersonCreateDto.HomeView tmp = personService.getPayerInTravel(travelId);
        //then
        System.out.println("payer : " + tmp.getId());
        System.out.println("payer : " + tmp.getName());
    }

//    @Test
//    @Commit
//    @DisplayName("사람 뽑아오기")
//    void getPersonEntityByPersonId() {
//        //given
//        //when
//        //then
//    }

}