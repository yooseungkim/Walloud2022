package com.spring.mydiv.Service;

import com.spring.mydiv.Dto.PersonCreateDto;
import com.spring.mydiv.Dto.PersonDto;
import com.spring.mydiv.Dto.TravelCreateDto;
import com.spring.mydiv.Entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
class PersonServiceTest {
    @Autowired(required=true)
    private PersonService personService;
    @Autowired(required=true)
    private UserService userService;
    @Autowired(required=true)
    private TravelService travelService;

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
        int userId = 13;
        int travelId = 58;

        //when
        personService.deleteJoinTravel(userId, travelId);

        //then
        System.out.println("check DB please!");
    }

//    @Test
//    @Commit
//    @DisplayName("여행")


}