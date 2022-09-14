package com.spring.mydiv.Controller;

import java.util.List;
import java.util.Map;

import com.spring.mydiv.Dto.*;
import com.spring.mydiv.Service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.mydiv.Entity.Travel;
import com.spring.mydiv.Service.TravelService;
import com.spring.mydiv.Service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * @author 12nov
 */
//@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userservice;
    private final TravelService travelservice;
    private final PersonService personservice;

    @GetMapping("/halo")
    public String ddd() {
        return "He";
    }

    @PostMapping(value = "/Register")
    public ResponseEntity<UserCreateDto.Response> createUser(@RequestBody Map map) {

        System.out.println("");
        System.out.println(map.get("user_name"));
        System.out.println(map.get("user_email"));
        System.out.println(map.get("user_password"));
        System.out.println(map.get("user_account"));
        UserCreateDto.Request request = new UserCreateDto.Request(map.get("user_name").toString(),
                map.get("user_email").toString(),
                map.get("user_password").toString(),
                map.get("user_account").toString());
        return ResponseEntity.ok(userservice.createUser(request));
    }

    /**수정할지는 front분들이랑 다시 논의할 것
     * 지금은 로그인 성공(email 있음 & pw 일치)-> 유저 정보 그대로 리턴
     * 로그인 실패(email 없거나 | pw 틀리거나) -> 유저 정보 = null로 리턴
     * =>
     * email 없으면 -> none
     * pw 틀리면 -> fail
     * 둘다 맞으면 -> 유저 id.toString()
     * 리턴하는 방식으로 바꿔도 됨
     * */
//    @PostMapping(value = "/login")
//    public String login(@RequestBody Map map) { //ver 1.
//        UserCreateDto.Login loginUser = new UserCreateDto.Login(map.get("input_id").toString(),
//                map.get("input_password").toString());
//        UserCreateDto.Response answer = userservice.login(loginUser);
//        if (answer.getPassword() == null){
//            return "fail!";
//        } else {
//            return "Yes!";
//        }
//    }
    @PostMapping(value = "/login")
    public String login(@RequestBody Map map) { //ver 1.
        UserCreateDto.Login loginUser = new UserCreateDto.Login(map.get("input_id").toString(),
                map.get("input_password").toString());
        return userservice.login(loginUser);
    }

    @PostMapping("/{no}")
    public UserDetailDto getUserInfo(@PathVariable int no){
        //@PathVariable = 로그인한 유저 아이디
        return userservice.getUserInfo(no);
    }

    /** person DB에 user id 있으면 -> travel 리스트 리턴
     *  null -> "여행을 만들어보세요!" 출력해주세용(프론트에서)*/
    @PostMapping("/{no}/travellist")
    public List<String> getUserJoinedTravel(@PathVariable int no){
        return userservice.getUserJoinedTravel(no);
    }    //테스트해야함


    /**travel join
     * input: travel 정보 & 현재 유저 email
     * to travel db -> travel 생성
     * to person db -> 현재 유저 & travel 데이터 생성
     * return travel 메인 페이지*/
   @PostMapping("/{no}/joinTravel")
   public ResponseEntity<PersonDto> joinTravel(@PathVariable int userNo, @RequestBody Map map){
       TravelCreateDto.Request travelInfo = new TravelCreateDto.Request(map.get("travel_name").toString());
       PersonCreateDto.Request request = new PersonCreateDto.Request(
               userservice.getUserInfo(userNo),
               travelservice.createTravel(travelInfo));
       return ResponseEntity.ok(personservice.createPerson(request));
   }

}