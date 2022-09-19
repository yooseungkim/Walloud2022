package com.spring.mydiv.Controller;

import com.spring.mydiv.Dto.PersonCreateDto;
import com.spring.mydiv.Dto.PersonDto;
import com.spring.mydiv.Dto.TravelCreateDto;
import com.spring.mydiv.Dto.UserDetailDto;
import com.spring.mydiv.Entity.User;
import com.spring.mydiv.Repository.PersonRepository;
import com.spring.mydiv.Repository.UserRepository;
import com.spring.mydiv.Service.ParticipateService;
import com.spring.mydiv.Service.PersonService;
import com.spring.mydiv.Service.TravelService;
import com.spring.mydiv.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

/**
 * @author 12nov
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ParticipateController {

    private final UserService userService;
    private final TravelService travelService;
    private final PersonService personService;
    
    @PostMapping("/{userid}/{travelid}/invitePerson") //프론트 테스트중
    public String createPerson2Travel(@PathVariable int travelid,
                                    @RequestBody Map map){
        String user_email = map.get("user_email").toString();
        UserDetailDto userDetailDto = userService.getUserInfoByEmail(user_email);
        if (userDetailDto == null){
            return "-1";
        } else {
            PersonCreateDto.Request request = new PersonCreateDto.Request(
                    userDetailDto,
                    travelService.getTravelInfo(travelid));
            PersonDto personDto = personService.createPerson(request);
            if (personDto != null)
                return "200"; //success
            else return "-2"; //fail
        }
    }

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
