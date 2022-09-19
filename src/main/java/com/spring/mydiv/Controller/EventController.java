package com.spring.mydiv.Controller;

import com.spring.mydiv.Dto.PersonCreateDto;
import com.spring.mydiv.Dto.TravelCreateDto;
import com.spring.mydiv.Entity.Person;
import com.spring.mydiv.Entity.Travel;
import com.spring.mydiv.Entity.User;
import com.spring.mydiv.Repository.PersonRepository;
import com.spring.mydiv.Repository.UserRepository;
import com.spring.mydiv.Service.EventService;
import com.spring.mydiv.Service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author 12nov
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventController {
    private final EventService eventService;
    private final PersonService personService;

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
    @PostMapping("/{userid}/{travelid}/CreateEvent")
    public void createEvent(@PathVariable int travelid, @RequestBody Map map){
        /**s
         * 이벤트 생성
         * - 여행 id -> 여행 정보 받아오기 -> 받아온 여행 정보를 event에 입력
         * - 기본 정보들
         * - 참가자 리스트에 맞춰서 -> parti 생성
         *      - 결제 정보 계산하는 기능 필요(service)
         *      */
        //RequestBody = 생성할 이벤트 정보
        //return service.
        //	event Db에 event 정보 등록 & participant DB에도 등록
        //	participant DB에서 role & difference 수정
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
