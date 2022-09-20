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

    @GetMapping("/{userid}/{travelid}/createEvent") //don't use this
    public List<PersonCreateDto.Simple> getPersonNameInTravel(@PathVariable int travelid){
        return personService.getPersonNameInTravel(travelid);
    }

    @PostMapping("/{userid}/{travelid}/CreateEvent")
    public int createEvent(@PathVariable int travelId, @RequestBody Map map){
        //set event dto(name, travel(TravelCreateDto.Response), date, price)
        List<Map> partiDtoList = (List)map.get("parti_list");
        int partiCount = partiDtoList.size();
        EventCreateDto.Request request = EventCreateDto.Request.builder()
                .Name(map.get("event_name").toString())
                .Travel(travelService.getTravelInfo(travelId))
                .Date((Date) map.get("event_date"))
                .Price(Integer.parseInt(map.get("price").toString()))
                .PartiCount(partiCount)
                .build();
        //set dividePrice, takePrice(get은 method랑 혼동될 것 같아 이름 변경했습니다!)
        EventCreateDto.Response eventDto = eventService.createEvent(request);
        //get event dto(eventId)

        if (ResponseEntity.ok(eventDto).toString() =="200"){ //success to create event
            Long eventId = eventDto.getId();
            List<Person> personList = new ArrayList<>();

            //set parti dto(personId, eventId, role)
            for (Map partiDto : partiDtoList){
                Person person = personService.getPersonEntityByPersonId(Long
                        .valueOf(partiDto.get("person_id").toString())).get();
                personList.add(person);

                ParticipantCreateDto.Request partiRequest = ParticipantCreateDto.Request.builder()
                        .person(person)
                        .event(eventService.getEventEntityByEventId(Long
                                        .valueOf(partiDto.get("event_id").toString())).get())
                        .role(Boolean.valueOf(partiDto.get("role").toString()))
                        .build();
                if (ResponseEntity.ok(participantService.createParticipant(partiRequest)).toString() != "200")
                    return -2; //fail to create participate
            }

            //set person dto(sumsend, sumget, difference, travelRole)
            personService.updatePersonWithEvent(personList,
                    Long.valueOf(map.get("payer_person_id").toString()),
                    eventDto.getDividePrice(),
                    eventDto.getTakePrice());
//                return -3; //fail to update person

            return 200; //success all
        } else
            return -1; //fail to create event
    }

    /**이벤트 생성하고 디테일뷰에서 해당 내용 불러오기
     * -> 자세히 어떤 내용 필요할지는 논의 후 작성
     * */
//    @PostMapping("/{userid}/{travelid}/{eventid}")
//    public Event getEventInfoByEventId(@PathVariable int eventid){
//        //@PathVariable = 생성한 이벤트 아이디
//        //return service. event DB & participant DB에서 정보 리턴
//    }
}
