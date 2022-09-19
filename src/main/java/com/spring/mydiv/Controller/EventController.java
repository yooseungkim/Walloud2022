package com.spring.mydiv.Controller;

import com.spring.mydiv.Dto.EventCreateDto;
import com.spring.mydiv.Dto.PersonCreateDto;
import com.spring.mydiv.Dto.TravelCreateDto;
import com.spring.mydiv.Dto.UserCreateDto;
import com.spring.mydiv.Entity.Person;
import com.spring.mydiv.Entity.Travel;
import com.spring.mydiv.Entity.User;
import com.spring.mydiv.Repository.PersonRepository;
import com.spring.mydiv.Repository.UserRepository;
import com.spring.mydiv.Service.EventService;
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
    public ResponseEntity<EventCreateDto.Response> createEvent(@PathVariable int travelid,
                                                              @RequestBody Map map){
        EventCreateDto.Request request = EventCreateDto.Request.builder()
                .Name(map.get("event_name").toString())
                .Travel(travelService.getTravelInfo(travelid))
                .Date((Date) map.get("event_name"))
                .Price(Integer.parseInt(map.get("event_name").toString()))
                .build();
        if (ResponseEntity.ok(eventService.createEvent(request)).toString() =="200"){
            /**
             * 날짜 & name 으로 일치하는 event를 검색,
             * 해당 값으로 participate 업데이트
             * - 해당 이벤트에서의 role만 결정
             * 해당 값으로 person 업데이트
             * - 결론적으로 얻어야 할 값 등 정해야함*/
        }
        return null;
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
