package com.spring.mydiv.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author 12nov
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ParticipantController {

    /**사람 "디테일뷰"에서 해당 내용 불러오기
     * -> 이 사람의 기본 정보(user에서 가져와야함)
     * -> +) getPersonInfoByPersonId
     * -> 이 사람이 참여한 event list(from event serv) -> getJoinEventListByPersonId
     * */
//    @PostMapping("/{userid}/{travelid}/{personid}")
//    public User getPersonInfoByPersonId(@PathVariable int personid){
//        //@PathVariable = 초대된 사람 아이디
//        //return service. person DB에서 personid랑 일치하는 사용자의 userid 찾기 -> userdb에서 userid일치하는 사용자 정보 리턴
//    }
//
//    @PostMapping("/{userid}/{travelid}/{personid}")
//    public List<Event> getJoinEventListByPersonId(@PathVariable int personid){
//        //@PathVariable = 초대된 사람 아이디
//        //return service. participant DB에서 personid랑 일치하는 사용자가 참여한 이벤트 리스트 리턴
//    }
}
