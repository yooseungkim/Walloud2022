package com.spring.mydiv.Controller;

import com.spring.mydiv.Dto.*;
import com.spring.mydiv.Entity.Person;
import com.spring.mydiv.Entity.Travel;
import com.spring.mydiv.Entity.User;
import com.spring.mydiv.Repository.PersonRepository;
import com.spring.mydiv.Repository.UserRepository;
import com.spring.mydiv.Service.EventService;
import com.spring.mydiv.Service.ParticipateService;
import com.spring.mydiv.Service.PersonService;
import com.spring.mydiv.Service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.unit.DataUnit;
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
    private final ParticipateService participateService;

    /**이벤트 생성하는 화면에서 필요한거
     * - 여행 참가자 리스트
     * */
    @GetMapping("/{userid}/{travelid}/createEvent") //프론트 테스트중
    public List<PersonCreateDto.Simple> getPersonNameInTravel(@PathVariable int travelid){
        return personService.getPersonNameInTravel(travelid);
    }

    /**이벤트 생성하기
     * -> 프론트로부터 event 정보 받아온다
     * -> 참가한 유저 리스트도 주셔야 해요!(map에 넣어서)
     * */
    @PostMapping("/{userid}/{travelid}/CreateEvent") //백엔드 테스트중
    public int createEvent(@PathVariable int travelid, @RequestBody Map map){
        //set event dto(name, travel(TravelCreateDto.Response), date, price)
        EventCreateDto.Request request = EventCreateDto.Request.builder()
                .Name(map.get("event_name").toString())
                .Travel(travelService.getTravelInfo(travelid))
                .Date((Date) map.get("event_name"))
                .Price(Integer.parseInt(map.get("event_name").toString()))
                .build();
        ResponseEntity<EventCreateDto.Response> eventDto = eventService.createEvent(request);
        //get event dto(eventId)

        if (ResponseEntity.ok(eventDto).toString() =="200"){ //success to create event
            //set parti dto(personId, eventId, role)
            List<ParticipateCreateDto.Request> partiDtoList =
                    (List<ParticipateCreateDto.Request>)map.get("parti_list");
            if (ResponseEntity.ok(participateService.createParticipate()).toString() == "200"){
                //set person dto(sumsend, sumget, difference, travelRole)
                if (ResponseEntity.ok(personService.updatePersonWithEvent()).toString() == "200")
                    return 200; //success all
                else return -3; //fail to update person
            } else return -2; //fail to create participate
        } else return -1; //fail to create event
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
