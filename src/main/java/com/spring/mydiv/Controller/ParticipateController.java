package com.spring.mydiv.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 12nov
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ParticipateController {

    /**여행에 사람 초대(생성)하기
     * -> 초대하고 싶은 사람 이메일로 사람 찾는 기능 필요*/
//    @PostMapping("/{userid}/{travelid}/invitePerson")
//    public void createPerson2Travel(String email){
//        //email: 초대할 사람 이메일 -> 아이디로 바꿔도 됨
//        //return service. user DB에서 이메일 일치하는 사람 있으면 -> person DB에 추가
//    }

    /**사람 생성하고 디테일뷰에서 해당 내용 불러오기
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
