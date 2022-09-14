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
        int userNo = 6; //2번만 없다 ㅋㅋㅋ
        TravelCreateDto.Request travelInfo = TravelCreateDto.Request.builder()
                .Name("test!")
                .build();

        PersonCreateDto.Request request = PersonCreateDto.Request.builder()
                .User(userService.getUserInfo(userNo))
                .Travel(travelService.createTravel(travelInfo))
                .build();
//        System.out.println("here!"); //여기는 됐음
        //when
        PersonDto person = personService.createPerson(request);
        //then
        System.out.println("User name = " + person.getUser().getName());
        System.out.println("Travel name = " + person.getTravel().getName());
    }


}