package com.spring.mydiv.Controller;

import com.spring.mydiv.Dto.TravelDto;
import com.spring.mydiv.Entity.Event;
import com.spring.mydiv.Entity.Person;
import com.spring.mydiv.Service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 12nov
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TravelController {
    private final TravelService travelservice;

    /**travel main view에 보내줄 dto 만드는 컨트롤러
     * -> travel info(in travel service)
     * -> getPersonBrieflyByTravelId(in parti serv)
     * -> getEventBrieflyNameByTravelId(in event serv)
     * -> getEventNumByTravelId(in event serv)
     * */
//    @GetMapping("/{userid}/{travelid}")
//    public List<Person> getPersonBrieflyByTravelId(@PathVariable int travelid){
//        //@PathVariable = 여행 아이디
//        //return service. person DB에서 travelid랑 일치하는 사용자 리스트 "이름만" 리턴
//    }
//    @GetMapping("/{userid}/{travelid}")
//    public List<Event> getEventBrieflyNameByTravelId(@PathVariable int travelid){
//        //@PathVariable = 여행 아이디
//        //return service. event DB에서 travelid랑 일치하는 이벤트 리스트 리턴
//    }
//    @GetMapping("/{userid}/{travelid}")
//    public int getEventNumByTravelId(@PathVariable int travelid){
//        //@PathVariable = 여행 아이디
//        //return service. event DB에서 travelid랑 일치하는 이벤트 개수 세서 리턴
//    }


}
