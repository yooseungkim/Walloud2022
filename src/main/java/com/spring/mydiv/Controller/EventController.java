package com.spring.mydiv.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 12nov
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventController {
    /**이벤트 생성하는 화면에서 필요한거
     * - 여행 참가자 리스트(getPersonNameByTravelId)
     * */
//    @PostMapping("/{userid}/{travelid}/createEvent")
//    public List<String> getPersonNameByTravelId(@PathVariable int travelid){
//        //@PathVariable = 여행 아이디
//        //return service. person DB에서 travelid랑 일치하는 사용자 리스트 "이름만" 리턴
//    }

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
