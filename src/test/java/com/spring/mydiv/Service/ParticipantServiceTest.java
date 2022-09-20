package com.spring.mydiv.Service;

import com.spring.mydiv.Dto.ParticipantCreateDto;
import com.spring.mydiv.Dto.ParticipantDto;
import com.spring.mydiv.Entity.Event;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Commit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ParticipantServiceTest {

    @Autowired(required = true)
    private ParticipantService participantService;
    @Autowired(required = true)
    private PersonService personService;
    @Autowired(required = true)
    private EventService eventService;

    @Test
    @Commit
    @DisplayName("참가자 생성")
    void createParticipant() {
        //given
        Long person_id = Long.valueOf(50); //이하은
        Long event_id = Long.valueOf(2); //대치동
        ParticipantCreateDto.Request partiRequest = ParticipantCreateDto.Request.builder()
                .person(personService.getPersonEntityByPersonId(person_id).get())
                .event(eventService.getEventEntityByEventId(event_id).get())
                .role(false)
                .build();

        //when
        ParticipantDto dto = participantService.createParticipant(partiRequest);

        //then
        System.out.println("status: " + ResponseEntity.ok(dto).toString());
        System.out.println("role: " + dto.getEventRole());
    }

//    @Test
//    @Commit
//    @DisplayName("참가자 생성")
//    void createParticipant() {
//        //given
//
//        //when
//
//        //then
//    }
}