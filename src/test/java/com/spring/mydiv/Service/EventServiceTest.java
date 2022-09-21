package com.spring.mydiv.Service;

import com.spring.mydiv.Dto.EventCreateDto;
import com.spring.mydiv.Dto.TravelCreateDto;
import com.spring.mydiv.Entity.Event;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Commit;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventServiceTest {

    @Autowired(required = true)
    private EventService eventService;
    @Autowired(required = true)
    private TravelService travelService;

    @Test
    @Commit
    @DisplayName("이벤트 생성")
    void createEvent() {
        //given
        Date date = new Date(22, 9, 20);
        EventCreateDto.Request request = EventCreateDto.Request.builder()
                .Name("은마아파트")
                .Travel(travelService.getTravelInfo(57)) //서울 여행
                .Date(date)
                .Price(25000)
                .PartiCount(2)
                .build();
        //when
        EventCreateDto.Response eventDto = eventService.createEvent(request);

        //then
        System.out.println("status: " + ResponseEntity.ok(eventDto).toString());
        System.out.println("event id: " + eventDto.getId());
        System.out.println("dividePrice: "+ eventDto.getDividePrice());
        System.out.println("takePrice: "+ eventDto.getTakePrice());
        /**result
         * status: <200 OK OK,com.spring.mydiv.Dto.EventCreateDto$Response@6d2693f,[]>
         * event id: 2
         * dividePrice: 12500.0
         * takePrice: 12500.0
         */
    }

    @Test
    @Commit
    @DisplayName("이벤트 생성")
    void getEventInfoInTravel() {
        //given

        //when

        //then
    }

    @Test
    @Commit
    @DisplayName("이벤트 생성")
    void getEventCountInTravel() {
        //given

        //when

        //then
    }

    @Test
    @Commit
    @DisplayName("이벤트 생성")
    void getTravelPeriod() {
        //given

        //when

        //then
    }

    @Test
    @Commit
    @DisplayName("이벤트 뽑아오기")
    void getEventEntityByEventId() {
        //given
        Long event_id = Long.valueOf(1); //대치동
        //when
        Event event = eventService.getEventEntityByEventId(event_id).get();
        //then
        System.out.println("name: " + event.getName());
    }
}