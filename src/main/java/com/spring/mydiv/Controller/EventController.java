package com.spring.mydiv.Controller;

import com.spring.mydiv.Dto.*;
import com.spring.mydiv.Entity.Person;
import com.spring.mydiv.Service.EventService;
import com.spring.mydiv.Service.ParticipantService;
import com.spring.mydiv.Service.PersonService;
import com.spring.mydiv.Service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 12nov
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventController {
    private final EventService eventService;
    private final PersonService personService;
    private final TravelService travelService;
    private final ParticipantService participantService;

//    @GetMapping("/{userid}/{travelid}/createEvent") //don't use this
//    public List<PersonDto.Simple> getPersonNameInTravel(@PathVariable("travelid") int travelid){
//        return personService.getPersonNameInTravel(travelid);
//    }

    @PostMapping("/{userid}/{travelid}/CreateEvent")
    public int createEvent(@PathVariable("travelid") int travelId, @RequestBody Map map) throws ParseException {
        //set event dto(name, travel(TravelCreateDto.Response), date, price)
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Map> partiDtoList = (List)map.get("parti_list");
        int partiCount = partiDtoList.size();
        EventDto.Request request = EventDto.Request.builder()
                .Name(map.get("event_name").toString())
                .Travel(travelService.getTravelInfo(travelId))
                .Date(simpleDateFormat.parse(map.get("event_date").toString()))
                .Price(Integer.parseInt(map.get("price").toString()))
                .PartiCount(partiCount)
                .build();
        boolean isPayerinParticipant = eventService.checkPayerInParticipant(partiDtoList,
                Long.valueOf(map.get("payer_person_id").toString()));
        //set dividePrice, takePrice
        EventDto.Response eventDto = eventService.createEvent(request, isPayerinParticipant);
        //get event dto(eventId)

        if (ResponseEntity.ok(eventDto).getStatusCodeValue() == 200){ //success to create event
            List<Person> personList = new ArrayList<>();
            System.out.println(partiDtoList.size());
            //set parti dto(personId, eventId, role)
            for (Map partiDto : partiDtoList){ // 결제자가 참가자에 들어가지 않을 경우를 handling 해야함
                Person person = personService.getPersonEntityByPersonId(Long
                        .valueOf(partiDto.get("id").toString())); //orElseThrow
                personList.add(person);

                ParticipantDto.Request partiRequest = ParticipantDto.Request.builder()
                        .person(person)
                        .event(eventService.getEventEntityByEventId(Long
                                        .valueOf(eventDto.getId().toString()))) //orElseThrow
                        .role(Boolean.valueOf(partiDto.get("role").toString()))
                        .build();
                if (ResponseEntity.ok(participantService.createParticipant(partiRequest)).getStatusCodeValue() != 200)
                    return -2; //fail to create participate
            }

            System.out.println(eventDto.getDividePrice());

            //set person dto(sumsend, sumget, difference, travelRole)
            personService.updatePersonMoneyByCreating(personList,
                    Long.valueOf(map.get("payer_person_id").toString()),
                    eventDto.getDividePrice(),
                    eventDto.getTakePrice());
//                return -3; //fail to update person
            personService.updatePersonRole(travelId);
            return 200; //success all
        } else
            return -1; //fail to create event
    }

    /**이벤트 생성하고 디테일뷰에서 해당 내용 불러오기
     * -> 자세히 어떤 내용 필요할지는 논의 후 작성
     * */

    @DeleteMapping("/{userid}/{travelid}/{eventid}/deleteEvent")
    public void deleteEvent(@PathVariable("eventid") int event_id)
    {
        EventDto.deleteRequest DeleteRequest = eventService.getEventDetailforDelete(event_id);
        personService.updatePersonMoneyByDeleting(DeleteRequest.getJoinedPerson(),
                DeleteRequest.getPayerId(),
                DeleteRequest.getDividePrice(),
                DeleteRequest.getTakePrice());
        eventService.deleteEvent(event_id);
    }

    @GetMapping("/{userid}/{travelid}/{eventid}/detail")
    public List<ParticipantDto.detailView> getDetailInEvent(@PathVariable("eventid") int eventid){
        return participantService.getParticipantInEvent(eventid);
    }
}
