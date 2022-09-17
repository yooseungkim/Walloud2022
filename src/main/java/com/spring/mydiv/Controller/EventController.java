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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
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
    @PostMapping("/{userid}/{travelid}/createEvent") //프론트 테스트중
    public List<PersonCreateDto.Simple> getPersonNameInTravel(@PathVariable int travelid){
        return personService.getPersonNameInTravel(travelid);
    }

    /**이벤트 생성하기
     * -> 프론트로부터 유저 정보 받아온다
     * */
//    @PostMapping("/{userid}/{travelid}/createEvent")
//    public void createEvent(@RequestBody Event newevent){
//        //RequestBody = 생성할 이벤트 정보
//        //return service.
//        //	event Db에 event 정보 등록 & participant DB에도 등록
//        //	participant DB에서 role & difference 수정
//    }

    /**이벤트 생성하고 디테일뷰에서 해당 내용 불러오기
     * -> 자세히 어떤 내용 필요할지는 논의 후 작성
     * */
//    @PostMapping("/{userid}/{travelid}/{eventid}")
//    public Event getEventInfoByEventId(@PathVariable int eventid){
//        //@PathVariable = 생성한 이벤트 아이디
//        //return service. event DB & participant DB에서 정보 리턴
//    }
}
